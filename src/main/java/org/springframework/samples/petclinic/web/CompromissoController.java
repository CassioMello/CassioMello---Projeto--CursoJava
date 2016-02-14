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
import org.springframework.samples.petclinic.model.Compromisso;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Juergen Hoeller
 * @author Mark Fisher
 * @author Ken Krebs
 * @author Arjen Poutsma
 */
@Controller
public class CompromissoController {

    private final ClinicService clinicService;


    @Autowired
    public CompromissoController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }
    
    @RequestMapping(value = "/compromissos/new", method = RequestMethod.GET)
    public String initCreationForm(Agenda agenda, Map<String, Object> model) {
        Compromisso compromisso = new Compromisso();
    	agenda.addCompromisso(compromisso);
        model.put("compromisso", compromisso);
        return "compromissos/createOrUpdateCompromissoForm";
    }
      
    @RequestMapping(value = "/compromissos/new", method = RequestMethod.POST)
    public String processCreationForm(Agenda agenda, @Valid Compromisso compromisso, BindingResult result) {
        if (result.hasErrors()) {
            return "compromissos/createOrUpdateCompromissoForm";
        } else {
        	agenda.addCompromisso(compromisso);
        	//this.clinicService.saveAgenda(agenda);
            this.clinicService.saveCompromisso(compromisso);
            return "redirect:/agendas/{agendaId}";
        }
    }
    
    /**
     * Custom handler for displaying an owner.
     *
     * @param ownerId the ID of the owner to display
     * @return a ModelMap with the model attributes for the view
     */
    @RequestMapping("/compromissos/{compromissoId}")
    public ModelAndView showCompromisso(@PathVariable("compromissoId") int compromissoId) {
        ModelAndView mav = new ModelAndView("compromissos/compromissoDetails");
        mav.addObject(this.clinicService.findCompromissoById(compromissoId));
        return mav;
    }
    
}
