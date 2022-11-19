package platform.business;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CodeService {

    public List<Code> codes = new ArrayList<>();

    public void addCode(Code code) {
        this.codes.add(code);
    }
}
