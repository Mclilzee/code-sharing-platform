package platform.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
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

    private final CodeService codeService = new CodeService();

    @ResponseBody
    @PostMapping("/api/code/new")
    public Map<String, String> addCodeInformation(@RequestBody @Valid Code code) {
       long id = codeService.addCode(code);
       Map<String, String> response = new HashMap<>();
       response.put("id", String.valueOf(id));
       return response;
    }

    @GetMapping("/code/{index}")
    public String getHtml(Model model, @PathVariable int index) {
        Code code = codeService.getCode(index);
        model.addAttribute("code", code);
        return "snippet";
    }

    @GetMapping("/code/new")
    public String getNewCodeForm() {
        return "new-snippet";
    }

    @ResponseBody
    @GetMapping("/api/code/{index}")
    public Code getCodeInformation(@PathVariable int index) {
        return codeService.getCode(index);
    }

    @ResponseBody
    @GetMapping("/api/code/latest")
    public List<Code> getLastTenCodeSnippets() {
        return codeService.getLatest(10);
    }

    @GetMapping("/code/latest")
    public String getLatestAsHtml(Model model) {
        model.addAttribute("codes", codeService.getLatest(10));
        return "latest-snippets";
    }

}
