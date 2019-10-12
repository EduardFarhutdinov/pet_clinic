package org.farhutdinov.app.petclinic.controller;

import org.farhutdinov.app.petclinic.model.Vets;
import org.farhutdinov.app.petclinic.repositories.VetRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class VetController {
private final VetRepository vetRepository;

    public VetController(VetRepository vetRepository) {
        this.vetRepository = vetRepository;
    }

    @GetMapping("/vetList.ftl")
    public String showVetList(Map<String,Object> model){
        Vets vets = new Vets();
        vets.getVets().addAll(vetRepository.findAll());
        model.put("vets",vets);
        return "vetList";
    }
    @GetMapping({ "/vets" })
    public @ResponseBody
    Vets showResourcesVetList() {
        // Here we are returning an object of type 'Vets' rather than a collection of Vet
        // objects so it is simpler for JSon/Object mapping
        Vets vets = new Vets();
        vets.getVets().addAll(this.vetRepository.findAll());
        return vets;
    }
}
