package dogTrio.arounDog.repository;

import dogTrio.arounDog.domain.User;
import dogTrio.arounDog.domain.UserDog;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserDogRepository {

    private final EntityManager em;

    public void save(UserDog userDog) {
        em.persist(userDog);
    }

    public List<UserDog> findByUserId(User user) {
        return em.createQuery("select u from UserDog u where u.user =:user", UserDog.class).setParameter("user", user).getResultList();
    }
}
