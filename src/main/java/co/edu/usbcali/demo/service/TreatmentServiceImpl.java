package co.edu.usbcali.demo.service;

import co.edu.usbcali.demo.domain.*;
import co.edu.usbcali.demo.exception.*;
import co.edu.usbcali.demo.repository.*;
import co.edu.usbcali.demo.utility.Utilities;

import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Scope;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.*;

import java.util.*;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;


/**
* @author Zathura Code Generator Version 9.0 http://zathuracode.org
* www.zathuracode.org
*
*/
@Scope("singleton")
@Service
@Slf4j
public class TreatmentServiceImpl implements TreatmentService {
    @Autowired
    private TreatmentRepository treatmentRepository;
    @Autowired
    private Validator validator;

    @Override
    public void validate(Treatment treatment)
        throws ConstraintViolationException {
        Set<ConstraintViolation<Treatment>> constraintViolations = validator.validate(treatment);

        if (!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException(constraintViolations);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Long count() {
        return treatmentRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Treatment> findAll() {
        log.debug("finding all Treatment instances");

        return treatmentRepository.findAll();
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Treatment save(Treatment entity) throws Exception {
        log.debug("saving Treatment instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("Treatment");
        }

        validate(entity);

        if (treatmentRepository.existsById(entity.getTreatmentId())) {
            throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
        }

        return treatmentRepository.save(entity);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(Treatment entity) throws Exception {
        log.debug("deleting Treatment instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("Treatment");
        }

        if (entity.getTreatmentId() == null) {
            throw new ZMessManager().new EmptyFieldException("treatmentId");
        }

        if (treatmentRepository.existsById(entity.getTreatmentId()) == false) {
            throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
        }

        treatmentRepository.deleteById(entity.getTreatmentId());
        log.debug("delete Treatment successful");
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deleteById(Integer id) throws Exception {
        log.debug("deleting Treatment instance");

        if (id == null) {
            throw new ZMessManager().new EmptyFieldException("treatmentId");
        }

        if (treatmentRepository.existsById(id)) {
            delete(treatmentRepository.findById(id).get());
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Treatment update(Treatment entity) throws Exception {
        log.debug("updating Treatment instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("Treatment");
        }

        validate(entity);

        if (treatmentRepository.existsById(entity.getTreatmentId()) == false) {
            throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
        }

        return treatmentRepository.save(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Treatment> findById(Integer treatmentId) {
        log.debug("getting Treatment instance");

        return treatmentRepository.findById(treatmentId);
    }
}
