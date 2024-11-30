package dev.ecagataydogan.authservice.user.service;


import dev.ecagataydogan.authservice.user.entity.User;
import dev.ecagataydogan.authservice.user.entity.UserDetailsImpl;
import dev.ecagataydogan.authservice.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException {
        User user;
        user = userRepository.findByEmail(identifier)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with identifier: " + identifier));
        return new UserDetailsImpl(user);
    }
}
