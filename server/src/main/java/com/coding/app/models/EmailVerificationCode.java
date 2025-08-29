package com.coding.app.models;

import com.coding.app.models.enums.CodeType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Random;

@Entity
@Table(name="verifications")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailVerificationCode {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
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
