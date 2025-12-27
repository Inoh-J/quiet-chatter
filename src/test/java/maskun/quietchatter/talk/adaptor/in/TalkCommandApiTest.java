package maskun.quietchatter.talk.adaptor.in;

import static org.assertj.core.api.Assertions.assertThat;
import static org.instancio.Instancio.create;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import maskun.quietchatter.TestSecurityConfig;
import maskun.quietchatter.shared.web.WebConfig;
import maskun.quietchatter.talk.application.in.TalkCreatable;
import maskun.quietchatter.talk.domain.Talk;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.assertj.MvcTestResult;
import tools.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = TalkCommandApi.class)
@Import({WebConfig.class, TestSecurityConfig.class})
class TalkCommandApiTest {

    @MockitoBean
    private TalkCreatable talkCreatable;

    @Autowired
    private MockMvcTester tester;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        when(talkCreatable.create(any())).thenReturn(create(Talk.class));
    }

    @Test
    @WithUserDetails(TestSecurityConfig.TEST_GUEST)
    @DisplayName("북톡 등록 성공")
    void post() {
        TalkCreateWebRequest request = create(TalkCreateWebRequest.class);

        //when
        MvcTestResult result = tester.post().uri("/api/talks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
                .exchange();

        //then
        assertThat(result).hasStatusOk()
                .bodyJson()
                .extractingPath("$.id").isNotNull();
    }
}
