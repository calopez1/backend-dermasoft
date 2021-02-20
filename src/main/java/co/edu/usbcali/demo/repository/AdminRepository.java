package co.edu.usbcali.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.edu.usbcali.demo.domain.Admin;

/**
 * @author Zathura Code Generator Version 9.0 http://zathuracode.org
 *         www.zathuracode.org
 *
 */
public interface AdminRepository extends JpaRepository<Admin, String> {
}
