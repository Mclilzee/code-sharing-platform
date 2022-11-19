package platform.business;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
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
    private LocalDateTime localDateTime;

    @JsonIgnore
    private boolean restricted;

    @JsonCreator
    public Code(String code, int views, long time) {
        this.code = code;
        this.localDateTime = LocalDateTime.now();
        this.id = UUID.randomUUID().toString();
        this.views = views;
        this.time = time;

        if (views < 0) {
            this.views = 0;
        }

        if (time < 0) {
            this.time = 0;
        }

        restricted = views > 0 || time > 0;
    }

    public String getDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")
                .withLocale(Locale.getDefault())
                .withZone(ZoneId.systemDefault());
        return formatter.format(localDateTime);
    }

    public boolean expired() {
        if (!restricted) {
            return false;
        }

        return outOfTime() || outOfViews();
    }

    private boolean outOfTime() {
        Duration secondsPassed = Duration.between(localDateTime, LocalDateTime.now());
        time = time - secondsPassed.getSeconds();
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
