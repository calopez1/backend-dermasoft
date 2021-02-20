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
public class EpsServiceImpl implements EpsService{

	@Autowired
	private EpsRepository epsRepository;
	
	@Autowired
	private Validator validator;
                
    @Override        		
	public void validate(Eps eps)throws ConstraintViolationException{		
		
		Set<ConstraintViolation<Eps>> constraintViolations =validator.validate(eps);
		 if (!constraintViolations.isEmpty()) {			
			throw new ConstraintViolationException(constraintViolations);
		}
		
	}
	
	@Override
	@Transactional(readOnly=true)
	public Long count(){
	 	return epsRepository.count();
	}

	@Override
	@Transactional(readOnly=true)
	public List<Eps> findAll(){
		log.debug("finding all Eps instances");
       	return epsRepository.findAll();
    }
			
			
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED,rollbackFor = Exception.class)			
    public Eps save(Eps entity) throws Exception {
		log.debug("saving Eps instance");
	   
	    
	    if(entity==null){
			throw new ZMessManager().new NullEntityExcepcion("Eps");
		}
		
		validate(entity);	
	
		if(epsRepository.existsById(entity.getEpsId())){
           throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
        }    
	
	    return epsRepository.save(entity);
	   
    }
			
			
			@Override
			@Transactional(readOnly = false, propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
            public void delete(Eps entity) throws Exception {
            	log.debug("deleting Eps instance");
            	
	            if(entity==null){
	    			throw new ZMessManager().new NullEntityExcepcion("Eps");
	    		}
    	
                                if(entity.getEpsId()==null){
                    throw new ZMessManager().new EmptyFieldException("epsId");
                    }
                        
            if(epsRepository.existsById(entity.getEpsId())==false){
           		throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
        	} 
            
            	            findById(entity.getEpsId()).ifPresent(entidad->{	            	
	                													List<Patient> patients = entidad.getPatients();
							                    if(Utilities.validationsList(patients)==true){
                       	 	throw new ZMessManager().new DeletingException("patients");
                        }
	                	            });
                       

           
            
            epsRepository.deleteById(entity.getEpsId());
            log.debug("delete Eps successful");
            
           
            	
            }
            
            @Override
			@Transactional(readOnly = false, propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
            public void deleteById(Integer id) throws Exception {            
            	log.debug("deleting Eps instance");
            	if(id==null){
            		throw new ZMessManager().new EmptyFieldException("epsId");
            	}
            	if(epsRepository.existsById(id)){
           			delete(epsRepository.findById(id).get());
       			}    
            }	
			
			@Override
			@Transactional(readOnly = false, propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
            public Eps update(Eps entity) throws Exception {

				log.debug("updating Eps instance");
				
	           
	            
	            	if(entity==null){
		    			throw new ZMessManager().new NullEntityExcepcion("Eps");
		    		}
		    		
	            validate(entity);
	            
	            
	            if(epsRepository.existsById(entity.getEpsId())==false){
           			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
        		}	            
	
	            return epsRepository.save(entity);
	        
            }
			
			@Override
			@Transactional(readOnly=true)
            public Optional<Eps> findById(Integer epsId) {            
            	log.debug("getting Eps instance");
            	return epsRepository.findById(epsId);
            }
			
}
