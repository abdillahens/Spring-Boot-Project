package com.immobilier.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.immobilier.entities.Reservation;

public interface ReservationDAO extends JpaRepository<Reservation, Long>{

}
