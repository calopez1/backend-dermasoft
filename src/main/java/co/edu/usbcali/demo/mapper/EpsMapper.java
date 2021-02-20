package co.edu.usbcali.demo.mapper;

import co.edu.usbcali.demo.domain.Eps;
import co.edu.usbcali.demo.dto.EpsDTO;

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
public interface EpsMapper {
    public EpsDTO epsToEpsDTO(Eps eps);

    public Eps epsDTOToEps(EpsDTO epsDTO);

    public List<EpsDTO> listEpsToListEpsDTO(List<Eps> epss);

    public List<Eps> listEpsDTOToListEps(List<EpsDTO> epsDTOs);
}
