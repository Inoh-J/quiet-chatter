package maskun.quietchatter.adaptor.web.customer;

import lombok.RequiredArgsConstructor;
import maskun.quietchatter.hexagon.inbound.CustomerMessageCreatable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/customer/messages")
@RestController
@RequiredArgsConstructor
public class CustomerMessageApi {
    private final CustomerMessageCreatable customerMessageCreatable;

    @PostMapping
    public ResponseEntity<String> postMessage(@RequestBody Message message) {
        customerMessageCreatable.create(message);
        return ResponseEntity.accepted().build();
    }
}
