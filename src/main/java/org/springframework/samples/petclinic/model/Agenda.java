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
package org.springframework.samples.petclinic.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;

/**
 * Simple JavaBean domain object representing a veterinarian.
 *
 * @author Ken Krebs
 * @author Juergen Hoeller
 * @author Sam Brannen
 * @author Arjen Poutsma
 */
@Entity
@Table(name = "agendas")
public class Agenda extends BaseEntity {

   // @OneToOne(fetch = FetchType.EAGER)
  /*  @JoinTable(name = "vet_specialties", joinColumns = @JoinColumn(name = "vet_id"),
        inverseJoinColumns = @JoinColumn(name = "specialty_id"))*/
    @Column(name = "titulo")
    private String titulo;

    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "agenda", targetEntity = Agenda.class, fetch = FetchType.LAZY)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "agenda")
    private List<Compromisso> compromissos;

    public String getTitulo() {
        if (this.titulo == null) {
            this.titulo = "";
        }
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @XmlElement
    public List<Compromisso> getCompromissoList() {
        if (this.compromissos == null) {
            this.compromissos = new ArrayList<>();
        }
        return compromissos;
    }
    
    public void addCompromisso(Compromisso compromisso) {
    	getCompromissoList().add(compromisso);
        compromisso.setAgenda(this);
    }
    
}
