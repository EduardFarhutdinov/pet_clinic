package org.farhutdinov.app.petclinic.controller;

import org.farhutdinov.app.petclinic.model.Pet;
import org.farhutdinov.app.petclinic.model.Visit;
import org.farhutdinov.app.petclinic.repositories.PetRepository;
import org.farhutdinov.app.petclinic.repositories.VisitRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.bind.Binder;
import java.nio.channels.FileChannel;
import java.time.LocalDate;
import java.util.Map;

@Controller
public class VisitController {

    private final VisitRepository visitRepository;
    private final PetRepository petRepository;

    public VisitController(VisitRepository visitRepository , PetRepository petRepository) {
        this.visitRepository = visitRepository;
        this.petRepository = petRepository;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder){dataBinder.setAllowedFields("id");}

    @ModelAttribute("visit")
    public Visit loadPEtWithVisit(@PathVariable("petId") int petId, Map<String,Object> objectMap){
        Pet pet = petRepository.findById(petId);
        objectMap.put("pet",pet);
        Visit visit = new Visit();
        pet.addVisit(visit);
        return visit;
    }

    @GetMapping("/owners/*/pets/{petId}/visits/new")
    public String initNewVisitForm(@PathVariable("petId") int petId, ModelMap map){
        Pet pet = petRepository.findById(petId);
        map.addAttribute("pet",pet);
        return "pets/createVisitForm";

    }
    @PostMapping("/owners/{ownerId}/pets/{petId}/visits/new")
    public String processNewVisitForm(@Valid Visit visit ,
                                      BindingResult result ,
                                      @RequestParam String date ,
                                      @RequestParam String desc) {
//        if (result.hasErrors()) {
//            System.out.println(result);
//            System.out.println(desc);
//            return "pets/createVisitForm";
//        } else {
            visit.setDate(LocalDate.parse(date));
            visit.setDescription(desc);
            visitRepository.save(visit);
            return "redirect:/owners/{ownerId}";
//        }
    }
}
