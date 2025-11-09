package maskun.quietchatter.hexagon.application;

import maskun.quietchatter.hexagon.application.value.Keyword;
import maskun.quietchatter.hexagon.domain.Book;
import maskun.quietchatter.hexagon.inbound.BookQueryable;
import maskun.quietchatter.hexagon.outbound.ExternalBookSearcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class BookQueryService implements BookQueryable {
    private final ExternalBookSearcher externalBookSearcher;

    public BookQueryService(ExternalBookSearcher externalBookSearcher) {
        this.externalBookSearcher = externalBookSearcher;
    }

    @Override
    public Page<Book> findBy(Keyword keyword, PageRequest pageRequest) {
        return externalBookSearcher.findByKeyword(keyword, pageRequest);
    }
}
