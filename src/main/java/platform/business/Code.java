package platform.business;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "codes")
public class Code {

    @Id
    @JsonIgnore
    private String id;

    private String code;

    private int views;
    private long time;

    @JsonIgnore
    private Instant instant;

    @JsonIgnore
    private boolean restricted;

    @JsonCreator
    public Code(String code, int views, long time) {
        this.code = code;
        this.instant = Instant.now();
        this.id = UUID.randomUUID().toString();

        if (views <= 0 && time <= 0) {
            views = 0;
            time = 0;
            restricted = false;
        } else {
            this.views = views;
            this.time = time;
            restricted = true;
        }
    }

    public String getDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        return formatter.format(instant);
    }

    public boolean expired() {
        if (!restricted) {
            return false;
        }

        return outOfTime() || outOfViews();
    }

    private boolean outOfTime() {
        long secondsPassed = Instant.now().getEpochSecond() - instant.getEpochSecond();
        time = time - secondsPassed;
        return time <= 0;
    }

    private boolean outOfViews() {
        boolean outOfViews = views <= 0;
        views--;
        return outOfViews;
    }

    public boolean notRestricted() {
        return !restricted;
    }
}
