package co.edu.usbcali.demo.mapper;

import co.edu.usbcali.demo.domain.Treatment;
import co.edu.usbcali.demo.dto.TreatmentDTO;

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
public interface TreatmentMapper {
    @Mapping(source = "appointment.appointmentId", target = "appointmentId_Appointment")
    @Mapping(source = "product.productId", target = "productId_Product")
    public TreatmentDTO treatmentToTreatmentDTO(Treatment treatment);

    @Mapping(source = "appointmentId_Appointment", target = "appointment.appointmentId")
    @Mapping(source = "productId_Product", target = "product.productId")
    public Treatment treatmentDTOToTreatment(TreatmentDTO treatmentDTO);

    public List<TreatmentDTO> listTreatmentToListTreatmentDTO(
        List<Treatment> treatments);

    public List<Treatment> listTreatmentDTOToListTreatment(
        List<TreatmentDTO> treatmentDTOs);
}
