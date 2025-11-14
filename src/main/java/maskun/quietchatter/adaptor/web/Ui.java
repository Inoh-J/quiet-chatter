package maskun.quietchatter.adaptor.web;

import java.util.UUID;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Ui {

    @GetMapping("/")
    public String redirectToHome() {
        return "redirect:/home";
    }

    @RequestMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/books/search")
    public String searchBooks() {
        return "book-search-results";
    }

    @GetMapping("/books/{bookId}")
    public String bookDetail(@PathVariable UUID bookId) {
        return "book-detail";
    }
}
