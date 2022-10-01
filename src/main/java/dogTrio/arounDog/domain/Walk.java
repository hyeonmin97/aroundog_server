package dogTrio.arounDog.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter(AccessLevel.PRIVATE)
public class Walk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user")
    private User user;

    @Lob
    private String course;
    private String courseCenter;
    private int good;
    private int bad;
    private String img;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Walk() {

    }

    public void setUser(User user) {
        this.user = user;
    }

    public Walk(User user, String course, String courseCenter, int good, int bad, String img, LocalDateTime startTime, LocalDateTime endTime) {
        this.user = user;
        this.course = course;
        this.courseCenter = courseCenter;
        this.good = good;
        this.bad = bad;
        this.img = img;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public void clickButton(String button) {
        if ("good".equals(button)) {
            this.good += 1;
        } else {
            this.bad += 1;
        }
    }
}
