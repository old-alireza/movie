package alireza.sn.moviesguide.model;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

public class Details {

    private int id;
    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setRated(String rated) {
        this.rated = rated;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public void setMetascore(int metascore) {
        this.metascore = metascore;
    }

    public void setImdb_rating(float imdb_rating) {
        this.imdb_rating = imdb_rating;
    }

    public void setImdb_votes(String imdb_votes) {
        this.imdb_votes = imdb_votes;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    private String title;
    private int year;
    private String rated;
    private String released;
    private String runtime;
    private String director;
    private String writer;
    private String actors;
    private String plot;
    private String country;
    private String awards;
    private int metascore;
    private float imdb_rating;
    private String imdb_votes;
    private List<String> genres;
    private List<String> images;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public String getRated() {
        return rated;
    }

    public String getReleased() {
        return released;
    }

    public String getRuntime() {
        return runtime;
    }

    public String getDirector() {
        return director;
    }

    public String getWriter() {
        return writer;
    }

    public String getActors() {
        return actors;
    }

    public String getPlot() {
        return plot;
    }

    public String getCountry() {
        return country;
    }

    public String getAwards() {
        return awards;
    }

    public int getMetascore() {
        return metascore;
    }

    public float getImdb_rating() {
        return imdb_rating;
    }

    public String getImdb_votes() {
        return imdb_votes;
    }

    public List<String> getGenres() {
        return genres;
    }

    public List<String> getImages() {
        return images;
    }

    public Details() {
    }
}
