package org.farhutdinov.app.petclinic.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class Vets {
    private List<Vet> vets;

    @XmlElement
    public List<Vet> getVets(){
        if (vets == null){
            vets = new ArrayList<>();
        }
        return vets;
    }
}
