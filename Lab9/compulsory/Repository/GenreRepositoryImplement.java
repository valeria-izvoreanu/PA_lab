package Repository;

import DAO.GenresEntity;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class GenreRepositoryImplement implements GenreRepository {
    private EntityManager em;

    public GenreRepositoryImplement(EntityManager em) {
        this.em = em;
    }

    public void create(GenresEntity genre) {
        em.getTransaction().begin();
        em.persist(genre);
        em.getTransaction().commit();
    }

    public GenresEntity findByID(int id) {
        return em.find(GenresEntity.class, id);
    }

    public List<GenresEntity> findByName(String nume) {
        Query query = em.createNamedQuery("Genres.findByName").setParameter("nume", nume);
        return query.getResultList();
    }
}
