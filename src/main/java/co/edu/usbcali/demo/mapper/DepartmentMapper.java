package co.edu.usbcali.demo.mapper;

import co.edu.usbcali.demo.domain.Department;
import co.edu.usbcali.demo.dto.DepartmentDTO;

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
public interface DepartmentMapper {
    public DepartmentDTO departmentToDepartmentDTO(Department department);

    public Department departmentDTOToDepartment(DepartmentDTO departmentDTO);

    public List<DepartmentDTO> listDepartmentToListDepartmentDTO(
        List<Department> departments);

    public List<Department> listDepartmentDTOToListDepartment(
        List<DepartmentDTO> departmentDTOs);
}
