package platform.business;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class CodeInformation {

    private final String code;

    private final LocalDateTime date;

    @JsonCreator
    public CodeInformation(String code) {
        this.code = code;
        this.date = LocalDateTime.now();
    }

    public String getDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        return this.date.format(formatter);
    }
}
