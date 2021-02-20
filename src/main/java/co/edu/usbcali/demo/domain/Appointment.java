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
* www.zathuracode.org
* 
*/
@Entity
@Table ( name="appointment", schema="public" )
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appointment implements java.io.Serializable {

	private static final long serialVersionUID = 1L;


			@Id
	    @Column(name="appointment_id", unique=true, nullable=false)
		@NotNull
		private Integer appointmentId;
		
	
			@ManyToOne(fetch=FetchType.LAZY)
   		@JoinColumn(name="doctor_identification"  	 )
		@NotNull
		private Doctor doctor;	
    		@ManyToOne(fetch=FetchType.LAZY)
   		@JoinColumn(name="patient_identification"  	 )
		@NotNull
		private Patient patient;	
        
						@NotNull
							@Column(name="date"  , nullable=false  )
		private Date date;	
    					@NotNull
						@NotEmpty
			@Size(max=255)
							@Column(name="description"  , nullable=false  )
		private String description;	
    					@NotNull
							@Column(name="duration"  , nullable=false  )
		private Integer duration;	
    					@NotNull
						@NotEmpty
			@Size(max=255)
							@Column(name="reason"  , nullable=false  )
		private String reason;	
    					@NotNull
						@NotEmpty
			@Size(max=1)
							@Column(name="state"  , nullable=false  )
		private String state;	
    					@NotNull
							@Column(name="valid_register"  , nullable=false  )
		private Date validRegister;	
        
		@OneToMany(fetch=FetchType.LAZY, mappedBy="appointment")
		private List<Treatment> treatments = new ArrayList<>();	
        
}	