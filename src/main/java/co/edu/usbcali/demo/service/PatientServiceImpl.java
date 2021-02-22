package co.edu.usbcali.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.demo.domain.Appointment;
import co.edu.usbcali.demo.domain.Patient;
import co.edu.usbcali.demo.exception.ZMessManager;
import co.edu.usbcali.demo.repository.PatientRepository;
import co.edu.usbcali.demo.utility.Utilities;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Zathura Code Generator Version 9.0 http://zathuracode.org/
 *         www.zathuracode.org
 * 
 */

@Scope("singleton")
@Service
@Slf4j
public class PatientServiceImpl implements PatientService {

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private Validator validator;

	// Encriptar contraseña
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public void validate(Patient patient) throws ConstraintViolationException {

		Set<ConstraintViolation<Patient>> constraintViolations = validator.validate(patient);
		if (!constraintViolations.isEmpty()) {
			throw new ConstraintViolationException(constraintViolations);
		}

	}

	@Override
	@Transactional(readOnly = true)
	public Long count() {
		return patientRepository.count();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Patient> findAll() {
		log.debug("finding all Patient instances");
		return patientRepository.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Patient save(Patient entity) throws Exception {
		log.debug("saving Patient instance");

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("Patient");
		}

		validate(entity);

		if (patientRepository.existsById(entity.getPatientIdentification())) {
			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
		}
		// Encriptación de la contraseña
		entity.setPassword(bCryptPasswordEncoder.encode(entity.getPassword()));
		return patientRepository.save(entity);

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(Patient entity) throws Exception {
		log.debug("deleting Patient instance");

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("Patient");
		}

		if (entity.getPatientIdentification() == null) {
			throw new ZMessManager().new EmptyFieldException("patientIdentification");
		}

		if (patientRepository.existsById(entity.getPatientIdentification()) == false) {
			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
		}

		findById(entity.getPatientIdentification()).ifPresent(entidad -> {
			List<Appointment> appointments = entidad.getAppointments();
			if (Utilities.validationsList(appointments) == true) {
				throw new ZMessManager().new DeletingException("appointments");
			}
		});

		patientRepository.deleteById(entity.getPatientIdentification());
		log.debug("delete Patient successful");

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteById(String id) throws Exception {
		log.debug("deleting Patient instance");
		if (id == null) {
			throw new ZMessManager().new EmptyFieldException("patientIdentification");
		}
		if (patientRepository.existsById(id)) {
			delete(patientRepository.findById(id).get());
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Patient update(Patient entity) throws Exception {

		log.debug("updating Patient instance");

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("Patient");
		}

		validate(entity);

		if (patientRepository.existsById(entity.getPatientIdentification()) == false) {
			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
		}
		return patientRepository.save(entity);

	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Patient> findById(String patientIdentification) {
		log.debug("getting Patient instance");
		return patientRepository.findById(patientIdentification);
	}

	@Override
	public Optional<Patient> findByPatientIdAndPassword(String identification, String password) {
		if (patientRepository.existsById(identification) == false) {
			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
		}
		Patient patient = patientRepository.findById(identification).get();
		if (bCryptPasswordEncoder.matches(password, patient.getPassword()) == false) {
			throw new ZMessManager("Incorrect password");
		}
		return patientRepository.findById(identification);
	}

}
