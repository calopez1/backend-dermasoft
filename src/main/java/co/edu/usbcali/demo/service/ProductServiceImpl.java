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
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private Validator validator;
                
    @Override        		
	public void validate(Product product)throws ConstraintViolationException{		
		
		Set<ConstraintViolation<Product>> constraintViolations =validator.validate(product);
		 if (!constraintViolations.isEmpty()) {			
			throw new ConstraintViolationException(constraintViolations);
		}
		
	}
	
	@Override
	@Transactional(readOnly=true)
	public Long count(){
	 	return productRepository.count();
	}

	@Override
	@Transactional(readOnly=true)
	public List<Product> findAll(){
		log.debug("finding all Product instances");
       	return productRepository.findAll();
    }
			
			
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED,rollbackFor = Exception.class)			
    public Product save(Product entity) throws Exception {
		log.debug("saving Product instance");
	   
	    
	    if(entity==null){
			throw new ZMessManager().new NullEntityExcepcion("Product");
		}
		
		validate(entity);	
	
		if(productRepository.existsById(entity.getProductId())){
           throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
        }    
	
	    return productRepository.save(entity);
	   
    }
			
			
			@Override
			@Transactional(readOnly = false, propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
            public void delete(Product entity) throws Exception {
            	log.debug("deleting Product instance");
            	
	            if(entity==null){
	    			throw new ZMessManager().new NullEntityExcepcion("Product");
	    		}
    	
                                if(entity.getProductId()==null){
                    throw new ZMessManager().new EmptyFieldException("productId");
                    }
                        
            if(productRepository.existsById(entity.getProductId())==false){
           		throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
        	} 
            
            	            findById(entity.getProductId()).ifPresent(entidad->{	            	
	                													List<Treatment> treatments = entidad.getTreatments();
							                    if(Utilities.validationsList(treatments)==true){
                       	 	throw new ZMessManager().new DeletingException("treatments");
                        }
	                	            });
                       

           
            
            productRepository.deleteById(entity.getProductId());
            log.debug("delete Product successful");
            
           
            	
            }
            
            @Override
			@Transactional(readOnly = false, propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
            public void deleteById(Integer id) throws Exception {            
            	log.debug("deleting Product instance");
            	if(id==null){
            		throw new ZMessManager().new EmptyFieldException("productId");
            	}
            	if(productRepository.existsById(id)){
           			delete(productRepository.findById(id).get());
       			}    
            }	
			
			@Override
			@Transactional(readOnly = false, propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
            public Product update(Product entity) throws Exception {

				log.debug("updating Product instance");
				
	           
	            
	            	if(entity==null){
		    			throw new ZMessManager().new NullEntityExcepcion("Product");
		    		}
		    		
	            validate(entity);
	            
	            
	            if(productRepository.existsById(entity.getProductId())==false){
           			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
        		}	            
	
	            return productRepository.save(entity);
	        
            }
			
			@Override
			@Transactional(readOnly=true)
            public Optional<Product> findById(Integer productId) {            
            	log.debug("getting Product instance");
            	return productRepository.findById(productId);
            }
			
}
