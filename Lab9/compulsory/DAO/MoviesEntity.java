package DAO;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "movies", schema = "public", catalog = "Movie")
@NamedQueries({
        @NamedQuery(name = "Movies.findByTitle",
                query = "select p FROM MoviesEntity p WHERE p.title=:title")})

public class MoviesEntity {
    private int id;
    private String title;
    private List<GenresEntity> genresEntityList = new ArrayList<>();
    private Date releaseDate;
    private int movieLength;
    private int score;


    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "title", nullable = false, length = 1000)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "release_date")
    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Basic
    @Column(name = "movie_length")
    public int getMovieLength() {
        return movieLength;
    }

    public void setMovieLength(int movieLength) {
        this.movieLength = movieLength;
    }

    @Basic
    @Column(name = "score")
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "collections",
            joinColumns = @JoinColumn(name = "id_movie"),
            inverseJoinColumns = @JoinColumn(name = "id_genre"))
    public List<GenresEntity> getGenresEntityList() {
        return genresEntityList;
    }

    public void setGenresEntityList(List<GenresEntity> genresEntityList) {
        this.genresEntityList = genresEntityList;
    }

    public void addGenre(GenresEntity genresEntity) {
        genresEntityList.add(genresEntity);
        genresEntity.getMoviesEntityList().add(this);
    }

    public void removeTag(GenresEntity genresEntity) {
        genresEntityList.remove(genresEntity);
        genresEntity.getMoviesEntityList().remove(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MoviesEntity that = (MoviesEntity) o;
        return id == that.id && Objects.equals(title, that.title) && Objects.equals(releaseDate, that.releaseDate) && Objects.equals(movieLength, that.movieLength) && Objects.equals(score, that.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, releaseDate, movieLength, score);
    }

    @Override
    public String toString() {
        return "MoviesEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", releaseDate=" + releaseDate +
                ", movieLength=" + movieLength +
                ", score=" + score +
                '}';
    }
}
