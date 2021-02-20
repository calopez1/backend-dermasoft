package co.edu.usbcali.demo.service;

import java.math.*;
import java.util.*;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import co.edu.usbcali.demo.exception.*;
import co.edu.usbcali.demo.repository.*;
import co.edu.usbcali.demo.utility.Utilities;

import co.edu.usbcali.demo.domain.*;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Zathura Code Generator Version 9.0 http://zathuracode.org/
 *         www.zathuracode.org
 * 
 */

@Scope("singleton")
@Service
@Slf4j
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private Validator validator;

	@Override
	public void validate(Department department) throws ConstraintViolationException {

		Set<ConstraintViolation<Department>> constraintViolations = validator.validate(department);
		if (!constraintViolations.isEmpty()) {
			throw new ConstraintViolationException(constraintViolations);
		}

	}

	@Override
	@Transactional(readOnly = true)
	public Long count() {
		return departmentRepository.count();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Department> findAll() {
		log.debug("finding all Department instances");
		return departmentRepository.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Department save(Department entity) throws Exception {
		log.debug("saving Department instance");

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("Department");
		}

		validate(entity);

		if (departmentRepository.existsById(entity.getDepartmentId())) {
			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
		}

		return departmentRepository.save(entity);

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(Department entity) throws Exception {
		log.debug("deleting Department instance");

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("Department");
		}

		if (entity.getDepartmentId() == null) {
			throw new ZMessManager().new EmptyFieldException("departmentId");
		}

		if (departmentRepository.existsById(entity.getDepartmentId()) == false) {
			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
		}

		findById(entity.getDepartmentId()).ifPresent(entidad -> {
			List<City> cities = entidad.getCities();
			if (Utilities.validationsList(cities) == true) {
				throw new ZMessManager().new DeletingException("cities");
			}
		});

		departmentRepository.deleteById(entity.getDepartmentId());
		log.debug("delete Department successful");

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteById(Integer id) throws Exception {
		log.debug("deleting Department instance");
		if (id == null) {
			throw new ZMessManager().new EmptyFieldException("departmentId");
		}
		if (departmentRepository.existsById(id)) {
			delete(departmentRepository.findById(id).get());
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Department update(Department entity) throws Exception {

		log.debug("updating Department instance");

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("Department");
		}

		validate(entity);

		if (departmentRepository.existsById(entity.getDepartmentId()) == false) {
			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
		}

		return departmentRepository.save(entity);

	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Department> findById(Integer departmentId) {
		log.debug("getting Department instance");
		return departmentRepository.findById(departmentId);
	}

}
