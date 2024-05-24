package com.tinosnegocios.financas.entities;

import com.google.gson.annotations.SerializedName;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_rating")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }
    @SerializedName("Source")
    private String TheSource;
    @SerializedName("Value")
    private String TheValue;

    @ManyToOne()
    @JoinColumn(name = "movie_id")
    private Movie movie;

    public String getTheSource() {
        return TheSource;
    }

    public void setTheSource(String theSource) {
        TheSource = theSource;
    }

    public String getTheValue() {
        return TheValue;
    }

    public void setTheValue(String theValue) {
        TheValue = theValue;
    }
}
