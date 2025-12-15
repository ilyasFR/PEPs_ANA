/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package peps.peps_back.controllers;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Clément
 */
public class SoundControllerTest {
    
    public SoundControllerTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getAllSounds method, of class SoundController.
     */
    @Test
    public void testGetAllSounds() {
        System.out.println("getAllSounds");
        SoundController instance = new SoundController();
        ResponseEntity<List<SoundDTO>> expResult = null;
        ResponseEntity<List<SoundDTO>> result = instance.getAllSounds();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSoundFile method, of class SoundController.
     */
    @Test
    public void testGetSoundFile() {
        System.out.println("getSoundFile");
        Integer id = 0;
        SoundController instance = new SoundController();
        ResponseEntity<Resource> expResult = null;
        ResponseEntity<Resource> result = instance.getSoundFile(id);
        assertEquals(expResult, result);
    }

    /**
     * Test of uploadSound method, of class SoundController.
     */
    @Test
    public void testUploadSound() {
        System.out.println("uploadSound");
        String name = "blop";
        String type = "sound";
        MultipartFile file = null;
        SoundController instance = new SoundController();
        ResponseEntity expResult = null;
        ResponseEntity result = instance.uploadSound(name, type, file);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateSound method, of class SoundController.
     */
    @Test
    public void testUpdateSound() {
        System.out.println("updateSound");
        Integer id = null;
        SoundDTO soundDTO = null;
        SoundController instance = new SoundController();
        ResponseEntity expResult = null;
        ResponseEntity result = instance.updateSound(id, soundDTO);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteSound method, of class SoundController.
     */
    @Test
    public void testDeleteSound() {
        System.out.println("deleteSound");
        Integer id = null;
        SoundController instance = new SoundController();
        ResponseEntity expResult = null;
        ResponseEntity result = instance.deleteSound(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
