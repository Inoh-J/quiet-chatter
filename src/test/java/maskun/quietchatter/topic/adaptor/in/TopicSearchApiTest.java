package maskun.quietchatter.topic.adaptor.in;

import static org.instancio.Instancio.ofList;
import static org.instancio.Select.field;
import static org.mockito.AdditionalAnswers.answer;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.web.util.UriComponentsBuilder.fromPath;

import maskun.quietchatter.topic.application.TopicSearchRequest;
import maskun.quietchatter.topic.application.TopicSearchable;
import maskun.quietchatter.topic.domain.Topic;
import maskun.quietchatter.topic.domain.Topic.Type;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.SliceImpl;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.assertj.MockMvcTester;

@WebMvcTest(controllers = TopicSearchApi.class)
public class TopicSearchApiTest {

    @MockitoBean
    private TopicSearchable topicSearchable;

    @Autowired
    private MockMvcTester tester;

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void searchOk() {
        when(topicSearchable.search(any(TopicSearchRequest.class))).thenAnswer(
                answer((TopicSearchRequest req) -> new SliceImpl<>(ofList(Topic.class).size(req.size())
                        .set(field(Topic::getType), Type.AUTHOR).create()))

        );

        var uriBuilder = fromPath("/api/topics/search").queryParam("keyword", "김영하")
                .queryParam("size", 10)
                .build();

        var result = tester.get().uri(uriBuilder.toUriString())
                .with(csrf())
                .exchange();

        result.assertThat()
                .bodyJson()
                .extractingPath("$.content")
                .asArray()
                .extracting("type")
                .contains("AUTHOR");
    }
}
