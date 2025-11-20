package maskun.quietchatter.book.application.in;

import java.util.List;
import java.util.UUID;
import maskun.quietchatter.book.domain.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookQueryable {

    Book findBy(UUID bookId);

    Page<Book> findBy(Keyword keyword, Pageable pageRequest);

    List<Book> findBy(List<UUID> bookIds);
}
