package ru.alex.hotels.controllers;


import org.junit.jupiter.api.Test;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import ru.alex.hotels.controllers.controllerTestConfig.Path;
import ru.alex.hotels.controllers.controllerTestConfig.RootConfigController;
import ru.alex.hotels.services.DirectorService;
import ru.alex.hotels.tdo.Director;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.alex.hotels.dataForTests.DirectorDataTest.testDirector;
import static ru.alex.hotels.dataForTests.DirectorDataTest.testDirectorAfterCreate;

@WebMvcTest(DirectorController.class)
class DirectorTestController extends RootConfigController implements Path {
    @Override
    public String getPath() {
        return "/director";
    }

    @MockBean
    private DirectorService directorService;

    @Test
    void testAddDirector() throws Exception {

        Director directorAfterSave = testDirectorAfterCreate();

        when(directorService.addDirector(any(Director.class))).thenReturn(directorAfterSave);

        mockMvc.perform(post(getPath())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(testDirector())))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.fcs").value(directorAfterSave.getFcs()))
                .andExpect(jsonPath("$.phone").value(directorAfterSave.getPhone()));
    }

}