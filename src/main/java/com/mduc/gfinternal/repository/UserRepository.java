package com.mduc.gfinternal.repository;

import com.mduc.gfinternal.model.User;
import com.mduc.gfinternal.model.dto.GetUserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    @Query("SELECT NEW com.mduc.gfinternal.model.dto.GetUserDto(u.id, u.fullName, u.email, u.username, u.isDelete, u.phoneNumber, u.role) FROM User u WHERE u.username LIKE %:username%")
    Page<GetUserDto> searchUsersByUsername(@Param("username") String username, Pageable pageable);
//    @Query("SELECT new com.mduc.gfinternal.model.User(u.id, u.fullName, u.email, u.username, u.isDelete, u.phoneNumber, u.role) " +
//            "FROM User u WHERE u.id = ?1")
//    User findUserById(Integer id);
    boolean existsByEmail(String email);
}
