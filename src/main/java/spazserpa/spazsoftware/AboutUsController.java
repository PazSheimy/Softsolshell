package spazserpa.spazsoftware;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutUsController {

    @GetMapping("/aboutus")

    public String home() {
        return "aboutus";
    }

}
