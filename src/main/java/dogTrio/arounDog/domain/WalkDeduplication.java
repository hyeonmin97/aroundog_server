package dogTrio.arounDog.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class WalkDeduplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String rdp;
    private String hash;
    private String img;
    private String tile;
    private Long second;
    private Long distance;
    private int good;
    private int bad;
    private String address;

    public void clickButton(String button) {
        if ("good".equals(button)) {
            this.good += 1;
        } else {
            this.bad -= 1;
        }
    }
}
