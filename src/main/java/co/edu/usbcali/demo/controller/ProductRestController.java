package co.edu.usbcali.demo.controller;

import co.edu.usbcali.demo.domain.*;
import co.edu.usbcali.demo.dto.ProductDTO;
import co.edu.usbcali.demo.mapper.ProductMapper;
import co.edu.usbcali.demo.service.ProductService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


/**
* @author Zathura Code Generator Version 9.0 http://zathuracode.org
* www.zathuracode.org
*
*/
@RestController
@RequestMapping("/api/v1/product")
@CrossOrigin(origins = "*")
@Slf4j
public class ProductRestController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductMapper productMapper;

    @GetMapping(value = "/{productId}")
    public ResponseEntity<?> findById(
        @PathVariable("productId")
    Integer productId) throws Exception {
        log.debug("Request to findById() Product");

        Product product = (productService.findById(productId).isPresent() == true)
            ? productService.findById(productId).get() : null;

        return ResponseEntity.ok()
                             .body(productMapper.productToProductDTO(product));
    }

    @GetMapping()
    public ResponseEntity<?> findAll() throws Exception {
        log.debug("Request to findAll() Product");

        return ResponseEntity.ok()
                             .body(productMapper.listProductToListProductDTO(
                productService.findAll()));
    }

    @PostMapping()
    public ResponseEntity<?> save(@Valid
    @RequestBody
    ProductDTO productDTO) throws Exception {
        log.debug("Request to save Product: {}", productDTO);

        Product product = productMapper.productDTOToProduct(productDTO);
        product = productService.save(product);

        return ResponseEntity.ok()
                             .body(productMapper.productToProductDTO(product));
    }

    @PutMapping()
    public ResponseEntity<?> update(@Valid
    @RequestBody
    ProductDTO productDTO) throws Exception {
        log.debug("Request to update Product: {}", productDTO);

        Product product = productMapper.productDTOToProduct(productDTO);
        product = productService.update(product);

        return ResponseEntity.ok()
                             .body(productMapper.productToProductDTO(product));
    }

    @DeleteMapping(value = "/{productId}")
    public ResponseEntity<?> delete(@PathVariable("productId")
    Integer productId) throws Exception {
        log.debug("Request to delete Product");

        productService.deleteById(productId);

        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/count")
    public ResponseEntity<?> count() {
        return ResponseEntity.ok().body(productService.count());
    }
}
