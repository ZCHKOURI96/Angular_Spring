package com.gestion.formation.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion.formation.entity.User;


@Repository

public interface FormationRepository extends JpaRepository <Formation, Long> {

	boolean existsById(Long id);
}
