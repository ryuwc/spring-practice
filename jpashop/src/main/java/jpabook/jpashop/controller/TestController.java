package jpabook.jpashop.controller;

import jpabook.jpashop.jwt.TokenProvider;
import jpabook.jpashop.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final TokenProvider tokenProvider;

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/test")
    public String test(@RequestHeader("Authorization") String token) {

        Map<String, String> currentUser = SecurityUtil.getCurrentUser();
        System.out.println("====================================");
        System.out.println("currentUser = " + currentUser);
        System.out.println("====================================");
        return "test";
    }
}
