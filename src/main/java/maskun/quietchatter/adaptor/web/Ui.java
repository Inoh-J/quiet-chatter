package maskun.quietchatter.adaptor.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Ui {

    @GetMapping("/")
    public String redirectToHome() {
        // "/home" URL로 브라우저를 리디렉션시킵니다.
        return "redirect:/home";
    }

    @RequestMapping("/home")
    public String home() {
        return "home";
    }
}
