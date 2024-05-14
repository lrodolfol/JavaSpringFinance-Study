package com.tinosnegocios.financas.entities;

import java.time.Instant;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tb_balance_input")
public class BalanceInput {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String description;
    private String observation;
    private Double amount;
    private Instant moment;
    private boolean realProfit;
    @OneToMany(mappedBy = "balanceInput")
    @JsonIgnore
    private List<BalanceFlow> balanceFlowList;

    public BalanceInput(String description, String observation, Double amount, Instant moment, boolean realProfit) {
        this.description = description;
        this.observation = observation;
        this.amount = amount;
        this.moment = moment;
        this.realProfit = realProfit;
    }
    public BalanceInput(Long id, String description, String observation, Double amount, Instant moment, boolean realProfit) {
        this.id = id;
        this.description = description;
        this.observation = observation;
        this.amount = amount;
        this.moment = moment;
        this.realProfit = realProfit;
    }

    public BalanceInput() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public boolean isRealProfit() {
        return realProfit;
    }

    public void setRealProfit(boolean realProfit) {
        this.realProfit = realProfit;
    }

    @Override
    public String toString() {
        return "BalanceInput{" +
                "description='" + description + '\'' +
                ", observation='" + observation + '\'' +
                ", amounth=" + amount +
                ", moment=" + moment +
                ", realProfit=" + realProfit +
                '}';
    }
}
