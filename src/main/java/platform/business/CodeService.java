package platform.business;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class CodeService {

    public List<Code> codes = new ArrayList<>();

    public void addCode(Code code) {
        this.codes.add(code);
    }

    public Code getCode(int index) {
        try {
            return codes.get(index - 1);
        } catch (IndexOutOfBoundsException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
