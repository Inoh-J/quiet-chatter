package maskun.quietchatter.customer.application.out;

import java.util.UUID;
import maskun.quietchatter.customer.domain.CustomerMessage;
import org.springframework.data.repository.Repository;

public interface CustomerMessageRepository extends Repository<CustomerMessage, UUID> {
    CustomerMessage save(CustomerMessage message);
}
