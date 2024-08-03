package com.arzumozcan.todolist.audit;

import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

// LOMBOK
@Log4j2

// StereoType
@Component
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    @SuppressWarnings("all")
    public Optional<String> getCurrentAuditor() {
        // Authentication olmuş kullanıcı bilgilerini almak
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        // Eğer kullanıcı sisteme giriş yapmışsa
        if(authentication!=null&&authentication.isAuthenticated()){
            log.warn("Sistemde Kullanıcı adı"+authentication.getName());
            System.out.println("Sistemde Kullanıcı mevcut"+authentication.getName());
            log.warn("Sistemde Kullanıcı bilgileri"+authentication.getPrincipal());
            return Optional.of(authentication.getName());
        }
        //return Optional.empty();
        return Optional.of("ArzumÖzcan");
    }
}