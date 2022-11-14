package dogTrio.arounDog.repository;

import dogTrio.arounDog.domain.Walk;
import dogTrio.arounDog.dto.WalkDto;
import dogTrio.arounDog.dto.WalkWeekDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
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

    public List<WalkWeekDto> findWeekData(String userId, LocalDateTime startDate, LocalDateTime endDate) {
        return em.createQuery("select new dogTrio.arounDog.dto.WalkWeekDto(sum(w.second), sum(w.distance), count(w)) from Walk w left join w.user u where  w.user.userId = :userId and w.endTime between :startDate and :endDate", WalkWeekDto.class)
                .setParameter("userId", userId).setParameter("startDate", startDate).setParameter("endDate", endDate)
                .getResultList();
    }

    public List<Walk> findById(String userId) {
        return em.createQuery("select w from Walk w join fetch w.user where w.user.userId = :userId order by w.id asc").setParameter("userId", userId).getResultList();
    }

    public void remove(Walk walk) {
        em.remove(walk);
    }
}
