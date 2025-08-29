package com.coding.app.models.key;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

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
