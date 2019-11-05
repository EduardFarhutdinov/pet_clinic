package org.farhutdinov.app.petclinic.controller;

import org.farhutdinov.app.petclinic.model.Owner;
import org.farhutdinov.app.petclinic.model.Pet;
import org.farhutdinov.app.petclinic.model.utilPet.PetType;
import org.farhutdinov.app.petclinic.model.utilPet.PetValidator;
import org.farhutdinov.app.petclinic.repositories.OwnerRepository;
import org.farhutdinov.app.petclinic.repositories.PetRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Collection;

@Controller
@RequestMapping("/owners/{ownerId}")
public class PetController {

    private final PetRepository petRepository;
    private final OwnerRepository ownerRepository;

    public PetController(PetRepository petRepository , OwnerRepository ownerRepository) {
        this.petRepository = petRepository;
        this.ownerRepository = ownerRepository;
    }

    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable("ownerId") int ownerId){
        return ownerRepository.findById(ownerId);
    }
    @ModelAttribute("types")
    public Collection<PetType> populatePetTypes() {
        return this.petRepository.findPetTypes();
    }
    @InitBinder("owner")
    public void initOwnerBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @InitBinder("pet")
    public void initPetBinder(WebDataBinder dataBinder) {
        dataBinder.setValidator(new PetValidator());
    }

    @GetMapping("/pets/new")
    public String initCreationForm(Owner owner, ModelMap model){
        Pet newPet = new Pet();
        owner.addPet(newPet);
        model.put("pet",newPet);
        return "pets/createPetForm";
    }



    @PostMapping("/pets/new")
    public String processCreationForm(Owner owner, @Valid Pet newPet, BindingResult result,ModelMap modelMap){
        if (StringUtils.hasLength(newPet.getName()) && newPet.isNew() && owner.getPet(newPet.getName(), true) != null){
            result.rejectValue("name", "duplicate", "already exists");
        }
        owner.addPet(newPet);
        if(result.hasErrors()){
            modelMap.put("pet",newPet);
            return "pets/createPetForm";
        }else {
            petRepository.save(newPet);
            return "redirect:/owners/{ownerId}";
        }
    }

    @GetMapping("/pets/{petId}/edit")
    public String initUpdateForm(@PathVariable("petId") int petId,ModelMap modelMap){
        Pet editPet = petRepository.findById(petId);
        modelMap.addAttribute("pet",editPet);
        return "pets/updatePetForm";
    }

    @PostMapping("/pets/{petId}/edit")
    public String processUpdateForm(@Valid Pet pet , BindingResult result , Owner owner , ModelMap modelMap , @PathVariable int  petId){

        if(result.hasErrors()){
            pet.setOwner(owner);
            modelMap.put("pet",pet);
//            modelMap.put("error","is empty");
            return "pets/updatePetForm";
        }else {
            pet.setId(petId);
            owner.addPet(pet);
            petRepository.save(pet);
            return "redirect:/owners/{ownerId}";

    }
    }
}
