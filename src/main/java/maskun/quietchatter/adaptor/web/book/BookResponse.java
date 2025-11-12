package maskun.quietchatter.adaptor.web.book;

import java.io.Serializable;
import java.util.UUID;
import maskun.quietchatter.hexagon.domain.book.Book;

/**
 * DTO for {@link Book}
 */
public record BookResponse(
        UUID id,
        String title,
        String isbn,
        String author,
        String thumbnailImageUrl,
        String description,
        String externalLinkUrl) implements Serializable {

    public static BookResponse from(Book book) {
        return new BookResponse(
                book.getId(),
                book.getTitle().value(),
                book.getIsbn().value(),
                book.getAuthor().name(),
                book.getThumbnailImage().url(),
                book.getDescription().value(),
                book.getExternalLink().url()
        );
    }
}
