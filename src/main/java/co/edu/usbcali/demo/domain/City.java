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
@Table(name = "city", schema = "public")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class City implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "city_id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@NotNull
	private Integer cityId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "department_id")
	@NotNull
	private Department department;

	@NotNull
	@NotEmpty
	@Size(max = 5)
	@Column(name = "city_code", nullable = false)
	private String cityCode;
	@NotNull
	@NotEmpty
	@Size(max = 50)
	@Column(name = "description", nullable = false)
	private String description;
	@NotNull
	@NotEmpty
	@Size(max = 1)
	@Column(name = "state", nullable = false)
	private String state;
	@NotNull
	@NotEmpty
	@Size(max = 1)
	@Column(name = "valid_register", nullable = false)
	private String validRegister;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "city")
	private List<Doctor> doctors = new ArrayList<>();
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "city")
	private List<Patient> patients = new ArrayList<>();

}