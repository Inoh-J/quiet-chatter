package maskun.quietchatter.hexagon.domain;

import java.util.UUID;
import maskun.quietchatter.hexagon.domain.value.BookId;
import maskun.quietchatter.hexagon.domain.value.Title;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "books")
@TypeAlias(value = "book")
public class Book{
    private final BookId id;
    private final Title title;

    private Book(BookId id, Title title) {
        this.id = id;
        this.title = title;
    }
    public static Book newOf(Title title) {
        BookId id = new BookId(UUID.randomUUID());
        return new Book(id, title);
    }

    public BookId getId() {
        return id;
    }
    public Title getTitle() {
        return title;
    }
}
