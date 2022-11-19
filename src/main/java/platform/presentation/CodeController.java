package platform.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import platform.business.Code;
import platform.business.CodeService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CodeController {

    private final CodeService codeService;

    public CodeController(CodeService codeService) {
        this.codeService = codeService;
    }

    @ResponseBody
    @PostMapping("/api/code/new")
    public Map<String, String> addCodeInformation(@RequestBody @Valid Code code) {
       String id = codeService.addCode(code);
       Map<String, String> response = new HashMap<>();
       response.put("id", id);
       return response;
    }

    @GetMapping("/code/{id}")
    public String getHtml(Model model, @PathVariable String id) {
        Code code = codeService.getCode(id);
        model.addAttribute("code", code);
        return "snippet";
    }

    @GetMapping("/code/new")
    public String getNewCodeForm() {
        return "new-snippet";
    }

    @ResponseBody
    @GetMapping("/api/code/{id}")
    public Code getCodeInformation(@PathVariable String id) {
        return codeService.getCode(id);
    }

    @ResponseBody
    @GetMapping("/api/code/latest")
    public List<Code> getLastTenCodeSnippets() {
        return codeService.getLatest();
    }

    @GetMapping("/code/latest")
    public String getLatestAsHtml(Model model) {
        model.addAttribute("codes", codeService.getLatest());
        return "latest-snippets";
    }

}
