package com.immobilier.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.immobilier.entities.Contrat;

public interface ContratDAO extends JpaRepository<Contrat, Long>{

}
