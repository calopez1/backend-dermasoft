package  co.edu.usbcali.demo.service;


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
* www.zathuracode.org
* 
*/

@Scope("singleton")
@Service
@Slf4j
public class PatientServiceImpl implements PatientService{

	@Autowired
	private PatientRepository patientRepository;
	
	@Autowired
	private Validator validator;
                
    @Override        		
	public void validate(Patient patient)throws ConstraintViolationException{		
		
		Set<ConstraintViolation<Patient>> constraintViolations =validator.validate(patient);
		 if (!constraintViolations.isEmpty()) {			
			throw new ConstraintViolationException(constraintViolations);
		}
		
	}
	
	@Override
	@Transactional(readOnly=true)
	public Long count(){
	 	return patientRepository.count();
	}

	@Override
	@Transactional(readOnly=true)
	public List<Patient> findAll(){
		log.debug("finding all Patient instances");
       	return patientRepository.findAll();
    }
			
			
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED,rollbackFor = Exception.class)			
    public Patient save(Patient entity) throws Exception {
		log.debug("saving Patient instance");
	   
	    
	    if(entity==null){
			throw new ZMessManager().new NullEntityExcepcion("Patient");
		}
		
		validate(entity);	
	
		if(patientRepository.existsById(entity.getPatientIdentification())){
           throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
        }    
	
	    return patientRepository.save(entity);
	   
    }
			
			
			@Override
			@Transactional(readOnly = false, propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
            public void delete(Patient entity) throws Exception {
            	log.debug("deleting Patient instance");
            	
	            if(entity==null){
	    			throw new ZMessManager().new NullEntityExcepcion("Patient");
	    		}
    	
                                if(entity.getPatientIdentification()==null){
                    throw new ZMessManager().new EmptyFieldException("patientIdentification");
                    }
                        
            if(patientRepository.existsById(entity.getPatientIdentification())==false){
           		throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
        	} 
            
            	            findById(entity.getPatientIdentification()).ifPresent(entidad->{	            	
	                													List<Appointment> appointments = entidad.getAppointments();
							                    if(Utilities.validationsList(appointments)==true){
                       	 	throw new ZMessManager().new DeletingException("appointments");
                        }
	                	            });
                       

           
            
            patientRepository.deleteById(entity.getPatientIdentification());
            log.debug("delete Patient successful");
            
           
            	
            }
            
            @Override
			@Transactional(readOnly = false, propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
            public void deleteById(String id) throws Exception {            
            	log.debug("deleting Patient instance");
            	if(id==null){
            		throw new ZMessManager().new EmptyFieldException("patientIdentification");
            	}
            	if(patientRepository.existsById(id)){
           			delete(patientRepository.findById(id).get());
       			}    
            }	
			
			@Override
			@Transactional(readOnly = false, propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
            public Patient update(Patient entity) throws Exception {

				log.debug("updating Patient instance");
				
	           
	            
	            	if(entity==null){
		    			throw new ZMessManager().new NullEntityExcepcion("Patient");
		    		}
		    		
	            validate(entity);
	            
	            
	            if(patientRepository.existsById(entity.getPatientIdentification())==false){
           			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
        		}	            
	
	            return patientRepository.save(entity);
	        
            }
			
			@Override
			@Transactional(readOnly=true)
            public Optional<Patient> findById(String patientIdentification) {            
            	log.debug("getting Patient instance");
            	return patientRepository.findById(patientIdentification);
            }
			
}
