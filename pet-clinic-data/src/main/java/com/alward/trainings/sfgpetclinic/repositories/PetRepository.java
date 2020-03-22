package com.alward.trainings.sfgpetclinic.repositories;

import com.alward.trainings.sfgpetclinic.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
