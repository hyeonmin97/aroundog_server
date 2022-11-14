package dogTrio.arounDog.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Coordinate implements Serializable {

    @Id
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_dog_id")
    private UserDog userDog;

    private Double latitude;
    private Double longitude;
    private String tile;
    private Boolean walking;
    private LocalDateTime timeStamp;

    public void endWalking() {
        this.walking = false;
    }

    public void updateCoor(Double latitude, Double longitude, String tile, Boolean walking, LocalDateTime timeStamp) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.tile = tile;
        this.walking = walking;
        this.timeStamp = timeStamp;
    }
}
