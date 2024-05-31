package com.tinosnegocios.financas.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tinosnegocios.financas.enuns.PaymentMethod;
import com.tinosnegocios.financas.models.dto.BalanceOutputDto;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "tb_balance_output")
public class BalanceOutPut {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private String observation;
    private Double amount;
    private Instant moment;
    private String establishment;
    private Double discount;
    private Double interest;
    private boolean atypical;
    private PaymentMethod paymentMethod;
    @OneToMany(mappedBy = "balanceOutPut")
    @JsonIgnore
    private List<BalanceFlow> balanceFlowList;

    public BalanceOutPut(Long id, String description, String observation, Double amount, Instant moment, String establishment,
                         Double discount, Double interest, boolean atypical, PaymentMethod paymentMethod) {
        this.id = id;
        this.description = description;
        this.observation = observation;
        this.amount = amount;
        this.moment = moment;
        this.establishment = establishment;
        this.discount = discount;
        this.interest = interest;
        this.atypical = atypical;
        this.paymentMethod = paymentMethod;
    }

    public PaymentMethod getPaymentMethod(){
        return PaymentMethod.valuOf(this.paymentMethod.getCode());
    }
    public void setPaymentMethod(PaymentMethod paymentMethod){
        this.paymentMethod = paymentMethod;
    }
    public BalanceOutPut(BalanceOutputDto dto) {
        this.description = dto.description();
        this.observation = dto.observation();
        this.amount = dto.amount();
        this.moment = dto.moment();
        this.establishment = dto.establishment();
        this.discount = dto.discount();
        this.interest = dto.interest();
        this.atypical = dto.atypical();
        this.paymentMethod = dto.paymentMethod();
    }
    public BalanceOutPut() {
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

    public String getEstablishment() {
        return establishment;
    }

    public void setEstablishment(String establishment) {
        this.establishment = establishment;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getInterest() {
        return interest;
    }

    public void setInterest(Double interest) {
        this.interest = interest;
    }

    public boolean isAtypical() {
        return atypical;
    }

    public void setAtypical(boolean atypical) {
        this.atypical = atypical;
    }
}
