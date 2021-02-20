package co.edu.usbcali.demo.mapper;

import co.edu.usbcali.demo.domain.Product;
import co.edu.usbcali.demo.dto.ProductDTO;

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
public interface ProductMapper {
    public ProductDTO productToProductDTO(Product product);

    public Product productDTOToProduct(ProductDTO productDTO);

    public List<ProductDTO> listProductToListProductDTO(List<Product> products);

    public List<Product> listProductDTOToListProduct(
        List<ProductDTO> productDTOs);
}
