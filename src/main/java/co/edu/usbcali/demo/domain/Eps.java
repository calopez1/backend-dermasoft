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
@Table ( name="eps", schema="public" )
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Eps implements java.io.Serializable {

	private static final long serialVersionUID = 1L;


			@Id
	    @Column(name="eps_id", unique=true, nullable=false)
		@NotNull
		private Integer epsId;
		
	
	    
						@NotNull
						@NotEmpty
			@Size(max=255)
							@Column(name="eps_name"  , nullable=false  )
		private String epsName;	
    					@NotNull
						@NotEmpty
			@Size(max=1)
							@Column(name="state"  , nullable=false  )
		private String state;	
    					@NotNull
							@Column(name="valid_register"  , nullable=false  )
		private Date validRegister;	
        
		@OneToMany(fetch=FetchType.LAZY, mappedBy="eps")
		private List<Patient> patients = new ArrayList<>();	
        
}	