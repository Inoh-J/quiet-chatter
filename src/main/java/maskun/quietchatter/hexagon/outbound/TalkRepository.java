package maskun.quietchatter.hexagon.outbound;

import java.util.List;
import java.util.UUID;
import maskun.quietchatter.hexagon.domain.book.Book;
import maskun.quietchatter.hexagon.domain.talk.Talk;
import org.springframework.data.repository.Repository;

public interface TalkRepository extends Repository<Talk, UUID> {
    Talk findById(UUID id);

    Talk save(Talk talk);

    void deleteById(UUID id);

    List<Talk> findByBookOrderByCreatedAt(Book book);
}
