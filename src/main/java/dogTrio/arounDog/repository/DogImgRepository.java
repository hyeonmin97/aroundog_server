package dogTrio.arounDog.repository;

import dogTrio.arounDog.domain.DogImg;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class DogImgRepository {
    private final EntityManager em;

    public List<DogImg> findDogImgs(Long id) {
        return em.createQuery("select d from DogImg d join fetch d.userDog where d.userDog.id = :id").setParameter("id", id).getResultList();
    }
}
