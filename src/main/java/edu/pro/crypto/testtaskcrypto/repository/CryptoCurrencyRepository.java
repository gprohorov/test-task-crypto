package edu.pro.crypto.testtaskcrypto.repository;
/*
  @author   george
  @project   test-task-crypto
  @class  CryptoCurrencyRepository
  @version  1.0.0 
  @since 13.10.23 - 12.01
*/

import edu.pro.crypto.testtaskcrypto.model.CryptoCurrency;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CryptoCurrencyRepository extends MongoRepository<CryptoCurrency, String> {
    List<CryptoCurrency> findAllBySymbol(String symbol);
}
