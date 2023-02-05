package ru.alex.hotels.controller;


import org.junit.jupiter.api.Test;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import ru.alex.hotels.controller.controllerTestConfig.Path;
import ru.alex.hotels.controller.controllerTestConfig.RootConfigController;
import ru.alex.hotels.dto.DirectorDto;
import ru.alex.hotels.service.DirectorService;

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

        DirectorDto directorDtoAfterSave = testDirectorAfterCreate();

        when(directorService.addDirector(any(DirectorDto.class))).thenReturn(directorDtoAfterSave);

        mockMvc.perform(post(getPath())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(testDirector())))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.fcs").value(directorDtoAfterSave.getFcs()))
                .andExpect(jsonPath("$.phone").value(directorDtoAfterSave.getPhone()));
    }

}