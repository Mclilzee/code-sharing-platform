package platform.business;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class Code {

    private String code;

    @JsonIgnore
    private LocalDateTime localDateTime;

    @JsonCreator
    public Code(String code) {
        this.code = code;
        this.localDateTime = LocalDateTime.now();
    }

    public String getDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        return this.localDateTime.format(formatter);
    }

    public void setCode(String code) {
        this.code = code;
        this.localDateTime = LocalDateTime.now();
    }
}
