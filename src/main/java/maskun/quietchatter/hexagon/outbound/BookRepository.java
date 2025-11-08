package maskun.quietchatter.hexagon.outbound;

import maskun.quietchatter.hexagon.domain.Book;
import org.bson.types.ObjectId;
import org.springframework.data.repository.Repository;

public interface BookRepository extends Repository<Book, ObjectId> {
    Book findById(ObjectId id);

    Book save(Book book);

    void delete(Book book);
}
