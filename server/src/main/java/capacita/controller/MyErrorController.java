package capacita.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyErrorController implements ErrorController {

    @Override
    @RequestMapping("/error")
    public String getErrorPath() {
        return "Unknown error occurred";
    }
}
