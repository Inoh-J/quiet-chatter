package maskun.quietchatter.hexagon.domain.book;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import maskun.quietchatter.hexagon.domain.BaseEntity;

@Entity(name = "book")
@Table(indexes = @Index(columnList = "isbn", name = "idx_book_isbn"))
public class Book extends BaseEntity {

    @Embedded
    private Title title;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "isbn"))
    private Isbn isbn;

    public static Book newOf(Title title, Isbn isbn) {
        Book book = new Book();
        book.title = title;
        book.isbn = isbn;
        return book;
    }

    public Title getTitle() {
        return title;
    }

    public Isbn getIsbn() {
        return isbn;
    }

    public void update(Title title) {
        this.title = title;
    }

    public void update(Isbn isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + getId() + ", " +
                "isbn = " + getIsbn() + ", " +
                "title = " + getTitle() + ", " +
                "createdAt = " + getCreatedAt() + ")";
    }
}
