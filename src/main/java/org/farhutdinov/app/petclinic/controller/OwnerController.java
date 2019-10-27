package org.farhutdinov.app.petclinic.controller;

import org.farhutdinov.app.petclinic.model.Owner;
import org.farhutdinov.app.petclinic.repositories.OwnerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Map;

@Controller
public class OwnerController {

    private static final String VIEWS_OWNER_CREATE_OR_UPDATE_FORM = "owners/createOrUpdateOwnerForm";
    private final OwnerRepository ownerRepository;

    public OwnerController(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/")
    public String welcomePage(){
        return "welcome";
    }

    @GetMapping("/owners/new")
    public String initCreationForm(Map<String,Object> model){
        Owner owner = new Owner();
        model.put("owner",owner);
        return "owners/createOrUpdateOwnerForm";
    }
    @PostMapping("/owners/new")
    public String processCreationForm(@Valid Owner owner, BindingResult result, RedirectAttributes attributes){
        System.out.println(owner);
        if(result.hasErrors()){
//            attributes.addAttribute("firstname",owner.getFirstName());
//            attributes.addAttribute("lastname",owner.getLastName());
            return "owners/createOrUpdateOwnerForm";
        }else {
            ownerRepository.save(owner);
            return "redirect:/owners/" + owner.getId();
        }
    }

    @GetMapping("/owners/find")
    public String initFindForm(Map<String,Object> model){

        model.put("owner",new Owner());
        return "owners/findOwners";

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
            return "owners/findOwners";
        } else if (results.size() == 1) {
            // 1 owner found
            owner = results.iterator().next();
            return "redirect:/owners/" + owner.getId();
        } else {
            // multiple owners found
            model.put("owners", results);
            return "owners/ownersList";
        }
    }

    @GetMapping("/owners/{ownerId}/edit")
    public String initUpdateOwnerForm(@PathVariable("ownerId") int ownerId, ModelMap model) {
        Owner owner = ownerRepository.findById(ownerId);
        model.addAttribute("owner",owner);
        model.addAttribute("telephone",owner.getTelephone());
        return "owners/updateOwnerForm";
    }
    @PostMapping("/owners/{ownerId}/edit")
    public String processUpdateOwnerForm(@Valid Owner owner,    BindingResult result,
                                         @PathVariable("ownerId") int ownerId,
                                         @RequestParam String firstName,
                                         @RequestParam String lastName,
                                         @RequestParam String address,
                                         @RequestParam String city,
                                         @RequestParam String telephone)
    {
        owner.setId(ownerId);
        if(result.hasErrors()){
            System.out.println(owner);
            return "owners/updateOwnerForm";
        }else {
//            owner.setFirstName(firstName);
//            owner.setLastName(lastName);
//            owner.setAddress(address);
//            owner.setCity(city);
//            owner.setTelephone(telephone);
            ownerRepository.save(owner);
            System.out.println(owner);
            return "redirect:/owners/{ownerId}";
        }
    }
    @GetMapping("/owners/{ownerId}")
    public ModelAndView showOwner(@PathVariable("ownerId") int ownerId) {
        ModelAndView mav = new ModelAndView("owners/ownerDetails");
        mav.addObject(ownerRepository.findById(ownerId));
        return mav;
    }
}
