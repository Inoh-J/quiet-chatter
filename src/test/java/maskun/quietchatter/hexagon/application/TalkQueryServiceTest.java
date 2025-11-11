package maskun.quietchatter.hexagon.application;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;
import maskun.quietchatter.hexagon.application.value.TalkQueryRequest;
import maskun.quietchatter.hexagon.domain.talk.Talk;
import maskun.quietchatter.hexagon.outbound.TalkRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest
@ActiveProfiles("test")
class TalkQueryServiceTest {
    @MockitoBean
    private TalkRepository talkRepository;

    @Autowired
    private TalkQueryService talkQueryService;

    @Test
    @DisplayName("없는 책을 조회시 빈 페이지 반환")
    void findByNotExistingBook() {
        TalkQueryRequest request = new TalkQueryRequest(UUID.randomUUID(), PageRequest.of(0, 10));
        Page<Talk> page = talkQueryService.findBy(request);

        assertThat(page).isEmpty();
    }
}
