package edu.pro.crypto.testtaskcrypto.model;
/*
  @author   george
  @project   test-task-crypto
  @class  CryptoCurrency
  @version  1.0.0 
  @since 13.10.23 - 11.02
*/

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
@NoArgsConstructor
@Getter
@Setter
@Document
public class CryptoCurrency {
    @Id
    private String id;  // generated by mongoDb
    private String symbol;
    private Double price;
    private LocalDateTime time; // generated by the back-end

    public CryptoCurrency(String symbol, Double price, LocalDateTime time) {
        this.symbol = symbol;
        this.price = price;
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CryptoCurrency that = (CryptoCurrency) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}