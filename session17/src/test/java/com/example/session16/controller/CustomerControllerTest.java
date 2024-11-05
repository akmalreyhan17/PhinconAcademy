package com.example.session16.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.session16.model.Customer;
import com.example.session16.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import static org.mockito.ArgumentMatchers.any;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.restdocs.headers.HeaderDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.*;

import java.util.NoSuchElementException;

@ExtendWith(RestDocumentationExtension.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @MockBean
    CustomerService customerService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp(WebApplicationContext webApplicationContext,
            RestDocumentationContextProvider restDocumentation) {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation)
                        .operationPreprocessors()
                        .withRequestDefaults(prettyPrint())
                        .withResponseDefaults(prettyPrint()))
                .build();
    }

    @Test
    void testGetCustomerById() throws Exception {
        final Long id = 1L;

        Customer customer = new Customer();
        customer.setId(id);
        customer.setName("Burhan");
        customer.setEmail("burhan@mail.com");

        Mockito.when(customerService.getCustomer(id)).thenReturn(customer);

        mockMvc.perform(get("/api/customer/{id}", id)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("customer-get-by-id",
                        pathParameters(parameterWithName("id").description("Customer id"))));
    }

    @Test
    void testGetCustomerByIdAlt() throws Exception {
        final Long id = 1L;

        Customer customer = new Customer();
        customer.setId(id);
        customer.setName("Burhan");
        customer.setEmail("burhan@mail.com");

        Mockito.when(customerService.getCustomer(id)).thenReturn(customer);

        mockMvc.perform(get("/api/customer/repo/{id}", id)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("customer-hypermedia",
                        links(halLinks(), 
                        linkWithRel("self").description("Get this customer"),
                        linkWithRel("next-customer").description("Get the next customer"))));
    }

    @Test
    void testGetCustomerByName() throws Exception {
        final String name = "Burhan";

        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("Burhan");
        customer.setEmail("burhan@mail.com");

        Mockito.when(customerService.getCustomerByName(name)).thenReturn(customer);

        mockMvc.perform(get("/api/customer?name=" + name)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("customer-get-by-name",
                        queryParameters(parameterWithName("name").description("Customer name"))));
    }

    @Test
    void testAddCustomer() throws Exception {

        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("Burhan");
        customer.setEmail("burhan@mail.com");

        Mockito.when(customerService.addCustomer(any(Customer.class))).thenReturn(customer);

        String customerJSON = new ObjectMapper().writeValueAsString(customer);

        mockMvc.perform(post("/api/customer")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(customerJSON))
                .andExpect(status().isOk())
                .andDo(document("customer-add",
                        requestFields(customerAdd),
                        responseFields(customerAdd),
                        responseHeaders(headerWithName("SignedIn")
                                .description("Is user signed in or not"))));
    }

    private FieldDescriptor[] customerAdd = {
            fieldWithPath("id").description("Customer Id"),
            fieldWithPath("name").description("Customer's name"),
            fieldWithPath("email").description("Customer's email")
    };

    @Test
    void testGetCustomerByIdNull() throws Exception {
        final Long id = 1L;

        Mockito.when(customerService.getCustomer(id)).thenThrow(NoSuchElementException.class);

        mockMvc.perform(get("/api/customer/{id}", id)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(204))
                .andDo(document("customer-get-by-id-null"));
    }
}
