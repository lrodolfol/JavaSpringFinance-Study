package com.tinosnegocios.financas.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;

@Entity
@Table(name = "tb_balance_flow")
public class BalanceFlow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String description;
    private String observation;
    private Double balance;
    private Instant moment;
    @ManyToOne
    @JoinColumn(name = "input_id")
    private BalanceInput balanceInput;
    @ManyToOne
    @JoinColumn(name = "output_id")
    private BalanceOutPut balanceOutPut;

    public BalanceFlow(String description, String observation, Double balance, Instant moment, BalanceInput balanceInput, BalanceOutPut balanceOutPut) {
        this.description = description;
        this.observation = observation;
        this.balance = balance;
        this.moment = moment;
        this.balanceInput = balanceInput;
        this.balanceOutPut = balanceOutPut;
    }
    public BalanceFlow() {
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

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public BalanceInput getBalanceInput() {
        return balanceInput;
    }

    public void setBalanceInput(BalanceInput balanceInput) {
        this.balanceInput = balanceInput;
    }

    public BalanceOutPut getBalanceOutPut() {
        return balanceOutPut;
    }

    public void setBalanceOutPut(BalanceOutPut balanceOutPut) {
        this.balanceOutPut = balanceOutPut;
    }
}
