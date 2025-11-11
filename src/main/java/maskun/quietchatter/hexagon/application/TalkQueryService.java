package maskun.quietchatter.hexagon.application;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import maskun.quietchatter.hexagon.application.value.TalkQueryRequest;
import maskun.quietchatter.hexagon.domain.book.Book;
import maskun.quietchatter.hexagon.domain.talk.Talk;
import maskun.quietchatter.hexagon.inbound.TalkQueryable;
import maskun.quietchatter.hexagon.outbound.BookRepository;
import maskun.quietchatter.hexagon.outbound.TalkRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class TalkQueryService implements TalkQueryable {
    private final BookRepository bookRepository;
    private final TalkRepository talkRepository;

    @Override
    public Page<Talk> findBy(TalkQueryRequest request) {
        Optional<Book> foundBook = bookRepository.findById(request.bookId());
        if (foundBook.isEmpty()) {
            return getEmptyPage(request);
        }
        Book book = foundBook.get();

        return talkRepository.findByBookOrderByCreatedAt(book, request.pageRequest());
    }

    private static PageImpl<Talk> getEmptyPage(TalkQueryRequest request) {
        return new PageImpl<>(List.of(), request.pageRequest(), 0L);
    }
}
