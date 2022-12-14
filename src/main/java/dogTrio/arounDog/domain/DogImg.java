package dogTrio.arounDog.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class DogImg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_dog_id")
    private UserDog userDog;

    private String path;

    @Column(name = "filename")
    private String fileName;

    public DogImg(UserDog userDog, String path, String fileName) {
        this.userDog = userDog;
        this.path = path;
        this.fileName = fileName;
    }
}

