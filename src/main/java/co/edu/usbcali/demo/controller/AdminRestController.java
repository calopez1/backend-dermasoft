package co.edu.usbcali.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.usbcali.demo.domain.Admin;
import co.edu.usbcali.demo.dto.AdminDTO;
import co.edu.usbcali.demo.mapper.AdminMapper;
import co.edu.usbcali.demo.service.AdminService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Zathura Code Generator Version 9.0 http://zathuracode.org
 *         www.zathuracode.org
 *
 */
@RestController
@RequestMapping("/api/v1/admin")
@CrossOrigin(origins = "*")
@Slf4j
public class AdminRestController {
	@Autowired
	private AdminService adminService;
	@Autowired
	private AdminMapper adminMapper;

	@GetMapping(value = "/{adminIdentification}")
	public ResponseEntity<?> findById(@PathVariable("adminIdentification") String adminIdentification)
			throws Exception {
		log.debug("Request to findById() Admin");

		Admin admin = (adminService.findById(adminIdentification).isPresent() == true)
				? adminService.findById(adminIdentification).get()
				: null;

		return ResponseEntity.ok().body(adminMapper.adminToAdminDTO(admin));
	}

	@GetMapping()
	public ResponseEntity<?> findAll() throws Exception {
		log.debug("Request to findAll() Admin");

		return ResponseEntity.ok().body(adminMapper.listAdminToListAdminDTO(adminService.findAll()));
	}

	@GetMapping("/loginAdmin/{id}/{password}")
	public ResponseEntity<?> loginAdmin(@PathVariable("id") String id, @PathVariable("password") String password)
			throws Exception {
		Admin admin = (adminService.findByAdminIdAndPassword(id, password).isPresent() == true)
				? adminService.findByAdminIdAndPassword(id, password).get()
				: null;

		return ResponseEntity.ok().body(adminMapper.adminToAdminDTO(admin));

	}

	@PostMapping()
	public ResponseEntity<?> save(@Valid @RequestBody AdminDTO adminDTO) throws Exception {
		log.debug("Request to save Admin: {}", adminDTO);

		Admin admin = adminMapper.adminDTOToAdmin(adminDTO);
		admin = adminService.save(admin);

		return ResponseEntity.ok().body(adminMapper.adminToAdminDTO(admin));
	}

	@PutMapping()
	public ResponseEntity<?> update(@Valid @RequestBody AdminDTO adminDTO) throws Exception {
		log.debug("Request to update Admin: {}", adminDTO);

		Admin admin = adminMapper.adminDTOToAdmin(adminDTO);
		admin = adminService.update(admin);

		return ResponseEntity.ok().body(adminMapper.adminToAdminDTO(admin));
	}

	@DeleteMapping(value = "/{adminIdentification}")
	public ResponseEntity<?> delete(@PathVariable("adminIdentification") String adminIdentification) throws Exception {
		log.debug("Request to delete Admin");

		adminService.deleteById(adminIdentification);

		return ResponseEntity.ok().build();
	}

	@GetMapping(value = "/count")
	public ResponseEntity<?> count() {
		return ResponseEntity.ok().body(adminService.count());
	}
}
