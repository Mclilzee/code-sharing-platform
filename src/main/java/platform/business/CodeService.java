package platform.business;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import platform.persistence.CodeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CodeService {

    private final CodeRepository codeRepository;

    public CodeService(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }

    public String addCode(Code newCode) {
        Code code = codeRepository.save(newCode);
        return code.getId();
    }

    public Code getCode(String id) {
        Optional<Code> code = codeRepository.findById(id);
        if (code.isEmpty() || code.get().expired()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return codeRepository.save(code.get());
    }

    public List<Code> getLatest() {
        return codeRepository.findAll().stream()
                .filter(code -> !code.isRestricted())
                .sorted((first, second) -> second.getDate().compareTo(first.getDate()))
                .limit(10)
                .toList();
    }
}
