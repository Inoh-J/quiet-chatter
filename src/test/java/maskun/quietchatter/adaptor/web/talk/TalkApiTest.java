package maskun.quietchatter.adaptor.web.talk;

import static maskun.quietchatter.hexagon.domain.Fixture.book;
import static maskun.quietchatter.hexagon.domain.Fixture.talk;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.UUID;
import maskun.quietchatter.adaptor.web.WebConfig;
import maskun.quietchatter.hexagon.application.TalkCreateService;
import maskun.quietchatter.hexagon.application.value.TalkCreateRequest;
import maskun.quietchatter.hexagon.domain.book.Book;
import maskun.quietchatter.hexagon.domain.talk.Content;
import maskun.quietchatter.hexagon.domain.talk.Talk;
import net.datafaker.Faker;
import org.instancio.Select;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.assertj.MvcTestResult;

@WebMvcTest(TalkApi.class)
@Import(WebConfig.class)
class TalkApiTest {

    @MockitoBean
    private TalkCreateService talkCreateService;

    @Autowired
    private TalkApi talkApi;

    @Autowired
    private MockMvcTester tester;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("success")
    void post() throws JsonProcessingException {
        Book book = book().asPersisted().create();

        UUID bookId = book.getId();
        String text = new Faker().text().text(100);
        Content content = new Content(text);

        Talk talk = talk().asPersisted(book)
                .set(Select.field(Talk::getContent), content)
                .create();

        when(talkCreateService.create(eq(new TalkCreateRequest(bookId, content))))
                .thenReturn(talk);

        TalkPostRequest request = new TalkPostRequest(bookId, text);

        //when
        MvcTestResult result = tester.post().uri("/api/talks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
                .exchange();

        //then
        assertThat(result).hasStatusOk()
                .bodyJson()
                .extractingPath("$.id").isEqualTo(talk.getId().toString());
    }
}
