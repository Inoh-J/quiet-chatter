package maskun.quietchatter.adaptor.web.naver;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.Assert;

@ConfigurationProperties(prefix = "naver.api")
public class NaverApiEnvironment {
    private final String clientId;
    private final String clientSecret;

    public NaverApiEnvironment(String clientId, String clientSecret) {
        Assert.hasText(clientId, "클라이언트 ID는 비어 있지 않아야 합니다.");
        Assert.hasText(clientSecret, "클라이언트 암호는 비어 있지 않아야 합니다.");

        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }
}
