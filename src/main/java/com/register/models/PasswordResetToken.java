package com.register.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.ForeignKey;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class PasswordResetToken {

	// expirationTime 10 mins
	private static final int EXPIRATION_TIME = 10;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private  Long id;
	private String token;
	private Date expirationTime;
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="user_id", 
	nullable=false,
	foreignKey=@ForeignKey(name= "FK_USER_PASSWORD_TOKEN"))
	private ApplicationUser user;
	
	public PasswordResetToken(String token, Date expirationTime, ApplicationUser user) {
		super();
		this.token = token;
		this.expirationTime = expirationTime;
		this.user = user;
	}


	
	
}
