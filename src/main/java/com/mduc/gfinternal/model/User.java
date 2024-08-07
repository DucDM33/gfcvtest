package com.mduc.gfinternal.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.mduc.gfinternal.model.dto.UserInfoDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "user")
public class User extends Auditable implements UserDetails {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Setter
    @Getter
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "username", unique = true)
    private String username;
    @Column(name = "password")
    private String password;
    @Getter
    @Setter
    @Column(name = "email", unique = true)
    private String email;
    @Getter
    @Setter
    @Column(name = "is_delete")
    private boolean isDelete;
    @Getter
    @Setter
    @Column(name = "phone_number")
    private String phoneNumber;
    @Setter
    @Getter
    @Column(name = "department")
    private String department;
    @Setter
    @Getter
    @Column(name = "position")
    private String position;
    @Getter
    @Enumerated(value = EnumType.STRING)
    private Role role;
    @OneToMany(mappedBy = "user")
    @Setter
    @Getter
    private List<Token> tokens;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return username;
    }

    public User() {
        // Constructor mặc định không tham số
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public User(Integer id, String department, String username, boolean isDelete, String phoneNumber, Role role) {
        this.id = id;

        this.department = department;
        this.username = username;
        this.isDelete = isDelete;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    public UserInfoDto getUserInfo() {
        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.setPhoneNumber(this.phoneNumber);
        return userInfoDto;
    }

}
