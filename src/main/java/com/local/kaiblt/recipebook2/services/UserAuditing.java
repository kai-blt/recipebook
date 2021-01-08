package com.local.kaiblt.recipebook2.services;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserAuditing implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        String uname;

        //Check if user is an authenticated user, if so use their name for auditing fields
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();

        if (authentication != null) {
            uname = authentication.getName();
        } else {
            uname = "SYSTEM";
        }

        return Optional.of(uname);
    }
}
