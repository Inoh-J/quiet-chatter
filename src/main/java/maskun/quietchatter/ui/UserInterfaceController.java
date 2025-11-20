package maskun.quietchatter.ui;

import java.util.UUID;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
class UserInterfaceController {

    @GetMapping("/")
    String redirectToHome() {
        return "redirect:/home";
    }

    @RequestMapping("/home")
    String home() {
        return "home";
    }

    @GetMapping("/books/search")
    String searchBooks() {
        return "book-search-results";
    }

    @SuppressWarnings("unused")
    @GetMapping("/books/{bookId}")
    String bookDetail(@PathVariable UUID bookId) {
        return "book-detail";
    }
}
