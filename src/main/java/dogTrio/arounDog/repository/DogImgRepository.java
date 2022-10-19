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

    public void save(DogImg dogImg) {
        em.persist(dogImg);
    }

    public List<DogImg> findDogImgs(Long id) {
        return em.createQuery("select d from DogImg d join fetch d.userDog where d.userDog.id = :id").setParameter("id", id).getResultList();
    }

    public DogImg findDogImg(Long id) {
        return em.find(DogImg.class, id);
    }

    public Boolean delete(DogImg dogImg) {
        em.remove(dogImg);
        return true;
    }
}
