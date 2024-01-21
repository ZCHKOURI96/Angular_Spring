package com.gestion.formation.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion.formation.entity.User;




@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long>     {
	public  Participant  findById(long id);
	boolean existsByEmail(String email);
	
	
}
