package dogTrio.arounDog.repository;

import dogTrio.arounDog.domain.Dog;
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
        return em.createQuery("select u from UserDog u join fetch u.dog where u.user =:user", UserDog.class).setParameter("user", user).getResultList();
    }

    public UserDog find(Long dogId) {
        return em.find(UserDog.class, dogId);
    }

    public List<UserDog> findWithDog(Long dogId) {
        return em.createQuery("select u from UserDog u join fetch u.dog where u.id = :dogId", UserDog.class).setParameter("dogId", dogId).getResultList();
    }
    public void remove(UserDog dog) {
        em.remove(dog);
    }

    public List<UserDog> getDogInfo(List<Long> dogIds) {
        return em.createQuery("select u from UserDog u where u.id in :ids", UserDog.class).setParameter("ids", dogIds).getResultList();
    }
}
