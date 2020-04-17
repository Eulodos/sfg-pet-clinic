package com.alward.trainings.sfgpetclinic.repositories;

import com.alward.trainings.sfgpetclinic.model.Owner;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface OwnerRepository extends CrudRepository<Owner, Long> {

    Optional<Owner> findByLastName(String lastName);

    List<Owner> findAllByLastNameContainingIgnoreCase(String lastName);
}
