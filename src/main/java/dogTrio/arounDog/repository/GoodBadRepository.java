package dogTrio.arounDog.repository;

import dogTrio.arounDog.domain.Button;
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
}
