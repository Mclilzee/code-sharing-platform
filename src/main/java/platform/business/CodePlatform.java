package platform.business;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class CodePlatform {
    private String code;

    public String getHtml() {
        return """
                <html>
                    <header>
                        <title>Code</title>
                    </header>
                    <body>
                        <pre>
                            %s
                        </pre>
                    </body>
                </html>
                """.formatted(this.code);
    }

}
