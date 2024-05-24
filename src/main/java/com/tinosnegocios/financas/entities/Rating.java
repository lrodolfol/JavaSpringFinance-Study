package com.tinosnegocios.financas.entities;

import com.google.gson.annotations.SerializedName;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_rating")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @SerializedName("Source") //ao converter para json, o campo do json a ser lido é 'Source'
    private String theSource;
    @SerializedName("Value") //ao converter para json, o campo do json a ser lido é 'Value'
    private String theValue;

    @ManyToOne()
    @JoinColumn(name = "movie_id")
    private Movie movie;

    public String getTheSource() {
        return theSource;
    }

    public void setTheSource(String theSource) {
        this.theSource = theSource;
    }

    public String getTheValue() {
        return theValue;
    }

    public void setTheValue(String theValue) {
        this.theValue = theValue;
    }
    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
