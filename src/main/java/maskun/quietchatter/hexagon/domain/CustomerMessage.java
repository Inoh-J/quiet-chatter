package maskun.quietchatter.hexagon.domain;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import maskun.quietchatter.adaptor.web.customer.Message;

@Entity(name = "customer_message")
public class CustomerMessage extends BaseEntity {

    @AttributeOverride(
            name = "content", column = @Column(name = "message", columnDefinition = "TEXT")
    )
    private Message message;

    public void update(Message message) {
        this.message = message;
    }
}
