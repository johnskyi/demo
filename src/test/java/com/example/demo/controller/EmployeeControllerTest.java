package com.example.demo.controller;

import com.example.demo.data.dto.EmployeeDto;
import com.example.demo.service.api.IEmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {EmployeeController.class})
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
class EmployeeControllerTest {
    @Autowired
    private EmployeeController employeeController;

    @MockBean
    private IEmployeeService iEmployeeService;

    @Test
    void deleteEmployee() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/employee/{id}", 1L);
        MockMvcBuilders.standaloneSetup(employeeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("1"));
    }

    @Test
    void getByIdEmployee() throws Exception {
        when(iEmployeeService.getById(anyLong())).thenReturn(new EmployeeDto());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/employee/{id}", 1L);
        MockMvcBuilders.standaloneSetup(employeeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":null,\"secondName\":null,\"name\":null,\"patronymic\":null,\"email\":null,\"sex\":null,\"worths\":[]}"));
    }
}

