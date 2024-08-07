package com.mduc.gfinternal.controller;

import com.mduc.gfinternal.model.Request.AdminChangePasswordRequest;
import com.mduc.gfinternal.model.Response.ApiResponse;
import com.mduc.gfinternal.model.User;
import com.mduc.gfinternal.model.dto.GetUserDto;
import com.mduc.gfinternal.model.dto.UpdateUserDto;
import com.mduc.gfinternal.model.dto.UserInfoDto;
import com.mduc.gfinternal.service.JwtService;
import com.mduc.gfinternal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Autowired
    public UserController(UserService userService, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok(new ApiResponse(true, "User deleted successfully"));
        } catch (UsernameNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(false, ex.getMessage()));
        }
    }

    @GetMapping("/search")
    public ResponseEntity<Page<GetUserDto>> searchUsersByUsername(
            @RequestParam (defaultValue = "") String username,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "desc") String direction) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.fromString(direction), sortBy);
        Page<GetUserDto> users = userService.searchUsersByUsername(username, pageable);
        return ResponseEntity.ok(users);
    }

//    @PutMapping("/update/{userId}")
//    public ResponseEntity<?> updateUser(@PathVariable Integer userId, @RequestBody UpdateUserDto updateUserDTO) {
//        return userService.updateUser(userId, updateUserDTO);
//    }
//    @GetMapping("/get-user/{id}")
//    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
//        User user = userService.findUserById(id);
//        if (user == null) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(user);
//    }
    @GetMapping("/info")
//    public ResponseEntity<UserInfoDto> getUserInfo() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String username = authentication.getName();
//        try {
//            UserInfoDto userInfoDto = userService.getUserInfoByUsername(username);
//            return new ResponseEntity<>(userInfoDto, HttpStatus.OK);
//        } catch (RuntimeException e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
    @PutMapping("/update-info")
    public ResponseEntity<?> updateUserInfoByUsername(
            @RequestBody UserInfoDto userInfoDto
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        try {
            UserInfoDto updatedUser = userService.updateUserInfoByUsername(username, userInfoDto);
            return ResponseEntity.ok(updatedUser);
        } catch (RuntimeException e) {
            String errorMessage = e.getMessage();
            if (errorMessage.contains("Email is already taken")) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    @PutMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        if (username == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Authorization header missing or invalid");
        }

        boolean result = userService.changePassword(username, changePasswordRequest.getCurrentPassword(), changePasswordRequest.getNewPassword());
        if (result) {
            return ResponseEntity.ok("Password changed successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to change password.");
        }
    }

//    @PostMapping("/change-user-password")
//    public ResponseEntity<String> changePassword(@RequestBody AdminChangePasswordRequest request) {
//        boolean isChanged = userService.adminChangePassword(request.getUsername(), request.getNewPassword());
//        if (isChanged) {
//            return ResponseEntity.ok("Password changed successfully");
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
//        }
//    }

    public static class ChangePasswordRequest {
        private String currentPassword;
        private String newPassword;

        public String getCurrentPassword() {
            return currentPassword;
        }

        public void setCurrentPassword(String currentPassword) {
            this.currentPassword = currentPassword;
        }

        public String getNewPassword() {
            return newPassword;
        }

        public void setNewPassword(String newPassword) {
            this.newPassword = newPassword;
        }
    }
}
