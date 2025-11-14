package maskun.quietchatter.hexagon.outbound;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import maskun.quietchatter.hexagon.domain.member.Member;
import org.springframework.data.repository.Repository;

public interface MemberRepository extends Repository<Member, UUID> {
    Optional<Member> findById(UUID id);

    Member save(Member member);

    default Member require(UUID id) {
        return findById(id).orElseThrow(() ->
                new NoSuchElementException("찾을 수 없음 [%s] id=%s".formatted(Member.class.getSimpleName(), id)));
    }

    ;
}
