package platform.presentation;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import platform.business.CodeInformation;

import javax.validation.Valid;

@RestController
public class HtmlController {

    CodeInformation codeInformation = new CodeInformation("""
            public static void main(String[] args) {
                SpringApplication.run(CodeSharingPlatform.class, args);
            }""");

    @PostMapping("/api/code/new")
    public void addCodeInformation(@RequestBody @Valid CodeInformation codeInformation) {
        this.codeInformation = codeInformation;
    }
    @GetMapping(value = "/code", produces = MediaType.TEXT_HTML_VALUE)
    public String getHtml() {
        return codeInformation.getHtml();
    }

    @GetMapping("/api/code")
    public CodeInformation getCodeInformation() {
        return codeInformation;
    }

}
