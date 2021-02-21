package co.edu.usbcali.demo.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
* @author Zathura Code Generator Version 9.0 http://zathuracode.org
* www.zathuracode.org
*
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Size(max = 20)
    private String approved;
    private Date birthdate;
    @NotNull
    @NotEmpty
    @Size(max = 255)
    private String email;
    @Size(max = 80)
    private String firstName;
    @Size(max = 1)
    private String gender;
    private Integer height;
    @NotNull
    @NotEmpty
    @Size(max = 40)
    private String identificationType;
    private String image;
    @Size(max = 120)
    private String lastName;
    @Size(max = 40)
    private String lastName2;
    @Size(max = 20)
    private String maritalStatus;
    @Size(max = 50)
    private String ocupation;
    @NotNull
    @NotEmpty
    @Size(max = 255)
    private String password;
    @NotNull
    @NotEmpty
    @Size(max = 20)
    private String patientIdentification;
    @Size(max = 20)
    private String phone;
    @Size(max = 1)
    private String state;
    private Date validRegister;
    @Size(max = 1)
    private String verified;
    private Integer weight;
    private Integer cityId_City;
    private Integer epsId_Eps;
}
