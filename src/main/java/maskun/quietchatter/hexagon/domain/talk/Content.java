package maskun.quietchatter.hexagon.domain.talk;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public record Content(
        @Column(name = "content")
        String value
) {
    public static final int MAX_LENGTH = 250;
    static final String ERROR_LENGTH_EXCEEDED = "내용은 %d 자를 초과할 수 없습니다".formatted(MAX_LENGTH);

    public Content {
        if (value.length() > MAX_LENGTH) {
            throw new IllegalArgumentException(ERROR_LENGTH_EXCEEDED);
        }
    }
}
