package dogTrio.arounDog.repository;

import dogTrio.arounDog.domain.Walk;
import dogTrio.arounDog.domain.WalkDeduplication;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class DeduplicationWalkRepository {
    private final EntityManager em;

    public WalkDeduplication findOne(Long id) {
        return em.find(WalkDeduplication.class, id);
    }

    public List<Object[]> findWithTile(Long userId, List<String> tiles, int start, int size) {
        return em.createQuery(
                        "select w, g, b " +
                                "from WalkDeduplication w " +
                                "left join Good g " +
                                "on g.deWalk = w " +
                                "and g.user.id = :user " +
                                "left join Bad b " +
                                "on b.deWalk = w " +
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
