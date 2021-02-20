package co.edu.usbcali.demo.mapper;

import co.edu.usbcali.demo.domain.Patient;
import co.edu.usbcali.demo.dto.PatientDTO;

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
public interface PatientMapper {
    @Mapping(source = "city.cityId", target = "cityId_City")
    @Mapping(source = "eps.epsId", target = "epsId_Eps")
    public PatientDTO patientToPatientDTO(Patient patient);

    @Mapping(source = "cityId_City", target = "city.cityId")
    @Mapping(source = "epsId_Eps", target = "eps.epsId")
    public Patient patientDTOToPatient(PatientDTO patientDTO);

    public List<PatientDTO> listPatientToListPatientDTO(List<Patient> patients);

    public List<Patient> listPatientDTOToListPatient(
        List<PatientDTO> patientDTOs);
}
