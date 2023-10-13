package edu.pro.crypto.testtaskcrypto.service;
/*
  @author   george
  @project   test-task-crypto
  @class  CryptoCurrencyService
  @version  1.0.0 
  @since 13.10.23 - 12.04
*/


import edu.pro.crypto.testtaskcrypto.dto.PriceDto;
import edu.pro.crypto.testtaskcrypto.model.CryptoCurrency;
import edu.pro.crypto.testtaskcrypto.repository.CryptoCurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
public class CryptoCurrencyService {

    @Value("telegram.bot.back-end.start-tracking")
    private String start;
    @Value("telegram.bot.back-end.critical-rise")
    private String crisis;

    private final CryptoCurrencyRepository repository;
    private RestTemplate restTemplate = new RestTemplate();
    private final String resourceUrl
            = "https://api.mexc.com/api/v3/ticker/price";

    private Map<String, Double> criticalValues = new HashMap<>();

    @Autowired
    public CryptoCurrencyService(CryptoCurrencyRepository repository) {
        this.repository = repository;
    }

    private List<Double> getAllPricesBySymbolAndAfterStart(String symbol) {
        LocalDateTime from = LocalDateTime.now().withHour(Integer.valueOf(start));
        return repository.findAllBySymbol(symbol).stream()
                .filter(item -> item.getTime().isAfter(from))
                .map(item -> item.getPrice())
                .toList();
    }

    // create a map <symbol, increment\decrement in %%>
    private Map<String, Double> getMapOfCriticalChanges() {
         List<String> symbols = repository.findAll().stream()
                 .map(CryptoCurrency::getSymbol)
                 .toList();
         symbols.forEach(symbol -> {
             List<Double> values = getAllPricesBySymbolAndAfterStart(symbol);
             Double min = values.stream().mapToDouble(x->x).min().getAsDouble();
             Double max = values.stream().mapToDouble(x->x).max().getAsDouble();
             Double current = values.get(values.size()-1); // the freshest record
             Double delta1 = Math.abs(current - min);  // absolute value of increment
             Double delta2 = Math.abs(current - max);  // absolute value of decrement
             if ((delta1/min)*100 >= Double.valueOf(crisis)) {
                 criticalValues.put(symbol, (delta1/min)*100);  // %% of increment
             }
             if ((delta1/max)*100 >= Double.valueOf(crisis)) {
                 criticalValues.put(symbol, (delta1/max)*100);  // %% of decrement
             }
         } );
        return null;
    }

    public List<PriceDto> getFreshValues() {
        // TODO via rest template
        return null;
    }
    private List<CryptoCurrency> refreshDb(List<PriceDto> list) {
        List<CryptoCurrency> currentValues = list.stream()
                .map(item ->
                        new CryptoCurrency(
                        item.getSymbol(),
                        item.getPrice(),
                        LocalDateTime.now()
                        )
                )
                .toList();

       return repository.saveAll(currentValues);
    }



}
