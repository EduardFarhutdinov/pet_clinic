package org.farhutdinov.app.petclinic;

import org.farhutdinov.app.petclinic.model.Vets;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

@SpringBootApplication
public class PetClinicSpringbootApplication {

    public static void main(String[] args) throws JAXBException {
        Vets vets = new Vets();
        JAXBContext context = JAXBContext.newInstance(Vets.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
        marshaller.marshal(vets,new File("./vets.xml"));
        SpringApplication.run(PetClinicSpringbootApplication.class , args);
    }

}
