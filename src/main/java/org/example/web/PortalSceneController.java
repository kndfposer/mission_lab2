package org.example.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PortalSceneController {
    @GetMapping({"/", "/console-ui"})
    public String portal() {
        return "forward:/dashboard.html";
    }
}
