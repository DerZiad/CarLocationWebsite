package com.coding.app.models;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "histories")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class History {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String action;
	
	private Date date = new Date(System.currentTimeMillis());
	
	public History(String action) {
		this.action = action;
	}
	
}
