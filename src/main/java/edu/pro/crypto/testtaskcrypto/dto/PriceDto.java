package edu.pro.crypto.testtaskcrypto.dto;
/*
  @author   george
  @project   test-task-crypto
  @class  PriceDto
  @version  1.0.0 
  @since 13.10.23 - 12.13
*/

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PriceDto {
    private String symbol;
    private Double price;
}
