package com.immobilier.dao;

import java.util.List;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.immobilier.entities.Compte;
import com.immobilier.entities.Utilisateur;

@Repository
public interface CompteDAO extends JpaRepository<Compte, Long>{

	@Query(value = "select c from Compte c where c.email = :email")
	List<Compte> findUserByStatusAndName(@Param("email") String email);
	
}
