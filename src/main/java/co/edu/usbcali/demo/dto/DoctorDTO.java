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
public class DoctorDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @NotNull
    private Date birthday;
    @NotNull
    @NotEmpty
    @Size(max = 255)
    private String description;
    @NotNull
    @NotEmpty
    @Size(max = 20)
    private String doctorIdentification;
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
    @NotNull
    @NotEmpty
    @Size(max = 20)
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
    @Size(max = 255)
    private String password;
    @NotNull
    @NotEmpty
    @Size(max = 20)
    private String phone;
    @NotNull
    private Integer price;
    private Integer reputation;
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
    private String adminIdentification_Admin;
    private Integer cityId_City;
}
