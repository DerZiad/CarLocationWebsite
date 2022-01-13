package com.coding.app.models;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import com.coding.app.models.enums.Categorie;
import com.coding.app.models.enums.MarqueVoiture;
import com.coding.app.models.enums.TypePicture;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "voitures")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Voiture {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Enumerated(EnumType.STRING)
	private MarqueVoiture marque;

	@Enumerated(EnumType.STRING)
	private Categorie categorie;

	private int annee;

	private double prix;

	private String fileName;

	@Lob
	@NotNull(message = "On a besoin d'au moins d'une image")
	@JsonIgnore
	private byte[] picture;
	
	@OneToMany(cascade = {CascadeType.ALL},mappedBy = "voiture",targetEntity = Reservation.class)
	private List<Reservation> reservations = new ArrayList<Reservation>();

	public void setPicturePart(MultipartFile file) throws IOException {
		fileName = file.getOriginalFilename();
		byte image[] = file.getBytes();
		if(image != null && image.length != 0) {
			this.picture = image;
		}else {
			this.picture = null;
		}
	}
		
	public String getBase64() {
		String c = "";
		if(this.picture != null && this.picture.length != 0) {
			c = Base64.getEncoder().encodeToString(this.picture);
		}
		return c;
	}

	public static List<Integer> getAnnees() {
		List<Integer> annees = new ArrayList<Integer>();
		for (int i = 1999; i <= 2022; i++) {
			annees.add(i);
		}
		return annees;
	}
}
