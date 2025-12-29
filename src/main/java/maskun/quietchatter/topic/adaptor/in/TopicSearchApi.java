package maskun.quietchatter.topic.adaptor.in;

import lombok.RequiredArgsConstructor;
import maskun.quietchatter.topic.application.TopicSearchRequest;
import maskun.quietchatter.topic.application.TopicSearchable;
import maskun.quietchatter.topic.domain.Topic;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/topics")
class TopicSearchApi {
    private final TopicSearchable topicSearchable;

    Slice<TopicResponse> search(@RequestParam(name = "keyword") String keyword,
                                @PageableDefault(size = 10) Pageable pageable) {

        Slice<Topic> topics = topicSearchable.search(
                TopicSearchRequest.of(keyword, pageable.getPageSize(), pageable.getPageNumber()));
        return topics.map(TopicResponse::from);
    }
}
