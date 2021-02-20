package co.edu.usbcali.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import java.sql.*;

import java.util.Date;

import javax.validation.constraints.*;


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
    @NotNull
    @NotEmpty
    @Size(max = 20)
    private String approved;
    @NotNull
    private Date birthdate;
    @NotNull
    @NotEmpty
    @Size(max = 255)
    private String email;
    @NotNull
    @NotEmpty
    @Size(max = 80)
    private String firstName;
    @NotNull
    @NotEmpty
    @Size(max = 1)
    private String gender;
    private Integer height;
    @NotNull
    @NotEmpty
    @Size(max = 40)
    private String identificationType;
    private String image;
    @NotNull
    @NotEmpty
    @Size(max = 120)
    private String lastName;
    @NotNull
    @NotEmpty
    @Size(max = 40)
    private String lastName2;
    @NotNull
    @NotEmpty
    @Size(max = 20)
    private String maritalStatus;
    @NotNull
    @NotEmpty
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
    @NotNull
    @NotEmpty
    @Size(max = 20)
    private String phone;
    @NotNull
    @NotEmpty
    @Size(max = 1)
    private String state;
    @NotNull
    private Date validRegister;
    @NotNull
    @NotEmpty
    @Size(max = 1)
    private String verified;
    private Integer weight;
    private Integer cityId_City;
    private Integer epsId_Eps;
}
