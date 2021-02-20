package co.edu.usbcali.demo.controller;

import co.edu.usbcali.demo.domain.*;
import co.edu.usbcali.demo.dto.DoctorDTO;
import co.edu.usbcali.demo.mapper.DoctorMapper;
import co.edu.usbcali.demo.service.DoctorService;

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
@RequestMapping("/api/v1/doctor")
@CrossOrigin(origins = "*")
@Slf4j
public class DoctorRestController {
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private DoctorMapper doctorMapper;

    @GetMapping(value = "/{doctorIdentification}")
    public ResponseEntity<?> findById(
        @PathVariable("doctorIdentification")
    String doctorIdentification) throws Exception {
        log.debug("Request to findById() Doctor");

        Doctor doctor = (doctorService.findById(doctorIdentification).isPresent() == true)
            ? doctorService.findById(doctorIdentification).get() : null;

        return ResponseEntity.ok().body(doctorMapper.doctorToDoctorDTO(doctor));
    }

    @GetMapping()
    public ResponseEntity<?> findAll() throws Exception {
        log.debug("Request to findAll() Doctor");

        return ResponseEntity.ok()
                             .body(doctorMapper.listDoctorToListDoctorDTO(
                doctorService.findAll()));
    }

    @PostMapping()
    public ResponseEntity<?> save(@Valid
    @RequestBody
    DoctorDTO doctorDTO) throws Exception {
        log.debug("Request to save Doctor: {}", doctorDTO);

        Doctor doctor = doctorMapper.doctorDTOToDoctor(doctorDTO);
        doctor = doctorService.save(doctor);

        return ResponseEntity.ok().body(doctorMapper.doctorToDoctorDTO(doctor));
    }

    @PutMapping()
    public ResponseEntity<?> update(@Valid
    @RequestBody
    DoctorDTO doctorDTO) throws Exception {
        log.debug("Request to update Doctor: {}", doctorDTO);

        Doctor doctor = doctorMapper.doctorDTOToDoctor(doctorDTO);
        doctor = doctorService.update(doctor);

        return ResponseEntity.ok().body(doctorMapper.doctorToDoctorDTO(doctor));
    }

    @DeleteMapping(value = "/{doctorIdentification}")
    public ResponseEntity<?> delete(
        @PathVariable("doctorIdentification")
    String doctorIdentification) throws Exception {
        log.debug("Request to delete Doctor");

        doctorService.deleteById(doctorIdentification);

        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/count")
    public ResponseEntity<?> count() {
        return ResponseEntity.ok().body(doctorService.count());
    }
}
