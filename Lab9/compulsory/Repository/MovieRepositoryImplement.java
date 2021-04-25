package Repository;

import DAO.MoviesEntity;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class MovieRepositoryImplement implements MovieRepository {
    private EntityManager em;

    public MovieRepositoryImplement(EntityManager em) {
        this.em = em;
    }

    public void create(MoviesEntity movie) {
        em.getTransaction().begin();
        em.persist(movie);
        em.getTransaction().commit();
    }

    public MoviesEntity findByID(int id) {
        return em.find(MoviesEntity.class, id);
    }

    public List<MoviesEntity> findByName(String title) {
        Query query = em.createNamedQuery("Movies.findByTitle").setParameter("title", title);
        return query.getResultList();
    }
}
