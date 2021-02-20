package co.edu.usbcali.demo.mapper;

import co.edu.usbcali.demo.domain.Appointment;
import co.edu.usbcali.demo.dto.AppointmentDTO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


/**
* @author Zathura Code Generator Version 9.0 http://zathuracode.org
* www.zathuracode.org
*
* Mapper Build with MapStruct https://mapstruct.org
* MapStruct is a code generator that greatly simplifies the
* implementation of mappings between Java bean type
* based on a convention over configuration approach.
*/
@Mapper
public interface AppointmentMapper {
    @Mapping(source = "doctor.doctorIdentification", target = "doctorIdentification_Doctor")
    @Mapping(source = "patient.patientIdentification", target = "patientIdentification_Patient")
    public AppointmentDTO appointmentToAppointmentDTO(Appointment appointment);

    @Mapping(source = "doctorIdentification_Doctor", target = "doctor.doctorIdentification")
    @Mapping(source = "patientIdentification_Patient", target = "patient.patientIdentification")
    public Appointment appointmentDTOToAppointment(
        AppointmentDTO appointmentDTO);

    public List<AppointmentDTO> listAppointmentToListAppointmentDTO(
        List<Appointment> appointments);

    public List<Appointment> listAppointmentDTOToListAppointment(
        List<AppointmentDTO> appointmentDTOs);
}
