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
@Table(name = "patient", schema = "public")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "patient_identification", unique = true, nullable = false)
	@NotNull
	private String patientIdentification;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "city_id")
	private City city;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "eps_id")
	Eps eps;
	@Size(max = 20)
	@Column(name = "approved", nullable = false)
	private String approved;
	@Column(name = "birthdate", nullable = false)
	private Date birthdate;
	@NotNull
	@NotEmpty
	@Size(max = 255)
	@Column(name = "email", nullable = false)
	private String email;
	@Size(max = 80)
	@Column(name = "first_name", nullable = false)
	private String firstName;
	@Size(max = 1)
	@Column(name = "gender", nullable = false)
	private String gender;
	@Column(name = "height")
	private Integer height;
	@NotNull
	@NotEmpty
	@Size(max = 40)
	@Column(name = "identification_type", nullable = false)
	private String identificationType;
	@Column(name = "image")
	private String image;
	@Size(max = 120)
	@Column(name = "last_name", nullable = false)
	private String lastName;
	@Size(max = 40)
	@Column(name = "last_name2", nullable = false)
	private String lastName2;
	@Size(max = 20)
	@Column(name = "marital_status", nullable = false)
	private String maritalStatus;
	@Size(max = 50)
	@Column(name = "ocupation", nullable = false)
	private String ocupation;
	@NotNull
	@NotEmpty
	@Size(max = 255)
	@Column(name = "password", nullable = false)
	private String password;
	@Size(max = 20)
	@Column(name = "phone", nullable = false)
	private String phone;
	@Size(max = 1)
	@Column(name = "state", nullable = false)
	private String state;
	@Column(name = "valid_register", nullable = false)
	private Date validRegister;
	@Size(max = 1)
	@Column(name = "verified", nullable = false)
	private String verified;
	@Column(name = "weight")
	private Integer weight;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "patient")
	private List<Appointment> appointments = new ArrayList<>();

}