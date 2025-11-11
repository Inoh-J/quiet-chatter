package maskun.quietchatter.hexagon.inbound;

import maskun.quietchatter.hexagon.application.value.TalkQueryRequest;
import maskun.quietchatter.hexagon.domain.talk.Talk;
import org.springframework.data.domain.Page;

public interface TalkQueryable {
    Page<Talk> findBy(TalkQueryRequest request);
}
