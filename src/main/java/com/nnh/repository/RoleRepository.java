package com.nnh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nnh.entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
	RoleEntity findOneByCode(String code);
}
