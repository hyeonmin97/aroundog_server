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

    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;

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
        this.userId = user;
    }

    public Walk(User userId, String course, String courseCenter, int good, int bad, String img, LocalDateTime startTime, LocalDateTime endTime) {
        this.userId = userId;
        this.course = course;
        this.courseCenter = courseCenter;
        this.good = good;
        this.bad = bad;
        this.img = img;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
