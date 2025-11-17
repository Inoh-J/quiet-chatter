package maskun.quietchatter.hexagon.inbound;

import maskun.quietchatter.adaptor.web.customer.Message;

public interface CustomerMessageCreatable {
    void create(Message message);
}
