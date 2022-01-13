package com.coding.app.models.key;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class KeyReservation implements Serializable{
	private static final long serialVersionUID = 1L;

	@Column(name = "idUser")
	private Long idUser;

	@Column(name = "idVoiture")
	private Long idVoiture;

}
