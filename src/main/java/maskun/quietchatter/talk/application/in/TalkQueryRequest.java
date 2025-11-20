package maskun.quietchatter.talk.application.in;

import java.util.UUID;
import org.springframework.data.domain.Pageable;
import org.springframework.util.Assert;

public record TalkQueryRequest(
        UUID bookId,
        Pageable pageRequest
) {
    public TalkQueryRequest {
        Assert.notNull(bookId, "bookId는 null이 아니어야 합니다.");
        Assert.notNull(pageRequest, "pageRequest는 null이 아니어야 합니다");
    }
}
