package platform.business;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Duration;
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
    private boolean viewsRestricted;

    @JsonIgnore
    private boolean timeRestricted;

    @JsonCreator
    public Code(String code, int views, long time) {
        this.code = code;
        this.localDateTime = LocalDateTime.now();
        this.id = UUID.randomUUID().toString();
        this.views = views;
        this.time = time;

        if (views <= 0) {
            this.views = 0;
            this.viewsRestricted = false;
        } else {
            this.viewsRestricted = true;
        }

        if (time <= 0) {
            this.time = 0;
            this.timeRestricted = false;
        } else {
            this.timeRestricted = true;
        }
    }

    public String getDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")
                .withLocale(Locale.getDefault())
                .withZone(ZoneId.systemDefault());
        return formatter.format(localDateTime);
    }

    public long getTime() {
        long secondsRemaining = time - getSecondsPassed();
        return secondsRemaining > 0 ? secondsRemaining : 0;
    }

    public boolean expired() {
        if (!isRestricted()) {
            return false;
        }

        return outOfTime() || outOfViews();
    }

    private boolean outOfTime() {
        if (!timeRestricted) {
            return false;
        }

        return getSecondsPassed() > time;
    }

    private boolean outOfViews() {
        if (!viewsRestricted) {
            return false;
        }

        boolean outOfViews = views <= 0;
        views--;
        return outOfViews;
    }

    private long getSecondsPassed() {
        Duration secondsPassed = Duration.between(localDateTime, LocalDateTime.now());
        return secondsPassed.getSeconds();
    }

    @JsonIgnore
    public boolean isRestricted() {
        return this.timeRestricted || this.viewsRestricted;
    }
}
