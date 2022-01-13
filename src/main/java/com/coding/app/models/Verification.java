package com.coding.app.models;

import java.util.Random;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.coding.app.models.enums.CodeType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="verifications")
public class Verification {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idVerif;
	
	private String code;
	
	@Enumerated(EnumType.STRING)
	private CodeType type;
	
	@OneToOne(cascade = {CascadeType.DETACH,CascadeType.REFRESH})
	private User user;
	
	private String ip;
	
	private long date = System.currentTimeMillis();
	
	private final static long DELAI = 6 * 60 * 1000;
	
	public boolean isDead() {
		return System.currentTimeMillis() >= (date + DELAI);
	}
	
	public void generateCode() {
		Random rand = new Random();
		this.code=String.valueOf(rand.nextInt(9999));
	}
}
