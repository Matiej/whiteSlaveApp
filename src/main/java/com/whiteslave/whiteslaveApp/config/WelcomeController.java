package com.whiteslave.whiteslaveApp.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Controller
public class WelcomeController {

    @GetMapping("/sw")
    public String redirectToUi() {
        return "redirect:/swagger-ui.html#/";
    }

    @GetMapping("/hal")
    public String redirectToHal() {
        return "redirect:/browser/index.html#/";
    }
}
