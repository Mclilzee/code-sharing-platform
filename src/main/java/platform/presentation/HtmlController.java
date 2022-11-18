package platform.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import platform.business.CodeInformation;

import javax.validation.Valid;

@Controller
public class HtmlController {

    CodeInformation codeInformation = new CodeInformation("""
            public static void main(String[] args) {
                SpringApplication.run(CodeSharingPlatform.class, args);
            }""");

    @ResponseBody
    @PostMapping("/api/code/new")
    public void addCodeInformation(@RequestBody @Valid CodeInformation codeInformation) {
        this.codeInformation = codeInformation;
    }

    @GetMapping("/code")
    public String getHtml(Model model) {
        model.addAttribute("code", codeInformation);
        return "snippet";
    }

    @ResponseBody
    @GetMapping("/api/code")
    public CodeInformation getCodeInformation() {
        return codeInformation;
    }

}
