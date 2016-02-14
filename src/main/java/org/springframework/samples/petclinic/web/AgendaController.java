/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.web;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Agenda;
import org.springframework.samples.petclinic.model.Agendas;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Juergen Hoeller
 * @author Mark Fisher
 * @author Ken Krebs
 * @author Arjen Poutsma
 */
@Controller
public class AgendaController {

    private final ClinicService clinicService;


    @Autowired
    public AgendaController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @RequestMapping(value = {"/agendas.xml", "/agendas.html"})
    public String showAgendaList(Map<String, Object> model) {
        // Here we are returning an object of type 'Vets' rather than a collection of Vet objects
        // so it is simpler for Object-Xml mapping
        Agendas agendas = new Agendas();
        agendas.getAgendaList().addAll(this.clinicService.findAgendas());
        model.put("agendas", agendas);
        return "agendas/agendaList";
    }

    @RequestMapping("/agendas.json")
    public
    @ResponseBody
    Agendas showResourcesAgendaList() {
        // Here we are returning an object of type 'Vets' rather than a collection of Vet objects
        // so it is simpler for JSon/Object mapping
        Agendas agendas = new Agendas();
        agendas.getAgendaList().addAll(this.clinicService.findAgendas());
        return agendas;
    }

    @RequestMapping(value = "/agendas/new", method = RequestMethod.GET)
    public String initCreationForm(Map<String, Object> model) {
        Agenda agenda = new Agenda();
        model.put("agenda", agenda);
        return "agendas/createOrUpdateAgendaForm";
    }    

    @RequestMapping(value = "/agendas/new", method = RequestMethod.POST)
    public String processCreationForm(@Valid Agenda agenda, BindingResult result) {
        if (result.hasErrors()) {
            return "agendas/createOrUpdateOwnerForm";
        } else {
            this.clinicService.saveAgenda(agenda);
            //return "redirect:/agendas/agendaList";
            return "redirect:/agendas.html";
        }
    }
    
    /**
     * Custom handler for displaying an owner.
     *
     * @param ownerId the ID of the owner to display
     * @return a ModelMap with the model attributes for the view
     */
    @RequestMapping("/agendas/{agendaId}")
    public ModelAndView showAgenda(@PathVariable("agendaId") int agendaId) {
        ModelAndView mav = new ModelAndView("agendas/agendaDetails");
        mav.addObject(this.clinicService.findAgendaById(agendaId));
        return mav;
    }
    
}
