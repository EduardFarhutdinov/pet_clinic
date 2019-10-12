package org.farhutdinov.app.petclinic.owner;

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
        george.setTelephone("60855510233333333");

        given(this.owners.findById(TEST_OWNER_ID)).willReturn(george);
    }

    @Test
    public void testInitCreationForm() throws Exception {
        mockMvc.perform(get("/owners/new"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("owner"))
                .andExpect(view().name("createOrUpdateOwnerForm"));
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
}
