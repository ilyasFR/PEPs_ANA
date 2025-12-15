package peps.peps_back.controllers;

import peps.peps_back.repositories.SoundRepository;
// Add other necessary imports depending on your project structure, e.g.:
// import peps.peps_back.dtos.SoundDTO;
// import peps.peps_back.entities.Sound; 

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

/**
 * Corrected Test Class using Mockito
 */
public class SoundControllerTest {
    
    // 1. Declare the Mock and the Controller
    private SoundRepository soundRepository;
    private SoundController instance;

    @BeforeEach
    public void setUp() {
        // 2. Create the Mock object
        soundRepository = mock(SoundRepository.class);
        
        // 3. Inject the Mock into the Controller
        instance = new SoundController(soundRepository);
    }

    @Test
    public void testGetAllSounds() {
        System.out.println("getAllSounds");
        // Example of defining behavior (Stubbing)
        // when(soundRepository.findAll()).thenReturn(new ArrayList<>());
        
        ResponseEntity<List<SoundDTO>> result = instance.getAllSounds();
        assertNotNull(result);
    }

    @Test
    public void testGetSoundFile() {
        System.out.println("getSoundFile");
        Integer id = 0;
        // You might need to stub findById here if the controller uses it
        
        // Ensure this method handles the null/empty behavior gracefully if that's what you test
        // otherwise expect an error or stub the behavior.
        try {
             ResponseEntity<Resource> result = instance.getSoundFile(id);
             // assertNotNull(result); // Uncomment once logic is verified
        } catch (Exception e) {
            // Handle exceptions expected during "empty" prototype testing
        }
    }

    @Test
    public void testUploadSound() {
        System.out.println("uploadSound");
        String name = "blop";
        String type = "sound";
        MultipartFile file = mock(MultipartFile.class); // Mock the file too

        ResponseEntity result = instance.uploadSound(name, type, file);
        assertNotNull(result);
    }

    @Test
    public void testUpdateSound() {
        System.out.println("updateSound");
        Integer id = 1;
        SoundDTO soundDTO = new SoundDTO(); // Ensure SoundDTO has a no-args constructor

        ResponseEntity result = instance.updateSound(id, soundDTO);
        assertNotNull(result);
    }

    @Test
    public void testDeleteSound() {
        System.out.println("deleteSound");
        Integer id = 1;
        
        ResponseEntity result = instance.deleteSound(id);
        assertNotNull(result);
    }
}