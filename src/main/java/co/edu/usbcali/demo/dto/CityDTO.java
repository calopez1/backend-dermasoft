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
public class CityDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @NotNull
    @NotEmpty
    @Size(max = 5)
    private String cityCode;
    @NotNull
    private Integer cityId;
    @NotNull
    @NotEmpty
    @Size(max = 50)
    private String description;
    @NotNull
    @NotEmpty
    @Size(max = 1)
    private String state;
    @NotNull
    @NotEmpty
    @Size(max = 1)
    private String validRegister;
    private Integer departmentId;
}
