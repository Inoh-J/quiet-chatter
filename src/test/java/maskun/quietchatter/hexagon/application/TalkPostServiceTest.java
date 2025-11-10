package maskun.quietchatter.hexagon.application;

import maskun.quietchatter.hexagon.outbound.BookRepository;
import maskun.quietchatter.hexagon.outbound.TalkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest
@ActiveProfiles("test")
class TalkPostServiceTest {
    @MockitoBean
    private TalkRepository talkRepository;

    @MockitoBean
    private BookRepository bookRepository;

    @Autowired
    private TalkCreateService talkCreateService;
}
