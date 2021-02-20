package co.edu.usbcali.demo.mapper;

import co.edu.usbcali.demo.domain.Doctor;
import co.edu.usbcali.demo.dto.DoctorDTO;

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
public interface DoctorMapper {
    @Mapping(source = "admin.adminIdentification", target = "adminIdentification_Admin")
    @Mapping(source = "city.cityId", target = "cityId_City")
    public DoctorDTO doctorToDoctorDTO(Doctor doctor);

    @Mapping(source = "adminIdentification_Admin", target = "admin.adminIdentification")
    @Mapping(source = "cityId_City", target = "city.cityId")
    public Doctor doctorDTOToDoctor(DoctorDTO doctorDTO);

    public List<DoctorDTO> listDoctorToListDoctorDTO(List<Doctor> doctors);

    public List<Doctor> listDoctorDTOToListDoctor(List<DoctorDTO> doctorDTOs);
}
