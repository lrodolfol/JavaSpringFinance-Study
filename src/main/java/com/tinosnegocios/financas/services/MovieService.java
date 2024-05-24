package com.tinosnegocios.financas.services;

import com.google.gson.Gson;
import com.tinosnegocios.financas.entities.Movie;
import com.tinosnegocios.financas.entities.Rating;
import com.tinosnegocios.financas.exceptions.DataBaseException;
import com.tinosnegocios.financas.repositories.MovieRepository;
import com.tinosnegocios.financas.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {
    private String movieUri = "http://www.omdbapi.com";
    private String myApiKey = "a03424d2";
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    RatingRepository ratingRepository;

    public Movie getMovie(String title, boolean persist) {
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
                movie = convertJsonToObject(response.body());
            }

            if(persist){
                persistMovie(movie);
            }
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }

        return movie;
    }
    private Movie convertJsonToObject(String json) {
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
    private void persistMovie(Movie movie){
        try{
            Movie movieSalved = movieRepository.save(movie);
            List<Rating> ratingList = new ArrayList<>();

            for (Rating rating : movie.getRatings()) {
                rating.setMovie(movieSalved);
                ratingList.add(rating);
            }

            ratingRepository.saveAll(ratingList);
        }catch(Exception ex){
            throw new DataBaseException("Erro ao cadastrar dados. "+ ex.getMessage());
        }
    }

}
