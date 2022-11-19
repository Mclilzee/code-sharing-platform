package platform.business;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

public class CodeService {

    public List<Code> codes = new ArrayList<>();

    public int addCode(Code code) {
        this.codes.add(code);
        return this.codes.size();
    }

    public Code getCode(int index) {
        try {
            return codes.get(index - 1);
        } catch (IndexOutOfBoundsException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    public List<Code> getLatest(int limit) {
        return codes.stream()
                .sorted((first, second) -> second.getLocalDateTime().compareTo(first.getLocalDateTime()))
                .limit(limit)
                .toList();

    }
}
