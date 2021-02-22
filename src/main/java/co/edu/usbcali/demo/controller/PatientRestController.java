package co.edu.usbcali.demo.controller;

import co.edu.usbcali.demo.domain.*;
import co.edu.usbcali.demo.dto.PatientDTO;
import co.edu.usbcali.demo.mapper.PatientMapper;
import co.edu.usbcali.demo.service.PatientService;

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
 *         www.zathuracode.org
 *
 */
@RestController
@RequestMapping("/api/v1/patient")
@CrossOrigin(origins = "*")
@Slf4j
public class PatientRestController {
	@Autowired
	private PatientService patientService;
	@Autowired
	private PatientMapper patientMapper;

	@GetMapping(value = "/{patientIdentification}")
	public ResponseEntity<?> findById(@PathVariable("patientIdentification") String patientIdentification)
			throws Exception {
		log.debug("Request to findById() Patient");

		Patient patient = (patientService.findById(patientIdentification).isPresent() == true)
				? patientService.findById(patientIdentification).get()
				: null;

		return ResponseEntity.ok().body(patientMapper.patientToPatientDTO(patient));
	}

	@GetMapping()
	public ResponseEntity<?> findAll() throws Exception {
		log.debug("Request to findAll() Patient");

		return ResponseEntity.ok().body(patientMapper.listPatientToListPatientDTO(patientService.findAll()));
	}

	@GetMapping("/loginPatient/{id}/{password}")
	public ResponseEntity<?> loginPatient(@PathVariable("id") String id, @PathVariable("password") String password)
			throws Exception {
		Patient patient = (patientService.findByPatientIdAndPassword(id, password).isPresent() == true)
				? patientService.findByPatientIdAndPassword(id, password).get()
				: null;

		return ResponseEntity.ok().body(patientMapper.patientToPatientDTO(patient));

	}

	@PostMapping()
	public ResponseEntity<?> save(@Valid @RequestBody PatientDTO patientDTO) throws Exception {
		log.debug("Request to save Patient: {}", patientDTO);

		Patient patient = patientMapper.patientDTOToPatient(patientDTO);
		patient = patientService.save(patient);

		return ResponseEntity.ok().body(patientMapper.patientToPatientDTO(patient));
	}

	@PutMapping()
	public ResponseEntity<?> update(@Valid @RequestBody PatientDTO patientDTO) throws Exception {
		log.debug("Request to update Patient: {}", patientDTO);

		Patient patient = patientMapper.patientDTOToPatient(patientDTO);
		patient = patientService.update(patient);

		return ResponseEntity.ok().body(patientMapper.patientToPatientDTO(patient));
	}

	@DeleteMapping(value = "/{patientIdentification}")
	public ResponseEntity<?> delete(@PathVariable("patientIdentification") String patientIdentification)
			throws Exception {
		log.debug("Request to delete Patient");

		patientService.deleteById(patientIdentification);

		return ResponseEntity.ok().build();
	}

	@GetMapping(value = "/count")
	public ResponseEntity<?> count() {
		return ResponseEntity.ok().body(patientService.count());
	}
}
