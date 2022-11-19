package platform.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import platform.business.Code;
import platform.business.CodeService;

import javax.validation.Valid;

@Controller
public class CodeController {

    @Autowired
    private CodeService codeService;

    @ResponseBody
    @PostMapping("/api/code/new")
    public void addCodeInformation(@RequestBody @Valid Code code) {
        codeService.addCode(code);
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

}
