package org.farhutdinov.app.petclinic.controller;

import org.farhutdinov.app.petclinic.model.Owner;
import org.farhutdinov.app.petclinic.repositories.OwnerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Controller
public class OwnerController {

    private static final String VIEWS_OWNER_CREATE_OR_UPDATE_FORM = "owners/createOrUpdateOwnerForm";
    private final OwnerRepository ownerRepository;

    public OwnerController(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @GetMapping("/")
    public String welcomePage(){
        return "welcome";
    }

    @GetMapping("/owners/new")
    public String initCreationForm(Map<String,Object> model){
        Owner owner = new Owner();
        model.put("owner",owner);
        return "createOrUpdateOwnerForm";
    }
    @PostMapping("/owners/new")
    public String processCreationForm(@Valid Owner owner, BindingResult result, RedirectAttributes attributes){
        System.out.println(owner);
        if(result.hasErrors()){
            attributes.addAttribute("firstname",owner.getFirstName());
            attributes.addAttribute("lastname",owner.getLastName());
            return "createOrUpdateOwnerForm";
        }else {
            ownerRepository.save(owner);
            return "redirect:/owners/" + owner.getId();
        }
    }

    @GetMapping("/owners/find")
    public String initFindForm(Map<String,Object> model){

        model.put("owner",new Owner());
        return "findOwners";

    }


    @GetMapping("/owners")
    public String processFindForm(Owner owner, BindingResult result, Map<String, Object> model) {

        if (owner.getLastName() == null) {
            owner.setLastName(""); // empty string signifies broadest possible search
        }

        Collection<Owner> results = this.ownerRepository.findByLastName(owner.getLastName());
        if (results.isEmpty()) {
            // no owners found
            result.rejectValue("lastName", "notFound", "not found");
            return "findOwners";
        } else if (results.size() == 1) {
            // 1 owner found
            owner = results.iterator().next();
            return "redirect:/owners/" + owner.getId();
        } else {
            // multiple owners found
            model.put("owners", results);
            return "ownersList";
        }
    }

    @GetMapping("/owners/{ownerId}/edit")
    public String initUpdateOwnerForm(@PathVariable("ownerId") int ownerId, Model model) {
        Owner owner = ownerRepository.findById(ownerId);
        model.addAttribute("owner",owner);
        return "createOrUpdateOwnerForm";
    }
    @PostMapping("/owners/{ownerId}/edit")
    public String processUpdateOwnerForm(@Valid Owner owner, BindingResult result, @PathVariable("ownerId") int ownerId){
        if(result.hasErrors()){
            return "createOrUpdateOwnerForm";
        }else {
            owner.setId(ownerId);
            ownerRepository.save(owner);
            return "redirect:/owners/{ownerId}";
        }
    }
    @GetMapping("/owners/{ownerId}")
    public ModelAndView showOwner(@PathVariable("ownerId") int ownerId) {
        ModelAndView mav = new ModelAndView("ownerDetails");
        mav.addObject(ownerRepository.findById(ownerId));
        return mav;
    }
}
