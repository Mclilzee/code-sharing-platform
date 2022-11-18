package platform.presentation;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import platform.business.CodePlatform;

@RestController
public class HtmlController {

    CodePlatform platform = new CodePlatform("""
            public static void main(String[] args) {
                SpringApplication.run(CodeSharingPlatform.class, args);
            }""");

    @GetMapping(value = "/code", produces = MediaType.TEXT_HTML_VALUE)
    public String getCode() {
        return platform.getHtml();
    }
}
