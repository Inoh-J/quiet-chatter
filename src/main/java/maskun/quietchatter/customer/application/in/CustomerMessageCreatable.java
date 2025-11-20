package maskun.quietchatter.customer.application.in;

import maskun.quietchatter.customer.domain.Message;

public interface CustomerMessageCreatable {
    void create(Message message);
}
