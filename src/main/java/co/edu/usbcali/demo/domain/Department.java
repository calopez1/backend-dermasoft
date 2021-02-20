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
@Table ( name="department", schema="public" )
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department implements java.io.Serializable {

	private static final long serialVersionUID = 1L;


			@Id
	    @Column(name="department_id", unique=true, nullable=false)
		@NotNull
		private Integer departmentId;
		
	
	    
						@NotNull
						@NotEmpty
			@Size(max=2)
							@Column(name="department_code"  , nullable=false  )
		private String departmentCode;	
    					@NotNull
						@NotEmpty
			@Size(max=50)
							@Column(name="description"  , nullable=false  )
		private String description;	
    					@NotNull
						@NotEmpty
			@Size(max=1)
							@Column(name="state"  , nullable=false  )
		private String state;	
    					@NotNull
							@Column(name="valid_register"  , nullable=false  )
		private Date validRegister;	
        
		@OneToMany(fetch=FetchType.LAZY, mappedBy="department")
		private List<City> cities = new ArrayList<>();	
        
}	