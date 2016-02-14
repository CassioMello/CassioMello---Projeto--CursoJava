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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.samples.petclinic.model.Compromisso;
import org.springframework.samples.petclinic.repository.CompromissoRepository;
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
public class JpaCompromissoRepositoryImpl implements CompromissoRepository {

    @PersistenceContext
    private EntityManager em;

	@Override
	public Compromisso findById(int compromissoId) {
        Query query = this.em.createQuery("SELECT compromisso FROM Compromisso compromisso WHERE compromisso.id = :compromissoId");
        query.setParameter("compromissoId", compromissoId);
        return (Compromisso) query.getSingleResult();
	}

	@Override
	@Transactional
	public void save(Compromisso compromisso) {
        if (compromisso.getId() == null) {
            this.em.persist(compromisso);
        } else {
            this.em.merge(compromisso);
        }

    }
}
