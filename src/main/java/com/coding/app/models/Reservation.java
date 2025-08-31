package com.coding.app.models;

import com.coding.app.models.key.KeyReservation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name="reservations")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Reservation implements Serializable{

	@EmbeddedId
	private KeyReservation id;

	@Column
	boolean isConfirmed = false;
	
	private long dateCreation = System.currentTimeMillis();
	
	private long delai;
			
	/**
	 * Relations
	 * */
	
	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST })
	@MapsId("idUser")
	private User user;
	
	
	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST })
	@MapsId("idVoiture")
	@JsonIgnore
	private Voiture voiture;
			
	public boolean isDead() {
		long dateNow = System.currentTimeMillis();
		if(dateNow >= (dateCreation + delai))
			return true;
		else
			return false;
	}
}
