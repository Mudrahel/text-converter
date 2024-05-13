package ryhor.mudrahel.textconverter.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import ryhor.mudrahel.textconverter.service.EncoderService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.mockito.Mockito.when;

@WebMvcTest(ConverterController.class)
class ConverterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EncoderService encoderService;

    @Test
    void testEncode() {
        // Given
        String inputText = "Hello, World!";
        String encodedText = "SGVsbG8sIFdvcmxkIQ==";
        when(encoderService.encode(inputText)).thenReturn(encodedText);

        // When & Then
        try {
            mockMvc.perform(post("/encode")
                    .param("textToEncode", inputText))
                    .andExpect(status().isOk())
                    .andExpect(content().string(encodedText));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}