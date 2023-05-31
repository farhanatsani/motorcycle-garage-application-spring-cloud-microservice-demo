package com.garage.idm.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    @Query("select u from UserEntity u where upper(u.username) = upper(?1)")
    List<UserEntity> findByUsernameIgnoreCase(String username);
}
