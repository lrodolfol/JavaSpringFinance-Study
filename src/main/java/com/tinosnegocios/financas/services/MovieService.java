package com.tinosnegocios.financas.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.tinosnegocios.financas.entities.Movie;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Service
public class MovieService {
    private String movieUri = "http://www.omdbapi.com";
    private String myApiKey = "a03424d2";

    public Movie GetMovie(String title) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .timeout(Duration.of(10, ChronoUnit.SECONDS))
                .uri(URI.create(movieUri + "/?t=" + title + "&apikey=" + myApiKey))
                .build();

        Movie movie = new Movie();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            //System.out.println("resposta: " + response.body());

            if (response.statusCode() == 200 && !response.body().isEmpty()) {
                movie = conterJsonToObject(response.body());
            }
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }

        return movie;
    }

    private Movie conterJsonToObject(String json) {
        Movie movie = new Movie();

        try{
            Gson gson = new Gson();
            movie = gson.fromJson(json, Movie.class);
            System.out.println("Objeto convertido com sucesso");
        }catch (Exception ex) {
            System.out.println("Falha na conversao de objeto. ");
        }

        return movie;
    }
}
