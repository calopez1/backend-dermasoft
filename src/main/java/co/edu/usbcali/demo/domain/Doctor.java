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
@Table ( name="doctor", schema="public" )
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Doctor implements java.io.Serializable {

	private static final long serialVersionUID = 1L;


			@Id
	    @Column(name="doctor_identification", unique=true, nullable=false)
		@NotNull
		private String doctorIdentification;
		
	
			@ManyToOne(fetch=FetchType.LAZY)
   		@JoinColumn(name="admin_identification"  	 )
		@NotNull
		private Admin admin;	
    		@ManyToOne(fetch=FetchType.LAZY)
   		@JoinColumn(name="city_id"  	 )
		@NotNull
		private City city;	
        
						@NotNull
							@Column(name="birthday"  , nullable=false  )
		private Date birthday;	
    					@NotNull
						@NotEmpty
			@Size(max=255)
							@Column(name="description"  , nullable=false  )
		private String description;	
    					@NotNull
						@NotEmpty
			@Size(max=255)
							@Column(name="email"  , nullable=false  )
		private String email;	
    					@NotNull
						@NotEmpty
			@Size(max=80)
							@Column(name="first_name"  , nullable=false  )
		private String firstName;	
    					@NotNull
						@NotEmpty
			@Size(max=1)
							@Column(name="gender"  , nullable=false  )
		private String gender;	
    					@NotNull
						@NotEmpty
			@Size(max=20)
							@Column(name="identification_type"  , nullable=false  )
		private String identificationType;	
    				@Column(name="image"   )
		private String image;	
    					@NotNull
						@NotEmpty
			@Size(max=120)
							@Column(name="last_name"  , nullable=false  )
		private String lastName;	
    					@NotNull
						@NotEmpty
			@Size(max=40)
							@Column(name="last_name2"  , nullable=false  )
		private String lastName2;	
    					@NotNull
						@NotEmpty
			@Size(max=255)
							@Column(name="password"  , nullable=false  )
		private String password;	
    					@NotNull
						@NotEmpty
			@Size(max=20)
							@Column(name="phone"  , nullable=false  )
		private String phone;	
    					@NotNull
							@Column(name="price"  , nullable=false  )
		private Integer price;	
    				@Column(name="reputation"   )
		private Integer reputation;	
    					@NotNull
						@NotEmpty
			@Size(max=1)
							@Column(name="state"  , nullable=false  )
		private String state;	
    					@NotNull
							@Column(name="valid_register"  , nullable=false  )
		private Date validRegister;	
    					@NotNull
						@NotEmpty
			@Size(max=1)
							@Column(name="verified"  , nullable=false  )
		private String verified;	
        
		@OneToMany(fetch=FetchType.LAZY, mappedBy="doctor")
		private List<Appointment> appointments = new ArrayList<>();	
        
}	