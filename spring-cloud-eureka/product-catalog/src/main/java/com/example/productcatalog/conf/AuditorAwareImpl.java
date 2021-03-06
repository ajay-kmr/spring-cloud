package com.example.productcatalog.conf;


import com.example.productcatalog.dao.entity.User;
import com.example.productcatalog.dao.repository.UserRepository;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * Creating implementation of AuditorAware and override its methods to provide currently logged in user
 */
public class AuditorAwareImpl implements AuditorAware<User> {

    private UserRepository userRepository;

    AuditorAwareImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> getCurrentAuditor() {
        //TODO
//        return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return Optional.empty();
    }
}
