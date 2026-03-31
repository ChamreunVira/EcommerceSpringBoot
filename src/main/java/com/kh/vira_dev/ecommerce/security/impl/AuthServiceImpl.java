package com.kh.vira_dev.ecommerce.security.impl;

import com.kh.vira_dev.ecommerce.entity.User;
import com.kh.vira_dev.ecommerce.io.request.AuthRequest;
import com.kh.vira_dev.ecommerce.io.request.UserRequest;
import com.kh.vira_dev.ecommerce.io.response.UserResponse;
import com.kh.vira_dev.ecommerce.mapper.AuthMapper;
import com.kh.vira_dev.ecommerce.repository.UserRepository;
import com.kh.vira_dev.ecommerce.security.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final AuthMapper authMapper;
    private final AuthenticationManager authenticationManager;

    @Override
    public UserResponse register(UserRequest request) {
        User user = authMapper.toEntity(request);
        User saved = userRepository.save(user);
        return authMapper.toResponse(saved);
    }

    @Override
    public UserResponse login(AuthRequest request) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(),
                        request.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(auth);
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found with Email: " + request.getEmail()));
        return authMapper.toResponse(user);
    }

    @Override
    public UserResponse create(UserRequest request) {
        return this.register(request);
    }

    @Override
    public UserResponse update(UserRequest request) {
        return null;
    }

    @Override
    public UserResponse me() {
        User user = getAuthenticatedUser();
        return authMapper.toResponse(user);
    }

    @Override
    public Page<UserResponse> getAll(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        return users.map(authMapper::toResponse);
    }

    @Override
    public UserResponse getById(Long id) {
        User user = findByOrThrow(id);
        return authMapper.toResponse(user);
    }

    @Override
    public void delete(Long id) {
        User user = findByOrThrow(id);
        log.info("Delete user: {}", user);
        userRepository.delete(user);
    }

    @Override
    public User getAuthenticatedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        assert(auth != null);
        return userRepository.findByEmail(auth.getName())
                .orElseThrow(() -> new UsernameNotFoundException(auth.getName()));
    }

    private User findByOrThrow(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with ID: " + id));
    }
}
