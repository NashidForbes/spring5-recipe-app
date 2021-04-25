package guru.springframework.controllers;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.services.interfaces.ImageService;
import guru.springframework.services.interfaces.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ImageControllerTest {

    @Mock
    ImageService imageService;

    @Mock
    RecipeService recipeService;

    ImageController imageController;

    MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.imageController = new ImageController(imageService, recipeService);
        mockMvc = MockMvcBuilders.standaloneSetup(imageController)
                // need to refer to new exception handlers
                // e.g. ControllerExceptionHandler.java
                .setControllerAdvice(new ControllerExceptionHandler())
                .build();
    }

    @Test
    public void showUploadForm() throws Exception {
        // given
        Mono<RecipeCommand> command = new RecipeCommand();
        when(recipeService.findCommandById(anyString())).thenReturn(command);
        //when
        mockMvc.perform(get("/recipe/1/image"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("recipe"));

    }

    @Test
    public void handleImagePost() throws Exception {
        MockMultipartFile multipartFile = new MockMultipartFile("imagefile", "testing" +
                ".txt", "text/plain", "Spring Framework Guru".getBytes());
        mockMvc.perform(multipart("/recipe/1/image").file(multipartFile))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "/recipe/1/show"));
        // since it's void can only test how many times it was called
        verify(imageService, times(1)).saveImageFile(anyString(), any());
    }


    @Test
    public void renderImageFromDB() throws Exception {
        //
        Mono<RecipeCommand> recipeCommand = new RecipeCommand();
        recipeCommand.setId("1");
        String s = "fake image text";
        Byte[] bytesBoxed = new Byte[s.getBytes().length];
        int i = 0;
        for (byte primByte : s.getBytes()) {
            bytesBoxed[i++] = primByte;
        }
        recipeCommand.setImage(bytesBoxed);
        when(recipeService.findCommandById(anyString())).thenReturn(recipeCommand);
        // when
        MockHttpServletResponse response = mockMvc.perform(get("/recipe/1/recipeimage"))
                .andExpect(status().isOk())
                .andReturn().getResponse();
        byte[] responseBytes = response.getContentAsByteArray();
        assertEquals(s.getBytes().length, responseBytes.length);

    }

    @Test
    public void testGetImageNumberFormatException() throws Exception {

        mockMvc.perform(get("/recipe/asdf/recipeimage"))
                .andExpect(status().isBadRequest())
                .andExpect(view().name("400error"));
    }
}