package maskun.quietchatter.talk.application.in;

import java.util.List;
import maskun.quietchatter.talk.domain.Talk;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.Page;

public interface TalkQueryable {
    Page<Talk> findBy(TalkQueryRequest request);

    List<Talk> getRecent(Limit limit);
}
