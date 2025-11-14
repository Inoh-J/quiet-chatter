package maskun.quietchatter.hexagon.outbound;

import java.util.UUID;
import maskun.quietchatter.hexagon.domain.talk.Talk;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

public interface TalkRepository extends Repository<Talk, UUID> {
    Talk findById(UUID id);

    Talk save(Talk talk);

    void saveAll(Iterable<Talk> talks);

    Page<Talk> findByBookIdOrderByCreatedAt(UUID bookId, Pageable pageRequest);
}
