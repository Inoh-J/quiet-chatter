package maskun.quietchatter.book.adaptor.out;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.Assert;

@ConfigurationProperties(prefix = "naver.api")
class NaverApiEnvironment {
    private final String clientId;
    private final String clientSecret;

    NaverApiEnvironment(String clientId, String clientSecret) {
        Assert.hasText(clientId, "클라이언트 ID는 비어 있지 않아야 합니다.");
        Assert.hasText(clientSecret, "클라이언트 암호는 비어 있지 않아야 합니다.");

        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    String getClientId() {
        return clientId;
    }

    String getClientSecret() {
        return clientSecret;
    }
}
