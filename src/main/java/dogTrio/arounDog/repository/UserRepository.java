package dogTrio.arounDog.repository;


import dogTrio.arounDog.domain.User;
import dogTrio.arounDog.domain.Walk;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final EntityManager em;

    public void save(User user) {
        em.persist(user);
    }
//    public void save(UserDto userDto) {
//
//        em.persist(userDto);
//    }

    public User findOne(Long id) {
        return em.find(User.class, id);
    }

    public Optional<User> findByUserId(String userId) {
        List<User> members = em.createQuery("select u from User u where u.userId = :userId", User.class).setParameter("userId", userId).getResultList();
        return members.stream().findAny();
    }
    public List<Walk> userWalkList(Long id) {
        return em.createQuery("select w from Walk w where w.id = :id", Walk.class).setParameter("id", id).getResultList();
    }

    public List<User> findByPhone(String phone) {
        return em.createQuery("select u from User u where u.phone = :phone", User.class).setParameter("phone", phone).getResultList();
    }

    public Optional<User> findByEmail(String email) {
        List<User> user = em.createQuery("select u from User u where u.email = :email", User.class).setParameter("email", email).getResultList();
        return user.stream().findAny();
    }

    public List<Object[]> findUserAndDog(String userId) {
        return em.createQuery("select u, d from User u , UserDog d join fetch d.dog where u.userId=:userId and d.user = u").setParameter("userId", userId).getResultList();
    }

    public List<String> findHateDog(String id) {
        return em.createQuery("select u.hateDog from User u where u.userId = :userId").setParameter("userId", id).getResultList();
    }
}
