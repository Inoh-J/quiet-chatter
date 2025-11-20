package maskun.quietchatter.shared.web;

import java.util.UUID;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
class Ui {

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

    @GetMapping("/books/{bookId}")
    String bookDetail(@PathVariable UUID bookId) {
        return "book-detail";
    }
}
