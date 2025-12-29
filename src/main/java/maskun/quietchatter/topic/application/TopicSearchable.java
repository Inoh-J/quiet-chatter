package maskun.quietchatter.topic.application;

import maskun.quietchatter.topic.domain.Topic;
import org.springframework.data.domain.Slice;

public interface TopicSearchable {

    Slice<Topic> search(TopicSearchRequest request);
}
