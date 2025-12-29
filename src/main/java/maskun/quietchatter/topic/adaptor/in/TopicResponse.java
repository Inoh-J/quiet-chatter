package maskun.quietchatter.topic.adaptor.in;

import java.util.Objects;
import java.util.UUID;
import maskun.quietchatter.topic.domain.Topic;
import maskun.quietchatter.topic.domain.Topic.Type;

record TopicResponse(
        UUID id,
        Type type,
        String name
) {
    public static TopicResponse from(Topic topic) {
        return new TopicResponse(
                Objects.requireNonNull(topic.getId()),
                topic.getType(),
                topic.getName()
        );
    }
}
