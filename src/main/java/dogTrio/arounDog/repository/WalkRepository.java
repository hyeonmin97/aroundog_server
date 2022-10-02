package dogTrio.arounDog.repository;

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
        return em.createQuery("select w from Walk w join fetch w.user order by w.good desc", Walk.class)
                .setFirstResult(start)
                .setMaxResults(size)
                .getResultList();
    }

    public List<Object[]> findWithTile(Long userId, List<String> tiles, int start, int size) {
        return em.createQuery(
                        "select w, g, b " +
                                "from Walk w " +
                                "join fetch w.user " +
                                "left join Good g " +
                                "on g.walk = w " +
                                "and g.user.id = :user " +
                                "left join Bad b " +
                                "on b.walk = w " +
                                "and b.user.id = :user " +
                                "where w.tile in :tiles " +
                                "order by w.good desc, w.bad desc")
                .setParameter("user", userId)
                .setParameter("tiles", tiles)
                .setFirstResult(start)
                .setMaxResults(size)
                .getResultList();
           }

}
