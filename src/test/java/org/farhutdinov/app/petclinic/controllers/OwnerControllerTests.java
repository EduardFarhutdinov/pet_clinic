package org.farhutdinov.app.petclinic.controllers;

import org.assertj.core.util.Lists;
import org.farhutdinov.app.petclinic.controller.OwnerController;
import org.farhutdinov.app.petclinic.model.Owner;
import org.farhutdinov.app.petclinic.repositories.OwnerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(OwnerController.class)
public class OwnerControllerTests {
    private static final int TEST_OWNER_ID = 1;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OwnerRepository owners;

    private Owner george;

    @Before
    public void setup() {
        george = new Owner();
            george.setId(TEST_OWNER_ID);
            george.setFirstName("George");
            george.setLastName("Franklin");
            george.setAddress("110 W. Liberty St.");
            george.setCity("Madison");
            george.setTelephone("6085551023");

        given(this.owners.findById(TEST_OWNER_ID)).willReturn(george);
    }

    @Test
    public void testInitCreationForm() throws Exception {
        mockMvc.perform(get("/owners/new"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("owner"))
                .andExpect(view().name("owners/createOrUpdateOwnerForm"));
    }
    @Test
    public void testProcessCreationFormSuccess() throws Exception {
        mockMvc.perform(post("/owners/new")
                .param("firstName", "Joe")
                .param("lastName", "Bloggs")
                .param("address", "123 Caramel Street")
                .param("city", "London")
                .param("telephone", "01316761638")
        )
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void testProcessCreationFormHasErrors() throws Exception {
        mockMvc.perform(post("/owners/new")
        .param("firstName","Joe")
        .param("lastName","Bloogs")
        .param("city","London")
        )
        .andExpect(status().isOk())
        .andExpect(model().attributeHasErrors("owner"))
        .andExpect(model().attributeHasFieldErrors("owner", "address"))
        .andExpect(model().attributeHasFieldErrors("owner", "telephone"))
        .andExpect(view().name("owners/createOrUpdateOwnerForm"));

    }

    @Test
    public void testInitFindForm() throws Exception {
        mockMvc.perform(get("/owners"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("owner"))
                .andExpect(view().name("owners/findOwners"));

    }

    @Test
    public void testProcessFindFormSuccess() throws Exception {
       Owner george1 = new Owner();
        george1.setId(2);
        george1.setFirstName("Georg1e");
        george1.setLastName("Frankli2n");
        george1.setAddress("110 W. Liberty St.");
        george1.setCity("Madison");
        george1.setTelephone("6085551023");

        given(owners.findByLastName("")).willReturn(Lists.newArrayList(george,george1));
        mockMvc.perform(get("/owners"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/ownersList"));
    }

    @Test
    public void testProcessFindFormByLastName() throws Exception {
        given(this.owners.findByLastName(george.getLastName())).willReturn(Lists.newArrayList(george));
        mockMvc.perform(get("/owners")
                .param("lastName", "Franklin")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/" + TEST_OWNER_ID));
    }

    @Test
    public void testInitUpdateForm() throws Exception {
        mockMvc.perform(get("/owners/{ownerId}/edit",TEST_OWNER_ID))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("owner"))
                .andExpect(model().attribute("owner", hasProperty("lastName", is("Franklin"))))
                .andExpect(model().attribute("owner", hasProperty("firstName", is("George"))))
                .andExpect(model().attribute("owner", hasProperty("address", is("110 W. Liberty St."))))
                .andExpect(model().attribute("owner", hasProperty("city", is("Madison"))))
                .andExpect(model().attribute("owner", hasProperty("telephone", is("6085551023"))))
                .andExpect(view().name("owners/updateOwnerForm"));
    }

    @Test
    public void testProcessUpdateOwnerFormSuccess() throws Exception {
        mockMvc.perform(post("/owners/{ownerId}/edit",TEST_OWNER_ID)
                .param("firstName", "Joe")
                .param("lastName", "Bloggs")
                .param("address", "123 Caramel Street")
                .param("city", "London")
                .param("telephone", "01616291589"))
            .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/{ownerId}"));

    }

    @Test
    public void testProcessUpdateOwnerFormHasErrors() throws Exception {
        mockMvc.perform(post("/owners/{ownerId}/edit",TEST_OWNER_ID)
                .param("firstName", "Joe")
                .param("lastName", "Bloggs")
                .param("address", "123 Caramel Street")
                .param("city","Dsadasd")
                .param("telephone","")
        )
                .andExpect(status().isOk())
                .andExpect(model().attributeHasErrors("owner"))
                .andExpect(model().attributeHasFieldErrors("owner","telephone"))
                .andExpect(view().name("owners/updateOwnerForm"));

    }

    @Test
    public void testShowOwner() throws Exception {
        mockMvc.perform(get("/owners/{ownerId}",TEST_OWNER_ID))
                .andExpect(status().isOk())
                .andExpect(model().attribute("owner",hasProperty("firstName",is("George"))))
                .andExpect(model().attribute("owner",hasProperty("lastName",is("Franklin"))))
                .andExpect(model().attribute("owner",hasProperty("address",is("110 W. Liberty St."))))
                .andExpect(model().attribute("owner",hasProperty("city",is("Madison"))))
                .andExpect(model().attribute("owner",hasProperty("telephone",is("6085551023"))))
                .andExpect(view().name("owners/ownerDetails"));
    }
}
