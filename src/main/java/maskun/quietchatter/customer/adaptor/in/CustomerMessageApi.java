package maskun.quietchatter.customer.adaptor.in;

import lombok.RequiredArgsConstructor;
import maskun.quietchatter.customer.application.in.CustomerMessageCreatable;
import maskun.quietchatter.customer.domain.Message;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/customer/messages")
@RestController
@RequiredArgsConstructor
class CustomerMessageApi {
    private final CustomerMessageCreatable customerMessageCreatable;

    @PostMapping
    ResponseEntity<String> postMessage(@RequestBody Message message) {
        customerMessageCreatable.create(message);
        return ResponseEntity.accepted().build();
    }
}
