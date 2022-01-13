package com.coding.app.models;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.coding.app.models.key.KeyReservation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="reservations")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Reservation implements Serializable{

	@EmbeddedId
	private KeyReservation id;
	
	private static final long serialVersionUID = 1L;
	
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
	private Voiture voiture;
			
	public boolean isDead() {
		long dateNow = System.currentTimeMillis();
		if(dateNow >= (dateCreation + delai))
			return true;
		else
			return false;
	}
}
