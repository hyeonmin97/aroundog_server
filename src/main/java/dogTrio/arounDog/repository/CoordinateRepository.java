package dogTrio.arounDog.repository;

import dogTrio.arounDog.domain.Coordinate;
import dogTrio.arounDog.domain.User;
import dogTrio.arounDog.domain.UserDog;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CoordinateRepository {

    private final EntityManager em;

    public void save(Coordinate coordinate) {
        em.persist(coordinate);
    }

    public Optional<Coordinate> findByUserDog(UserDog userDog) {
        List<Coordinate> findCoor = em.createQuery("select c from Coordinate c join fetch c.userDog where c.userDog = :userDog", Coordinate.class).setParameter("userDog", userDog).getResultList();

        return findCoor.stream().findAny();
    }

    /**
     * tile값이 일치하고 walking이 true인 Coordinate 리스트 리턴
     *
     * @param tile
     * @return
     */
    public List<Coordinate> findTile(String tile) {
        return em.createQuery("select c from Coordinate c where c.tile = :tile and c.walking = :boolean", Coordinate.class).setParameter("tile", tile).setParameter("boolean", true).getResultList();
    }

    @Transactional
    public List<Object[]> findTileAndDog(String tile) {
        return em.createQuery("select c, d from Coordinate c, UserDog d join fetch d.user join fetch d.dog " +
                "where c.userDog = d " +
                "and c.tile=:tile " +
                "and c.walking = :boolean")
                .setParameter("tile", tile)
                .setParameter("boolean", true)
                .getResultList();
    }

}
