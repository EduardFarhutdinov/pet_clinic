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
    public String initCreationForm(Owner owner, Model model){
        Pet newPet = new Pet();
        owner.addPet(newPet);
        model.addAttribute("newPet",newPet);
        return "pets/createPetForm";
    }



    @PostMapping("/pets/new")
    public String processCreationForm(Owner owner, @Valid Pet newPet, BindingResult result,ModelMap modelMap){
        if (StringUtils.hasLength(newPet.getName()) && newPet.isNew() && owner.getPet(newPet.getName(), true) != null){
            result.rejectValue("name", "duplicate", "already exists");
        }
        owner.addPet(newPet);
        if(result.hasErrors()){
            modelMap.put("newPet",newPet);
            System.out.println(result.getAllErrors());
            System.out.println(newPet);
            modelMap.addAttribute("error",result);
            return "pets/createPetForm";
        }else {
            petRepository.save(newPet);
            return "redirect:/owners/{ownerId}";
        }
    }

    @GetMapping("/pets/{petId}/edit")
    public String initUpdateForm(@PathVariable("petId") int petId,Model modelMap){
        Pet editPet = petRepository.findById(petId);
        modelMap.addAttribute("editPet",editPet);
        modelMap.addAttribute("ownerEditPEt",editPet.getOwner());
//        modelMap.put("editPet",editPet);
        return "pets/updatePetForm";
    }

    @PostMapping("/pets/{petId}/edit")
    public String processUpdateForm(@Valid Pet editPet,BindingResult result,
                                    @PathVariable("petId") int petId,Owner owner, ModelMap modelMap,
                                    @RequestParam String name,
                                    @RequestParam String birthDate,
                                    @RequestParam PetType type
                                    ){

//          Pet editPet = petRepository.findById(petId);

        if(result.hasErrors()){
            editPet.setOwner(owner);
            modelMap.put("pet",editPet);
            return "pets/updatePetForm";
        }else {
            editPet.setId(petId);
            editPet.setName(name);
            editPet.setBirthDate(LocalDate.parse(birthDate));
            editPet.setType(type);
            owner.addPet(editPet);
            petRepository.save(editPet);
            return "redirect:/owners/{ownerId}";

    }
    }
}
