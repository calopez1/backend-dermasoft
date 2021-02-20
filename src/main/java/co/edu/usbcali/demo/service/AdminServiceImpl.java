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

import co.edu.usbcali.demo.domain.Admin;
import co.edu.usbcali.demo.domain.Doctor;
import co.edu.usbcali.demo.exception.ZMessManager;
import co.edu.usbcali.demo.repository.AdminRepository;
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
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private Validator validator;

	//Encriptar contraseña
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public void validate(Admin admin) throws ConstraintViolationException {

		Set<ConstraintViolation<Admin>> constraintViolations = validator.validate(admin);
		if (!constraintViolations.isEmpty()) {
			throw new ConstraintViolationException(constraintViolations);
		}

	}

	@Override
	@Transactional(readOnly = true)
	public Long count() {
		return adminRepository.count();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Admin> findAll() {
		log.debug("finding all Admin instances");
		return adminRepository.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Admin save(Admin entity) throws Exception {
		log.debug("saving Admin instance");

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("Admin");
		}

		validate(entity);

		if (adminRepository.existsById(entity.getAdminIdentification())) {
			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
		}
		//Encriptación de la contraseña
		entity.setPassword(bCryptPasswordEncoder.encode(entity.getPassword()));
		return adminRepository.save(entity);

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(Admin entity) throws Exception {
		log.debug("deleting Admin instance");

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("Admin");
		}

		if (entity.getAdminIdentification() == null) {
			throw new ZMessManager().new EmptyFieldException("adminIdentification");
		}

		if (adminRepository.existsById(entity.getAdminIdentification()) == false) {
			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
		}

		findById(entity.getAdminIdentification()).ifPresent(entidad -> {
			List<Doctor> doctors = entidad.getDoctors();
			if (Utilities.validationsList(doctors) == true) {
				throw new ZMessManager().new DeletingException("doctors");
			}
		});

		adminRepository.deleteById(entity.getAdminIdentification());
		log.debug("delete Admin successful");

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteById(String id) throws Exception {
		log.debug("deleting Admin instance");
		if (id == null) {
			throw new ZMessManager().new EmptyFieldException("adminIdentification");
		}
		if (adminRepository.existsById(id)) {
			delete(adminRepository.findById(id).get());
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Admin update(Admin entity) throws Exception {

		log.debug("updating Admin instance");

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("Admin");
		}

		validate(entity);

		if (adminRepository.existsById(entity.getAdminIdentification()) == false) {
			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
		}

		return adminRepository.save(entity);

	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Admin> findById(String adminIdentification) {
		log.debug("getting Admin instance");
		return adminRepository.findById(adminIdentification);
	}
	
	//Método login admin
	@Override
	@Transactional(readOnly = true)
	public Optional<Admin> findByAdminIdAndPassword(String admin_id, String password) {
		if (adminRepository.existsById(admin_id) == false) {
			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
		}
		Admin admin = adminRepository.findById(admin_id).get();
		if (bCryptPasswordEncoder.matches(password, admin.getPassword()) == false) {
			throw new ZMessManager("Incorrect password");
		}
		return adminRepository.findById(admin_id);
	}

}
