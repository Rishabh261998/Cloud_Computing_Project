package com.cs551clc.ecommerce.service.utils.exception;

import lombok.*;

import java.math.BigInteger;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorProduct {
    private String productId;
    private BigInteger qty;
}
