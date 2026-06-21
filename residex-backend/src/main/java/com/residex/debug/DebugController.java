package com.residex.debug;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DebugController {

    @GetMapping("/api/debug/role")
    public Object role(
            Authentication authentication
    ) {
        return authentication.getAuthorities();
    }
}