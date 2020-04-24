package com.alward.trainings.sfgpetclinic.controllers;

import com.alward.trainings.sfgpetclinic.model.Pet;
import com.alward.trainings.sfgpetclinic.model.Visit;
import com.alward.trainings.sfgpetclinic.services.PetService;
import com.alward.trainings.sfgpetclinic.services.VisitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class VisitControllerTest {

    private static final LocalDate PET_BIRTHDAY = LocalDate.of(2020, 4, 3);

    @Mock
    private VisitService visitService;

    @Mock
    private PetService petService;

    private MockMvc mockMvc;

    @InjectMocks
    private VisitController visitController;

    private Pet pet;
    private Set<Visit> visits;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(visitController).build();

        pet = Pet.builder().id(1L).birthDate(PET_BIRTHDAY).build();
        visits = new HashSet<>();
        visits.add(Visit.builder().id(1L).build());
        visits.add(Visit.builder().id(2L).build());
    }

    @Test
    void testInitNewVisitForm() throws Exception {
        when(petService.findById(anyLong())).thenReturn(pet);
        when(visitService.findByPetId(anyLong())).thenReturn(visits);

        mockMvc.perform(get("/owners/1/pets/1/visits/new"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("pet"))
                .andExpect(view().name("pets/createOrUpdateVisitForm"));

        verify(petService, times(1)).findById(anyLong());
    }

    @Test
    void testProcessNewVisitForm() throws Exception {
        when(petService.findById(anyLong())).thenReturn(pet);
        when(visitService.findByPetId(anyLong())).thenReturn(visits);

        mockMvc.perform(post("/owners/1/pets/1/visits/new")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("petId", "1")
                .param("birthDate", PET_BIRTHDAY.toString()))
                .andExpect(status().is3xxRedirection())
                .andExpect(model().attributeExists("pet"))
                .andExpect(view().name("redirect:/owners/{ownerId}"));

        verify(visitService, times(1)).save(any());
    }
}