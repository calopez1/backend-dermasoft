package co.edu.usbcali.demo.controller;

import co.edu.usbcali.demo.domain.*;
import co.edu.usbcali.demo.dto.AppointmentDTO;
import co.edu.usbcali.demo.mapper.AppointmentMapper;
import co.edu.usbcali.demo.service.AppointmentService;

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
@RequestMapping("/api/v1/appointment")
@CrossOrigin(origins = "*")
@Slf4j
public class AppointmentRestController {
    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private AppointmentMapper appointmentMapper;

    @GetMapping(value = "/{appointmentId}")
    public ResponseEntity<?> findById(
        @PathVariable("appointmentId")
    Integer appointmentId) throws Exception {
        log.debug("Request to findById() Appointment");

        Appointment appointment = (appointmentService.findById(appointmentId)
                                                     .isPresent() == true)
            ? appointmentService.findById(appointmentId).get() : null;

        return ResponseEntity.ok()
                             .body(appointmentMapper.appointmentToAppointmentDTO(
                appointment));
    }

    @GetMapping()
    public ResponseEntity<?> findAll() throws Exception {
        log.debug("Request to findAll() Appointment");

        return ResponseEntity.ok()
                             .body(appointmentMapper.listAppointmentToListAppointmentDTO(
                appointmentService.findAll()));
    }

    @PostMapping()
    public ResponseEntity<?> save(
        @Valid
    @RequestBody
    AppointmentDTO appointmentDTO) throws Exception {
        log.debug("Request to save Appointment: {}", appointmentDTO);

        Appointment appointment = appointmentMapper.appointmentDTOToAppointment(appointmentDTO);
        appointment = appointmentService.save(appointment);

        return ResponseEntity.ok()
                             .body(appointmentMapper.appointmentToAppointmentDTO(
                appointment));
    }

    @PutMapping()
    public ResponseEntity<?> update(
        @Valid
    @RequestBody
    AppointmentDTO appointmentDTO) throws Exception {
        log.debug("Request to update Appointment: {}", appointmentDTO);

        Appointment appointment = appointmentMapper.appointmentDTOToAppointment(appointmentDTO);
        appointment = appointmentService.update(appointment);

        return ResponseEntity.ok()
                             .body(appointmentMapper.appointmentToAppointmentDTO(
                appointment));
    }

    @DeleteMapping(value = "/{appointmentId}")
    public ResponseEntity<?> delete(
        @PathVariable("appointmentId")
    Integer appointmentId) throws Exception {
        log.debug("Request to delete Appointment");

        appointmentService.deleteById(appointmentId);

        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/count")
    public ResponseEntity<?> count() {
        return ResponseEntity.ok().body(appointmentService.count());
    }
}
