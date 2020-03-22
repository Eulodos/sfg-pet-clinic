package com.alward.trainings.sfgpetclinic.repositories;

import com.alward.trainings.sfgpetclinic.model.PetType;
import org.springframework.data.repository.CrudRepository;

public interface PetTypeRepository extends CrudRepository<PetType, Long> {
}
