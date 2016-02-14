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
package org.springframework.samples.petclinic.repository.jpa;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.samples.petclinic.model.Agenda;
import org.springframework.samples.petclinic.repository.AgendaRepository;
import org.springframework.samples.petclinic.repository.VetRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA implementation of the {@link VetRepository} interface.
 *
 * @author Mike Keith
 * @author Rod Johnson
 * @author Sam Brannen
 * @author Michael Isvy
 * @since 22.4.2006
 */
@Repository
public class JpaAgendaRepositoryImpl implements AgendaRepository {

    @PersistenceContext
    private EntityManager em;


    @Override
    @Cacheable(value = "agendas")
    @SuppressWarnings("unchecked")
    public Collection<Agenda> findAll() {
    	return this.em.createQuery("SELECT distinct agenda FROM Agenda agenda ORDER BY agenda.titulo").getResultList();
    }

    @Override
    public void save(Agenda agenda) {
        if (agenda.getId() == null) {
            this.em.persist(agenda);
        } else {
            this.em.merge(agenda);
        }

    }

	@Override
	public Agenda findById(int agendaId) {
        Query query = this.em.createQuery("SELECT agenda FROM Agenda agenda left join fetch agenda.compromissos WHERE agenda.id = :agendaId");
        query.setParameter("agendaId", agendaId);
        return (Agenda) query.getSingleResult();
	}
}
