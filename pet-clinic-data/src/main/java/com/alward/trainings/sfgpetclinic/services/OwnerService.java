package com.alward.trainings.sfgpetclinic.services;

import com.alward.trainings.sfgpetclinic.model.Owner;

import java.util.List;

public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);

    List<Owner> findAllByLastNameContaining(String lastName);
}
