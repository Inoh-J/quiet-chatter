package maskun.quietchatter.hexagon.application;

import lombok.RequiredArgsConstructor;
import maskun.quietchatter.adaptor.web.customer.Message;
import maskun.quietchatter.hexagon.domain.CustomerMessage;
import maskun.quietchatter.hexagon.inbound.CustomerMessageCreatable;
import maskun.quietchatter.hexagon.outbound.CustomerMessageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomerMessageService implements CustomerMessageCreatable {
    private final CustomerMessageRepository customerMessageRepository;

    @Override
    @Transactional
    public void create(Message message) {
        CustomerMessage customerMessage = new CustomerMessage();
        customerMessage.update(message);
        customerMessageRepository.save(customerMessage);
    }
}
