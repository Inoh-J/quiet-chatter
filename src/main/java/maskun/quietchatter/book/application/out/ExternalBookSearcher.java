package maskun.quietchatter.book.application.out;

import maskun.quietchatter.book.application.in.Keyword;
import maskun.quietchatter.book.domain.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ExternalBookSearcher {
    Page<Book> findByKeyword(Keyword keyword, Pageable pageRequest);
}
