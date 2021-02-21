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
@Table(name = "product", schema = "public")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "product_id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	private Integer productId;

	@NotNull
	@NotEmpty
	@Size(max = 255)
	@Column(name = "description", nullable = false)
	private String description;
	@NotNull
	@NotEmpty
	@Size(max = 255)
	@Column(name = "image", nullable = false)
	private String image;
	@NotNull
	@NotEmpty
	@Size(max = 80)
	@Column(name = "name", nullable = false)
	private String name;
	@NotNull
	@NotEmpty
	@Size(max = 1)
	@Column(name = "state", nullable = false)
	private String state;
	@NotNull
	@NotEmpty
	@Size(max = 255)
	@Column(name = "url", nullable = false)
	private String url;
	@NotNull
	@Column(name = "valid_register", nullable = false)
	private Date validRegister;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
	private List<Treatment> treatments = new ArrayList<>();

}