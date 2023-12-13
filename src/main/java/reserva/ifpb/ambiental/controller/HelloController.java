package reserva.ifpb.ambiental.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/somos")
    public String somos() {
        return "somos";
    }

    @GetMapping("/fauna")
    public String fauna() {
        return "fauna";
    }

    @GetMapping("/contato")
    public String contato() {
        return "contato";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
