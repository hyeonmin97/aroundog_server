package dogTrio.arounDog.repository;

import dogTrio.arounDog.domain.User;
import dogTrio.arounDog.domain.Walk;
import dogTrio.arounDog.dto.WalkDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class WalkRepository {

    private final EntityManager em;

    public void save(Walk walk) {
        em.persist(walk);
    }
    public void save(WalkDto walkDto) {
        Walk walk = walkDto.makeEntity();
        em.persist(walk);
    }
    public Walk findOne(Long id) {
        return em.find(Walk.class, id);
    }


    public List<Walk> orderByGood(int start, int size) {
        return em.createQuery("select w from Walk w order by w.good desc", Walk.class)
                .setFirstResult(start)
                .setMaxResults(size)
                .getResultList();
    }

}
