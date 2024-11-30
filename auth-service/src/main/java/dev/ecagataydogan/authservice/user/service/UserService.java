package dev.ecagataydogan.authservice.user.service;

import dev.ecagataydogan.authservice.common.exception.BusinessException;
import dev.ecagataydogan.authservice.common.exception.ErrorCode;
import dev.ecagataydogan.authservice.user.entity.User;
import dev.ecagataydogan.authservice.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.not_found, "User not found"));
    }
}
