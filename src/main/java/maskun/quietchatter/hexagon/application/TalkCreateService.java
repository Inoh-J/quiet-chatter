package maskun.quietchatter.hexagon.application;

import maskun.quietchatter.hexagon.application.value.TalkCreateRequest;
import maskun.quietchatter.hexagon.domain.book.Book;
import maskun.quietchatter.hexagon.domain.talk.Talk;
import maskun.quietchatter.hexagon.inbound.TalkCreatable;
import maskun.quietchatter.hexagon.outbound.BookRepository;
import maskun.quietchatter.hexagon.outbound.TalkRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TalkCreateService implements TalkCreatable {
    private final BookRepository bookRepository;
    private final TalkRepository talkRepository;

    public TalkCreateService(BookRepository bookRepository, TalkRepository talkRepository) {
        this.bookRepository = bookRepository;
        this.talkRepository = talkRepository;
    }

    @Override
    @Transactional
    public Talk create(TalkCreateRequest request) {
        Book book = bookRepository.require(request.bookId());
        Talk talk = make(book, request);
        return talkRepository.save(talk);
    }

    private static Talk make(Book book, TalkCreateRequest request) {
        Talk talk = new Talk();

        talk.update(book);
        talk.update(request.content());

        return talk;
    }
}
