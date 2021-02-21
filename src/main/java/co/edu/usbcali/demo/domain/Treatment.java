package co.edu.usbcali.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import javax.persistence.*;

import javax.validation.constraints.*;

/**
 * @author Zathura Code Generator Version 9.0 http://zathuracode.org
 *         www.zathuracode.org
 *
 */
@Entity
@Table(name = "treatment", schema = "public")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Treatment implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "treatment_id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	private Integer treatmentId;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "appointment_id")
	@NotNull
	private Appointment appointment;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	@NotNull
	private Product product;
	@NotNull
	@NotEmpty
	@Size(max = 255)
	@Column(name = "description", nullable = false)
	private String description;
	@NotNull
	@Column(name = "duration", nullable = false)
	private Integer duration;
	@NotNull
	@Column(name = "quantity", nullable = false)
	private Integer quantity;
	@NotNull
	@NotEmpty
	@Size(max = 1)
	@Column(name = "state", nullable = false)
	private String state;
	@NotNull
	@Column(name = "valid_register", nullable = false)
	private Date validRegister;
}
