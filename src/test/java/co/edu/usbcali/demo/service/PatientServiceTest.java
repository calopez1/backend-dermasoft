package co.edu.usbcali.demo.service;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;


/**
* @author Zathura Code Generator Version 9.0 http://zathuracode.org
* www.zathuracode.org
*
*/
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PatientServiceTest {
    @Autowired
    private PatientService patientService;

    @Test
    @DisplayName("findAll")
    public void findAll() {
        //Arrange

        //Act

        //Assert
        assertNotNull(patientService);
    }

    @Test
    @DisplayName("save")
    public void save() throws Exception {
        //Arrange

        //Act

        //Assert
        assertNotNull(patientService);
    }

    @Test
    @DisplayName("delete")
    public void delete() throws Exception {
        //Arrange

        //Act

        //Assert
        assertNotNull(patientService);
    }

    @Test
    @DisplayName("deleteById")
    public void deleteById() throws Exception {
        //Arrange

        //Act

        //Assert
        assertNotNull(patientService);
    }

    @Test
    @DisplayName("update")
    public void update() throws Exception {
        //Arrange

        //Act

        //Assert
        assertNotNull(patientService);
    }

    @Test
    @DisplayName("findById")
    public void findById() throws Exception {
        //Arrange

        //Act

        //Assert
        assertNotNull(patientService);
    }

    @Test
    @DisplayName("count")
    public void count() throws Exception {
        //Arrange

        //Act

        //Assert
        assertNotNull(patientService);
    }
}
