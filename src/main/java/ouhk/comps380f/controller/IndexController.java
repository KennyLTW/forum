package ouhk.comps380f.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping
    public String index() {
        //return "redirect:/ticket/list";
        return "redirect:/forum/index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /*
    @GetMapping("/create_acc")
    public String create_acc() {
        return "create_acc";
    }
*/

}
