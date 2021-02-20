package co.edu.usbcali.demo.controller;

import co.edu.usbcali.demo.domain.*;
import co.edu.usbcali.demo.dto.CityDTO;
import co.edu.usbcali.demo.mapper.CityMapper;
import co.edu.usbcali.demo.service.CityService;

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
@RequestMapping("/api/v1/city")
@CrossOrigin(origins = "*")
@Slf4j
public class CityRestController {
    @Autowired
    private CityService cityService;
    @Autowired
    private CityMapper cityMapper;

    @GetMapping(value = "/{cityId}")
    public ResponseEntity<?> findById(@PathVariable("cityId")
    Integer cityId) throws Exception {
        log.debug("Request to findById() City");

        City city = (cityService.findById(cityId).isPresent() == true)
            ? cityService.findById(cityId).get() : null;

        return ResponseEntity.ok().body(cityMapper.cityToCityDTO(city));
    }

    @GetMapping()
    public ResponseEntity<?> findAll() throws Exception {
        log.debug("Request to findAll() City");

        return ResponseEntity.ok()
                             .body(cityMapper.listCityToListCityDTO(
                cityService.findAll()));
    }

    @PostMapping()
    public ResponseEntity<?> save(@Valid
    @RequestBody
    CityDTO cityDTO) throws Exception {
        log.debug("Request to save City: {}", cityDTO);

        City city = cityMapper.cityDTOToCity(cityDTO);
        city = cityService.save(city);

        return ResponseEntity.ok().body(cityMapper.cityToCityDTO(city));
    }

    @PutMapping()
    public ResponseEntity<?> update(@Valid
    @RequestBody
    CityDTO cityDTO) throws Exception {
        log.debug("Request to update City: {}", cityDTO);

        City city = cityMapper.cityDTOToCity(cityDTO);
        city = cityService.update(city);

        return ResponseEntity.ok().body(cityMapper.cityToCityDTO(city));
    }

    @DeleteMapping(value = "/{cityId}")
    public ResponseEntity<?> delete(@PathVariable("cityId")
    Integer cityId) throws Exception {
        log.debug("Request to delete City");

        cityService.deleteById(cityId);

        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/count")
    public ResponseEntity<?> count() {
        return ResponseEntity.ok().body(cityService.count());
    }
}
