package co.edu.usbcali.demo.mapper;

import co.edu.usbcali.demo.domain.City;
import co.edu.usbcali.demo.dto.CityDTO;

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
public interface CityMapper {
    @Mapping(source = "department.departmentId", target = "departmentId")
    public CityDTO cityToCityDTO(City city);

    @Mapping(source = "departmentId", target = "department.departmentId")
    public City cityDTOToCity(CityDTO cityDTO);

    public List<CityDTO> listCityToListCityDTO(List<City> citys);

    public List<City> listCityDTOToListCity(List<CityDTO> cityDTOs);
}
