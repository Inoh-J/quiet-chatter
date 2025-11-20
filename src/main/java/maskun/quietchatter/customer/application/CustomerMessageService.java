package maskun.quietchatter.customer.application;

import lombok.RequiredArgsConstructor;
import maskun.quietchatter.customer.application.in.CustomerMessageCreatable;
import maskun.quietchatter.customer.application.out.CustomerMessageRepository;
import maskun.quietchatter.customer.domain.CustomerMessage;
import maskun.quietchatter.customer.domain.Message;
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
