package com.residex.debug;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fees")
public class FeeDebugController {

    @GetMapping("/test")
    public String test() {
        return "Fees endpoint accessible";
    }
}
