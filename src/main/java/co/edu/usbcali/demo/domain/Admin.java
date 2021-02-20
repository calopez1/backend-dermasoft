package co.edu.usbcali.demo.domain;

import java.util.ArrayList;
import java.util.List;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Zathura Code Generator Version 9.0 http://zathuracode.org/
 *         www.zathuracode.org
 * 
 */
@Entity
@Table(name = "admin", schema = "public")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "admin_identification", unique = true, nullable = false)
	@NotNull
	private String adminIdentification;

	@NotNull
	@NotEmpty
	@Size(max = 255)
	@Column(name = "email", nullable = false)
	private String email;
	@Column(name = "first_name")
	private String firstName;
	@NotNull
	@NotEmpty
	@Size(max = 20)
	@Column(name = "identification_type", nullable = false)
	private String identificationType;
	@NotNull
	@NotEmpty
	@Size(max = 120)
	@Column(name = "last_name", nullable = false)
	private String lastName;
	@NotNull
	@NotEmpty
	@Size(max = 40)
	@Column(name = "last_name2", nullable = false)
	private String lastName2;
	@NotNull
	@NotEmpty
	@Size(max = 255)
	@Column(name = "password", nullable = false)
	private String password;
	@NotNull
	@NotEmpty
	@Size(max = 1)
	@Column(name = "state", nullable = false)
	private String state;
	@NotNull
	@Column(name = "valid_register", nullable = false)
	private Date validRegister;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "admin")
	private List<Doctor> doctors = new ArrayList<>();

}