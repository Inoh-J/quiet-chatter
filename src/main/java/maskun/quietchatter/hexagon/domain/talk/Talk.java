package maskun.quietchatter.hexagon.domain.talk;

import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import maskun.quietchatter.hexagon.domain.BaseEntity;
import maskun.quietchatter.hexagon.domain.book.Book;

@Entity(name = "talk")
@Table(indexes = @Index(columnList = "book_id"))
public class Talk extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "book_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Book book;

    @Embedded
    private Content content;

    public void update(Book book) {
        this.book = book;
    }

    public void update(Content content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + getId() + ", " +
                "book = " + getBook() + ", " +
                "content = " + getContent() + ", " +
                "createdAt = " + getCreatedAt() + ")";
    }

    public Book getBook() {
        return book;
    }

    public Content getContent() {
        return content;
    }
}
