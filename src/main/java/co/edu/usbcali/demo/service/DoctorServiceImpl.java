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
public class DoctorServiceImpl implements DoctorService{

	@Autowired
	private DoctorRepository doctorRepository;
	
	@Autowired
	private Validator validator;
                
    @Override        		
	public void validate(Doctor doctor)throws ConstraintViolationException{		
		
		Set<ConstraintViolation<Doctor>> constraintViolations =validator.validate(doctor);
		 if (!constraintViolations.isEmpty()) {			
			throw new ConstraintViolationException(constraintViolations);
		}
		
	}
	
	@Override
	@Transactional(readOnly=true)
	public Long count(){
	 	return doctorRepository.count();
	}

	@Override
	@Transactional(readOnly=true)
	public List<Doctor> findAll(){
		log.debug("finding all Doctor instances");
       	return doctorRepository.findAll();
    }
			
			
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED,rollbackFor = Exception.class)			
    public Doctor save(Doctor entity) throws Exception {
		log.debug("saving Doctor instance");
	   
	    
	    if(entity==null){
			throw new ZMessManager().new NullEntityExcepcion("Doctor");
		}
		
		validate(entity);	
	
		if(doctorRepository.existsById(entity.getDoctorIdentification())){
           throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
        }    
	
	    return doctorRepository.save(entity);
	   
    }
			
			
			@Override
			@Transactional(readOnly = false, propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
            public void delete(Doctor entity) throws Exception {
            	log.debug("deleting Doctor instance");
            	
	            if(entity==null){
	    			throw new ZMessManager().new NullEntityExcepcion("Doctor");
	    		}
    	
                                if(entity.getDoctorIdentification()==null){
                    throw new ZMessManager().new EmptyFieldException("doctorIdentification");
                    }
                        
            if(doctorRepository.existsById(entity.getDoctorIdentification())==false){
           		throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
        	} 
            
            	            findById(entity.getDoctorIdentification()).ifPresent(entidad->{	            	
	                													List<Appointment> appointments = entidad.getAppointments();
							                    if(Utilities.validationsList(appointments)==true){
                       	 	throw new ZMessManager().new DeletingException("appointments");
                        }
	                	            });
                       

           
            
            doctorRepository.deleteById(entity.getDoctorIdentification());
            log.debug("delete Doctor successful");
            
           
            	
            }
            
            @Override
			@Transactional(readOnly = false, propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
            public void deleteById(String id) throws Exception {            
            	log.debug("deleting Doctor instance");
            	if(id==null){
            		throw new ZMessManager().new EmptyFieldException("doctorIdentification");
            	}
            	if(doctorRepository.existsById(id)){
           			delete(doctorRepository.findById(id).get());
       			}    
            }	
			
			@Override
			@Transactional(readOnly = false, propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
            public Doctor update(Doctor entity) throws Exception {

				log.debug("updating Doctor instance");
				
	           
	            
	            	if(entity==null){
		    			throw new ZMessManager().new NullEntityExcepcion("Doctor");
		    		}
		    		
	            validate(entity);
	            
	            
	            if(doctorRepository.existsById(entity.getDoctorIdentification())==false){
           			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
        		}	            
	
	            return doctorRepository.save(entity);
	        
            }
			
			@Override
			@Transactional(readOnly=true)
            public Optional<Doctor> findById(String doctorIdentification) {            
            	log.debug("getting Doctor instance");
            	return doctorRepository.findById(doctorIdentification);
            }
			
}
