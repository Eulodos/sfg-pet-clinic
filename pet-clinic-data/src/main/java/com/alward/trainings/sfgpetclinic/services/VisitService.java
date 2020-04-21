package com.alward.trainings.sfgpetclinic.services;

import com.alward.trainings.sfgpetclinic.model.Visit;

import java.util.Set;

public interface VisitService extends CrudService<Visit, Long> {

    Set<Visit> findByPetId(Long petId);

}
