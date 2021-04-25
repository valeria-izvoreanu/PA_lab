package DAO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "genres", schema = "public", catalog = "Movie")
@NamedQueries({
        @NamedQuery(name = "Genres.findByName",
                query = "select p FROM GenresEntity p WHERE p.nume=:nume")})
public class GenresEntity {
    private int id;
    private String nume;
    private List<MoviesEntity> moviesEntityList = new ArrayList<>();

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "nume", nullable = false, length = 30)
    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    @ManyToMany(mappedBy = "genresEntityList")
    public List<MoviesEntity> getMoviesEntityList() {
        return moviesEntityList;
    }

    public void setMoviesEntityList(List<MoviesEntity> moviesEntityList) {
        this.moviesEntityList = moviesEntityList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenresEntity that = (GenresEntity) o;
        return id == that.id && Objects.equals(nume, that.nume);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nume);
    }

    @Override
    public String toString() {
        return "GenresEntity{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", moviesEntityList=" + moviesEntityList +
                '}';
    }
}
