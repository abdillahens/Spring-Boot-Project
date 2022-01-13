package com.immobilier.metiers;

import java.sql.Date;
import java.util.List;

import com.immobilier.entities.Contrat;
import com.immobilier.entities.Message;
import com.immobilier.entities.Reservation;
import com.immobilier.entities.Utilisateur;


public class Client extends Utilisateur{

	public Client(Long id, String nom, String prenom, Date date_naissance, String numero_tele, String cIN, Role role,
			List<Message> messagesRecus, List<Contrat> contrats, List<Reservation> reservations) {
		super(id, nom, prenom, date_naissance, numero_tele, cIN, role, messagesRecus, contrats, reservations);
		// TODO Auto-generated constructor stub
	}

}
