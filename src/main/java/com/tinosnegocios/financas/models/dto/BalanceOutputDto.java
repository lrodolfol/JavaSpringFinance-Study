package com.tinosnegocios.financas.models.dto;

import com.tinosnegocios.financas.enuns.PaymentMethod;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;

public record BalanceOutputDto(String description, String observation, Double amount,
                               Instant moment, String establishment, Double discount, Double interest,
                               boolean atypical, PaymentMethod paymentMethod) {

}
