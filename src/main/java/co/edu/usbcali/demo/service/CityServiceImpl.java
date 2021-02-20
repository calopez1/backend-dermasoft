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
public class CityServiceImpl implements CityService{

	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private Validator validator;
                
    @Override        		
	public void validate(City city)throws ConstraintViolationException{		
		
		Set<ConstraintViolation<City>> constraintViolations =validator.validate(city);
		 if (!constraintViolations.isEmpty()) {			
			throw new ConstraintViolationException(constraintViolations);
		}
		
	}
	
	@Override
	@Transactional(readOnly=true)
	public Long count(){
	 	return cityRepository.count();
	}

	@Override
	@Transactional(readOnly=true)
	public List<City> findAll(){
		log.debug("finding all City instances");
       	return cityRepository.findAll();
    }
			
			
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED,rollbackFor = Exception.class)			
    public City save(City entity) throws Exception {
		log.debug("saving City instance");
	   
	    
	    if(entity==null){
			throw new ZMessManager().new NullEntityExcepcion("City");
		}
		
		validate(entity);	
	
		if(cityRepository.existsById(entity.getCityId())){
           throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
        }    
	
	    return cityRepository.save(entity);
	   
    }
			
			
			@Override
			@Transactional(readOnly = false, propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
            public void delete(City entity) throws Exception {
            	log.debug("deleting City instance");
            	
	            if(entity==null){
	    			throw new ZMessManager().new NullEntityExcepcion("City");
	    		}
    	
                                if(entity.getCityId()==null){
                    throw new ZMessManager().new EmptyFieldException("cityId");
                    }
                        
            if(cityRepository.existsById(entity.getCityId())==false){
           		throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
        	} 
            
            	            findById(entity.getCityId()).ifPresent(entidad->{	            	
	                													List<Doctor> doctors = entidad.getDoctors();
							                    if(Utilities.validationsList(doctors)==true){
                       	 	throw new ZMessManager().new DeletingException("doctors");
                        }
	                													List<Patient> patients = entidad.getPatients();
							                    if(Utilities.validationsList(patients)==true){
                       	 	throw new ZMessManager().new DeletingException("patients");
                        }
	                	            });
                       

           
            
            cityRepository.deleteById(entity.getCityId());
            log.debug("delete City successful");
            
           
            	
            }
            
            @Override
			@Transactional(readOnly = false, propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
            public void deleteById(Integer id) throws Exception {            
            	log.debug("deleting City instance");
            	if(id==null){
            		throw new ZMessManager().new EmptyFieldException("cityId");
            	}
            	if(cityRepository.existsById(id)){
           			delete(cityRepository.findById(id).get());
       			}    
            }	
			
			@Override
			@Transactional(readOnly = false, propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
            public City update(City entity) throws Exception {

				log.debug("updating City instance");
				
	           
	            
	            	if(entity==null){
		    			throw new ZMessManager().new NullEntityExcepcion("City");
		    		}
		    		
	            validate(entity);
	            
	            
	            if(cityRepository.existsById(entity.getCityId())==false){
           			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
        		}	            
	
	            return cityRepository.save(entity);
	        
            }
			
			@Override
			@Transactional(readOnly=true)
            public Optional<City> findById(Integer cityId) {            
            	log.debug("getting City instance");
            	return cityRepository.findById(cityId);
            }
			
}
