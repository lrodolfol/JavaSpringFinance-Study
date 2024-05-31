package com.tinosnegocios.financas.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;

public record BalanceInputDto(String description, String observation, Double amount,
                              Instant moment, boolean realProfit) {
}