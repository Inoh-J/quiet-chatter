package maskun.quietchatter.hexagon.application;

import lombok.RequiredArgsConstructor;
import maskun.quietchatter.hexagon.application.value.TalkQueryRequest;
import maskun.quietchatter.hexagon.domain.talk.Talk;
import maskun.quietchatter.hexagon.inbound.TalkQueryable;
import maskun.quietchatter.hexagon.outbound.BookRepository;
import maskun.quietchatter.hexagon.outbound.TalkRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class TalkQueryService implements TalkQueryable {
    private final BookRepository bookRepository;
    private final TalkRepository talkRepository;

    @Override
    public Page<Talk> findBy(TalkQueryRequest request) {
        return talkRepository.findByBookIdOrderByCreatedAt(request.bookId(), request.pageRequest());
    }
}
