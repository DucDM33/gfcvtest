package com.mduc.gfinternal.service;

import com.mduc.gfinternal.converter.UserToGetUserDtoConverter;
import com.mduc.gfinternal.model.User;
import com.mduc.gfinternal.model.dto.GetUserDto;
import com.mduc.gfinternal.model.dto.UpdateUserDto;
import com.mduc.gfinternal.model.dto.UserInfoDto;
import com.mduc.gfinternal.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, UserToGetUserDtoConverter converter, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public Page<GetUserDto> searchUsersByUsername(String username, Pageable pageable) {
        return userRepository.searchUsersByUsername(username, pageable);
    }
    public void deleteUser(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        user.setDelete(true);
        userRepository.save(user);
    }
//    public ResponseEntity<?> updateUser(Integer userId, UpdateUserDto updateUserDTO) {
//        Optional<User> optionalUser = userRepository.findById(userId);
//        if (!optionalUser.isPresent()) {
//            return ResponseEntity.notFound().build();
//        }
//        User user = optionalUser.get();
//        if (updateUserDTO.getPhoneNumber() != null) {
//            user.setPhoneNumber(updateUserDTO.getPhoneNumber());
//        }
//        if (updateUserDTO.getDateOfBirth() != null) {
//            user.setDateOfBirth(updateUserDTO.getDateOfBirth());
//        }
//        if (updateUserDTO.getEmail() != null) {
//            user.setEmail(updateUserDTO.getEmail());
//        }
//        userRepository.save(user);
//        return ResponseEntity.ok().build();
//    }
//    public User findUserById(Integer id) {
//        return userRepository.findUserById(id);
//    }
//    public UserInfoDto getUserInfoByUsername(String username) {
//        Optional<User> userOptional = userRepository.findByUsername(username);
//        User user = userOptional.orElseThrow(() -> new RuntimeException("User not found with username: " + username));
//        return mapUserToUserInfoDto(user);
//    }

    private UserInfoDto mapUserToUserInfoDto(User user) {
        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.setEmail(user.getEmail());
        userInfoDto.setPhoneNumber(user.getPhoneNumber());

        return userInfoDto;
    }
    @Transactional
    public UserInfoDto updateUserInfoByUsername(String username, UserInfoDto userInfoDto) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        User user = userOptional.orElseThrow(() -> new RuntimeException("User not found with username: " + username));
        if (!userInfoDto.getEmail().equals(user.getEmail()) && userRepository.existsByEmail(userInfoDto.getEmail())) {
            throw new RuntimeException("Email is already taken");
        }
        user.setEmail(userInfoDto.getEmail());
        user.setPhoneNumber(userInfoDto.getPhoneNumber());
        userRepository.save(user);
        return mapUserToUserInfoDto(user);
    }
    public boolean changePassword(String username, String currentPassword, String newPassword) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user == null) {
            return false;
        }
        if (!passwordEncoder.matches(currentPassword, user.get().getPassword())) {
            return false;
        }
        user.get().setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user.get());
        return true;
    }
    public boolean adminChangePassword(String username, String newPassword) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user == null) {
            return false;
        }
        user.get().setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user.get());
        return true;
    }

}