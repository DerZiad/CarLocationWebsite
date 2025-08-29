package com.coding.app.models;

import com.coding.app.models.enums.ServerRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails, Serializable, Comparable<User> {

    @Id
	@Column(name = "username", unique = true, length = 50, nullable = false)
	@NotNull(message = "Le nom d'utilisateur ne doit pas être vide")
	private String username;

	@Column(name = "password", length = 100, nullable = false)
	@Length(min = 8, max = 100, message = "Veuillez respecter les contraintes pour valider votre password")
	@NotEmpty(message = "Le password ne doit pas être vide")
	@JsonIgnore
	private String password;

	@Email(message ="Must be a email valid")
	@NotNull(message="Email ne doit pas etre vide")
	private String email;
	
	private String roles = "";
	
	@OneToOne(cascade = {CascadeType.ALL},mappedBy = "user",targetEntity = EmailVerificationCode.class)
	private EmailVerificationCode emailVerificationCodeEmail;
	
	@OneToOne(cascade = {CascadeType.ALL},mappedBy = "user",targetEntity = EmailVerificationCode.class)
	private EmailVerificationCode emailVerificationCodeRecover;
	
	private boolean accountNonExpired = true;
	private boolean accountNonLocked = true;
	private boolean credentialsNonExpired = true;
	private boolean enabled = true;
	private boolean verificated = false;
	
	@OneToMany(cascade = {CascadeType.ALL},mappedBy = "user",targetEntity = Reservation.class)
	private List<Reservation> reservations = new ArrayList<Reservation>();

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (String role : roles.split(";")) {
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
		}
		return authorities;
	}

	public void addRole(ServerRole role) {
		roles = roles + role.getRole() + ";";
	}

	public void removeRole(ServerRole role) {
		String rolesTemp = roles;
		roles = "";
		for (String roleString : rolesTemp.split(";")) {
			if (roleString.equals(role.getRole()))
				continue;
			else
				roles = roles + roleString + ";";
		}
	}

	@Override
	public int compareTo(User o) {
		return username.compareTo(o.getUsername());
	}

}
