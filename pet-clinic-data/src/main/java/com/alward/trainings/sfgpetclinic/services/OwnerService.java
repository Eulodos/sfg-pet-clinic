package com.alward.trainings.sfgpetclinic.services;

import com.alward.trainings.sfgpetclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);
}
