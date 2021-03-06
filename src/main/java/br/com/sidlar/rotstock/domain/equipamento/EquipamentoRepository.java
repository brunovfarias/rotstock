package br.com.sidlar.rotstock.domain.equipamento;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class EquipamentoRepository {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void gravaEquipamento(Equipamento equipamento) {
        em.persist(equipamento);
    }

    @Transactional
    public void alteraEquipamento(Equipamento equipamento){
        em.merge(equipamento);
    }

    public Equipamento buscaPorId(int id){
        return em.find(Equipamento.class,id);
    }

    public List<Equipamento> buscaTodos() {
            String jpql ="SELECT i FROM Equipamento i";

        return em.createQuery(jpql, Equipamento.class).getResultList();
    }

    public List<Equipamento> buscaPorLocal(int idLocal) {
        String jpql ="SELECT i " +
                "FROM Equipamento i WHERE i.local.id = :idLocal";

        return em.createQuery(jpql, Equipamento.class)
                .setParameter("idLocal", idLocal)
                .getResultList();
    }

    public Equipamento buscaPorSerial(String serial) {
        String jpql ="SELECT i " +
                "FROM Equipamento i WHERE i.serial = :serial";

        List retorno = em.createQuery(jpql, Equipamento.class)
                       .setParameter("serial",serial)
                        .getResultList();

        if(retorno.isEmpty()){
            return null;
        }
        return (Equipamento) retorno.get(0);
    }

    public boolean exists(String serial) {
        String jpql ="SELECT i " +
                "FROM Equipamento i WHERE i.serial = :serial";

        return !em.createQuery(jpql, Equipamento.class)
                .setParameter("serial",serial)
                .getResultList()
                .isEmpty();
    }

    public List<Equipamento> buscaPorTipoEquipamento(Class entityClass) {
        CriteriaBuilder builder = em.getCriteriaBuilder();

        CriteriaQuery<Equipamento> criteria = builder.createQuery(Equipamento.class);
        Root<Equipamento> entityRoot = criteria.from(entityClass);
        criteria.select(entityRoot);

        return em.createQuery(criteria).getResultList();
    }

    public List<Equipamento> buscaPorTipoEquipamentoLocal(Class entityClass,int idLocal) {
        CriteriaBuilder builder = em.getCriteriaBuilder();

        CriteriaQuery<Equipamento> criteria = builder.createQuery(entityClass);

        Root<Equipamento> equipamento = criteria.from(entityClass);
        criteria.select(equipamento);
        criteria.where(builder.equal(equipamento.get("local").get("id"), idLocal));

        return em.createQuery(criteria).getResultList();
    }

    @Transactional
    public void removeEquipamento(Integer id) {
        em.remove(em.find(Equipamento.class,id));
    }
}