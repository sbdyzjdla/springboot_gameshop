package com.gameshop.service;

import com.gameshop.domain.user.User;
import com.gameshop.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * UserService - 사용자 정보 서비스
 */

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    /**
     * 사용자 - 사용자 조회
     * @param id
     * @return
     */

    @Transactional(readOnly = true)
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저정보를 찾을수 없습니다."));
    }

}
