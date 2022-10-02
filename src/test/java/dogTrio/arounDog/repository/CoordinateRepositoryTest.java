//package dogTrio.arounDog.repository;
//
//import dogTrio.arounDog.domain.Coordinate;
//import dogTrio.arounDog.domain.UserDog;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.EntityManager;
//import java.util.List;
//
//@SpringBootTest
//class CoordinateRepositoryTest {
//
//    @Autowired
//    CoordinateRepository coordinateRepository;
//
//    @Test
//    @Transactional
//    void findTileAndDog() {
//        //given
//        String tile = "13974:6339";
//        List<UserDog> tileAndDogTest = coordinateRepository.findTileAndDogTest(tile);
//
//        //when
//        System.out.println("find = " + tileAndDogTest);
//
//        //then
//    }
//}