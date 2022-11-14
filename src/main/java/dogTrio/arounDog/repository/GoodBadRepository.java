package dogTrio.arounDog.repository;

import dogTrio.arounDog.domain.Button;
import dogTrio.arounDog.domain.Good;
import dogTrio.arounDog.domain.Walk;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class GoodBadRepository {
    private final EntityManager em;

    public void save(Button button) {
        em.persist(button);
    }

    public void deleteGood(Walk walk) {
        em.createQuery("delete from Good g  where g.walk = :walk").setParameter("walk", walk).executeUpdate();
    }
    public void deleteBad(Walk walk) {
        em.createQuery("delete from Bad b  where b.walk = :walk").setParameter("walk", walk).executeUpdate();
    }

}
