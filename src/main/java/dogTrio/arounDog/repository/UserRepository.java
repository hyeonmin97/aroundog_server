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

    public List<Walk> userWalkList(Long id) {
        return em.createQuery("select w from Walk w where w.userId = :id", Walk.class).setParameter("id", id).getResultList();
    }

    public List<User> findByPhone(String phone) {
        return em.createQuery("select u from User u where u.phone = :phone", User.class).setParameter("phone", phone).getResultList();
    }
}
