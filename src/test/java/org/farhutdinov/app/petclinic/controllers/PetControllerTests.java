package org.farhutdinov.app.petclinic.controllers;

import org.assertj.core.util.Lists;
import org.farhutdinov.app.petclinic.controller.PetController;
import org.farhutdinov.app.petclinic.model.Owner;
import org.farhutdinov.app.petclinic.model.Pet;
import org.farhutdinov.app.petclinic.model.utilPet.PetType;
import org.farhutdinov.app.petclinic.model.utilPet.PetTypeFormatter;
import org.farhutdinov.app.petclinic.repositories.OwnerRepository;
import org.farhutdinov.app.petclinic.repositories.PetRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Set;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(value = PetController.class,
            includeFilters = @ComponentScan.Filter(
                            value = PetTypeFormatter.class,
                            type = FilterType.ASSIGNABLE_TYPE))
public class PetControllerTests {

    private static final int TEST_OWNER_ID = 1;
    private static final int TEST_PET_ID = 1;

    private Owner george;

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private PetRepository petRepository;

    @MockBean
    private OwnerRepository ownerRepository;

    @Before
    public void setUp() throws Exception {
        george = new Owner();
        george.setId(TEST_OWNER_ID);
        george.setFirstName("George");
        george.setLastName("Franklin");
        george.setAddress("110 W. Liberty St.");
        george.setCity("Madison");
        george.setTelephone("6085551023");


        PetType cat = new PetType();
        cat.setId(3);
        cat.setName("hamster");

        Pet pet = new Pet();
        pet.setId(TEST_PET_ID);
        pet.setType(cat);

        george.addPet(pet);
        given(petRepository.findPetTypes()).willReturn(Lists.newArrayList(cat));
        given(ownerRepository.findById(TEST_OWNER_ID)).willReturn(george);
        given(petRepository.findById(TEST_PET_ID)).willReturn(pet);

    }

    @Test
    public void testInitCreationForm() throws Exception {
        mockMvc.perform(get("/owners/{ownerId}/pets/new",TEST_OWNER_ID))
                .andExpect(status().isOk())
                .andExpect(view().name("pets/createPetForm"))
                .andExpect(model().attributeExists("pet"));
    }

    @Test
    public void testProcessCreationFormSuccess() throws Exception {
        mockMvc.perform(post("/owners/{ownerId}/pets/new",TEST_OWNER_ID)
        .param("name","Betty")
        .param("type","hamster")
        .param("birthDate","2015-02-12")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/{ownerId}"));
    }
    @Test
    public void testProcessCreationFormHasErrors() throws Exception {
        mockMvc.perform(post("/owners/{ownerId}/pets/new",TEST_OWNER_ID)
                .param("name","Betty")
                .param("birthDate","2015-02-12")
        )
                .andExpect(model().attributeHasNoErrors("owner"))
                .andExpect(model().attributeHasErrors("pet"))
                .andExpect(model().attributeHasFieldErrors("pet","type"))
                .andExpect(model().attributeHasFieldErrorCode("pet","type","required"))
                .andExpect(status().isOk())
                .andExpect(view().name("pets/createPetForm"));
    }
    @Test
    public void testInitUpdateForm() throws Exception {
        mockMvc.perform(get("/owners/{ownerId}/pets/{petId}/edit",TEST_OWNER_ID,TEST_PET_ID))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("pet"))
                .andExpect(view().name("pets/updatePetForm"));
    }

    @Test
    public void testUpdateFormSuccess() throws Exception {
        mockMvc.perform(post("/owners/{ownerId}/pets/{petId}/edit",TEST_OWNER_ID,TEST_PET_ID)
        .param("name","Betty")
        .param("type", "hamster")
        .param("birthDate","2015-02-12")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/{ownerId}"));
    }

}
