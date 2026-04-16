package com.kh.vira_dev.ecommerce.security.impl;

import com.kh.vira_dev.ecommerce.entity.User;
import com.kh.vira_dev.ecommerce.exception.DuplicateResourceException;
import com.kh.vira_dev.ecommerce.io.request.AuthRequest;
import com.kh.vira_dev.ecommerce.io.request.UserRequest;
import com.kh.vira_dev.ecommerce.io.response.UserResponse;
import com.kh.vira_dev.ecommerce.jwt.JwtService;
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
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final AuthMapper authMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    
    @Override
    @Transactional
    public UserResponse register(UserRequest request) {
        if(userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("User email is already in use with Email: " + request.getEmail());
        }
        User user = authMapper.toEntity(request);
        User saved = userRepository.save(user);
        String token = jwtService.generateToken(saved.getEmail());
        UserResponse response = authMapper.toResponse(saved);
        response.setToken(token);
        return response;
    }

    @Override
    public UserResponse login(AuthRequest request) {
        
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(auth);
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found with Email: " + request.getEmail()));
        String token = jwtService.generateToken(user.getEmail());
        UserResponse response = authMapper.toResponse(user);
        response.setToken(token);
        return response;
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
    @Transactional(readOnly = true)
    public Page<UserResponse> getAll(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        return users.map(authMapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
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
