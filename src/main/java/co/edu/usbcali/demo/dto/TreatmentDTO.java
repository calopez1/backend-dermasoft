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
public class TreatmentDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @NotNull
    @NotEmpty
    @Size(max = 255)
    private String description;
    @NotNull
    private Integer duration;
    @NotNull
    private Integer quantity;
    @NotNull
    @NotEmpty
    @Size(max = 1)
    private String state;
    @NotNull
    private Integer treatmentId;
    @NotNull
    private Date validRegister;
    private Integer appointmentId_Appointment;
    private Integer productId_Product;
}
