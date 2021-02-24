package org.vital;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

    @GetMapping("/hello-world")
    public String sayHello(@RequestParam(value = "name", required = false) String name,
                           Model model) {

        model.addAttribute("message", "Name: " + name);

        return "hello_world";
    }
}
