package br.com.sidlar.rotstock.domain;

import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class LocalRepository {

    @PersistenceContext
    private EntityManager em;

    public List<Local> buscaTodosLocais() {
        TypedQuery<Local> query = em.createQuery("SELECT l FROM Local l", Local.class);
        return query.getResultList();
    }
    public Local buscaPorId(int idLocal) {
        return em.find(Local.class,idLocal);
    }
}
