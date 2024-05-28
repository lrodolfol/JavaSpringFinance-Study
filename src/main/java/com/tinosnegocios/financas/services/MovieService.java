package com.tinosnegocios.financas.services;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tinosnegocios.financas.utils.FilesStorage;
import com.tinosnegocios.financas.entities.Movie;
import com.tinosnegocios.financas.entities.Rating;
import com.tinosnegocios.financas.enuns.StorageExtension;
import com.tinosnegocios.financas.exceptions.DataBaseException;
import com.tinosnegocios.financas.repositories.MovieRepository;
import com.tinosnegocios.financas.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class MovieService {
    private String movieUri = "http://www.omdbapi.com";
    private String myApiKey = "a03424d2";
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    RatingRepository ratingRepository;

    public Movie getMovie(String title) {
        Movie movie = new Movie();

        HttpResponse<String> response = makeRequest(title);

        if (response.statusCode() == 200 && !response.body().isEmpty()) {
            movie = convertJsonToObject(response.body());
        }

        return movie;
    }

    public Movie getAndPersistMovie(String title) {
        Movie movie = new Movie();

        HttpResponse<String> response = makeRequest(title);

        if (response.statusCode() == 200 && !response.body().isEmpty()) {
            movie = convertJsonToObject(response.body());
        }

        if (movie.getResponse()) {
            Random r = new Random();
            storagePersist(movie, r.nextInt() % 2 == 0 ? StorageExtension.json : StorageExtension.txt);
            dataBasePersist(movie);
        }

        return movie;
    }

    private void dataBasePersist(Movie movie) {
        try {
            Movie movieSalved = movieRepository.save(movie);
            List<Rating> ratingList = new ArrayList<>();

            for (Rating rating : movie.getRatings()) {
                rating.setMovie(movieSalved);
                ratingList.add(rating);
            }

            ratingRepository.saveAll(ratingList);
        } catch (Exception ex) {
            throw new DataBaseException("Erro ao cadastrar dados. " + ex.getMessage());
        }
    }

    private void storagePersist(Movie movie, StorageExtension extension) {
        String path = System.getProperty("user.dir") + File.separator + "files" + File.separator + "movie_";
        FilesStorage storage = new FilesStorage(path + movie.getTitle(), extension.toString());

        String content = switch (extension) {
            case txt -> convertObjectToTxt(movie);
            case json -> convertJsonToJson(movie);
            default -> throw new RuntimeException("Method for convert not implementation");
        };

        storage.write(content);
    }

    private String convertObjectToTxt(Movie movie) {
        return movie.toString();
    }

    private String convertJsonToJson(Movie movie) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String content = gson.toJson(movie);

        return content;
    }

    private Movie convertJsonToObject(String json) {
        Movie movie = new Movie();

        try {
            //Gson gson = new Gson();
            Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
            movie = gson.fromJson(json, Movie.class);
        } catch (Exception ex) {
            System.out.println("Falha na conversao de objeto. ");
        }

        return movie;
    }

    private HttpResponse<String> makeRequest(String title) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .timeout(Duration.of(10, ChronoUnit.SECONDS))
                    .uri(URI.create(movieUri + "/?t=" + title + "&apikey=" + myApiKey))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return response;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
