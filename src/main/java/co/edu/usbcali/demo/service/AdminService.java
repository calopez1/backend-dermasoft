package co.edu.usbcali.demo.service;

import java.util.Optional;

import co.edu.usbcali.demo.domain.Admin;


/**
* @author Zathura Code Generator Version 9.0 http://zathuracode.org
* www.zathuracode.org
*
*/
public interface AdminService extends GenericService<Admin, String> {
	//Método login admin
	public Optional<Admin> findByAdminIdAndPassword(String admin_id, String password);
}
