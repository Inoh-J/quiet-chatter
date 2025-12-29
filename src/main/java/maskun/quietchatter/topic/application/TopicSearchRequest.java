package maskun.quietchatter.topic.application;

import org.springframework.util.Assert;

public record TopicSearchRequest(
        String keyword,
        int size,
        int page
) {
    public TopicSearchRequest {
        Assert.hasText(keyword, "keyword must not be empty");
        keyword = keyword.trim();

        Assert.isTrue(size > 0, "size must be positive");
        Assert.isTrue(page >= 0, "page must be positive or zero");
    }

    public static TopicSearchRequest of(String keyword, int size, int page) {
        return new TopicSearchRequest(keyword, size, page);
    }
}
