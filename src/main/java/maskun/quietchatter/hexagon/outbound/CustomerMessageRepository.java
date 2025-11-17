package maskun.quietchatter.hexagon.outbound;

import java.util.UUID;
import maskun.quietchatter.hexagon.domain.CustomerMessage;
import org.springframework.data.repository.Repository;

public interface CustomerMessageRepository extends Repository<CustomerMessage, UUID> {
    CustomerMessage save(CustomerMessage message);
}
