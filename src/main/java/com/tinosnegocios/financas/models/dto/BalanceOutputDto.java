package com.tinosnegocios.financas.models.dto;

import com.tinosnegocios.financas.enuns.PaymentMethod;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;

public record BalanceOutputDto(Long id, @NotNull String description, @NotNull String observation, Double amount,
                               Instant moment, @NotNull String establishment, Double discount, Double interest,
                               boolean atypical, PaymentMethod paymentMethod) {

}
