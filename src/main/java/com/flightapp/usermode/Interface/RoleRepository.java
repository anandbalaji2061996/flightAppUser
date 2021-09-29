package com.flightapp.usermode.Interface;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flightapp.usermode.DAO.ERole;
import com.flightapp.usermode.DAO.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(ERole name);
}