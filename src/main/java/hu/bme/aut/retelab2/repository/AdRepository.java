package hu.bme.aut.retelab2.repository;


import hu.bme.aut.retelab2.domain.Ad;
import hu.bme.aut.retelab2.domain.Note;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Transient;
import java.util.List;
import java.util.stream.Collectors;

//LC3M9F
@Repository
public class AdRepository {


    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Ad save(Ad ad){
        return em.merge(ad);
    }

    public List<Ad> findAll() {
        return em.createQuery("SELECT a FROM Ad a", Ad.class).getResultList();
    }

    public Ad findById(long id) {
        return em.find(Ad.class, id);
    }

    @Transactional
    public void deleteById(long id) {
        Ad todo = findById(id);
        em.remove(todo);
    }
    //LC3M9F
    public List<Ad> findByPriceRange(int minPrice, int maxPrice){
        List<Ad> result = em.createQuery("SELECT a FROM Ad a WHERE a.price BETWEEN ?1 and ?2",Ad.class)
                .setParameter(1,minPrice)
                .setParameter(2,maxPrice)
                .getResultList();
        result.stream().forEach(
                        add -> add.setSecret(null)
                );
        return result;
    }

    public List<Ad> findByTag(String tag){
        return em.createQuery("SELECT a FROM Ad a WHERE a.tags LIKE ?1",Ad.class)
                .setParameter(1,"%"+tag+"%")
                .getResultList();
    }



}

