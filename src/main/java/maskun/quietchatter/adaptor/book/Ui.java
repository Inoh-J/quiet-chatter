package maskun.quietchatter.adaptor.book;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/ui")
@Controller
public class Ui {

    @RequestMapping("/home")
    public String home() {
        return "home";
    }
}
