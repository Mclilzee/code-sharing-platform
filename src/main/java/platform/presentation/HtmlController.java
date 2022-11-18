package platform.presentation;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import platform.business.CodeInformation;

@RestController
public class HtmlController {

    CodeInformation platform = new CodeInformation("""
            public static void main(String[] args) {
                SpringApplication.run(CodeSharingPlatform.class, args);
            }""");

    @GetMapping(value = "/code", produces = MediaType.TEXT_HTML_VALUE)
    public String getHtml() {
        return platform.getHtml();
    }

    @GetMapping("/api/code")
    public CodeInformation getCodeInformation() {
        return platform;
    }
}
