package com.adsecure.webapp.repositories.entities;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
@Data
@Builder
public class UserEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "fname")
	private String fname;
	
	@Column(name = "lname")
	private String lname;
	
    @Column(name = "password", nullable = false)
    private String password;
	
	@Column(name = "gender")
	private String gender;
	
	@Column(name = "phoneNo", unique = true, nullable = false)
	private String phoneNo;

	@Column(name = "mail", unique = true, nullable = false)
	private String mail;
	
	 @Column(name = "verified")
	 private boolean verified = false; // Mail ile aktivasyon yapılınca true olacak
	
	 @Column(name = "role")
	 private String role = "USER"; // ADMIN, USER gibi roller için

}
