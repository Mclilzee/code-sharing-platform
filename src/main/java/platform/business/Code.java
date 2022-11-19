package platform.business;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class Code {

    private String code;

    private LocalDateTime date;

    @JsonCreator
    public Code(String code) {
        this.code = code;
        this.date = LocalDateTime.now();
    }

    public String getDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        return this.date.format(formatter);
    }

    public void setCode(String code) {
        this.code = code;
        this.date = LocalDateTime.now();
    }
}
