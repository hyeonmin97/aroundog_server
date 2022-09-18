package dogTrio.arounDog.repository;

import dogTrio.arounDog.domain.Dog;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class DogRepository {

    private final EntityManager em;

    public Dog find(Long id) {
        return em.find(Dog.class, id);
    }
}
