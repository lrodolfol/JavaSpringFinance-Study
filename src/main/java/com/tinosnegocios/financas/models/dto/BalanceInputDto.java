package com.tinosnegocios.financas.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;

public record BalanceInputDto(Long id, @NotNull String description, @NotBlank String observation, Double amount,
                              Instant moment, boolean realProfit) {
}