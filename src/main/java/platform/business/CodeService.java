package platform.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

public class CodeService {

    @Autowired
    private CodeRepository codeRepository;

    public long addCode(Code newCode) {
        Code code = codeRepository.save(newCode);
        return code.getId();
    }

    public Code getCode(long id) {
        Optional<Code> code = codeRepository.findById(id);
        if (code.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        return code.get();
    }

    public List<Code> getLatest(int limit) {
        return codeRepository.findFirst10ByOrderByLocalDateTimeDesc();
    }
}
