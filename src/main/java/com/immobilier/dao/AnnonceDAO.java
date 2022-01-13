package com.immobilier.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.immobilier.entities.Annonce;

public interface AnnonceDAO extends JpaRepository<Annonce, Long>{

}
