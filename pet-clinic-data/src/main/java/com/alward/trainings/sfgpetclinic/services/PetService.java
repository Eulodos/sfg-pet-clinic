package com.alward.trainings.sfgpetclinic.services;

import com.alward.trainings.sfgpetclinic.model.Pet;

import java.util.Set;

public interface PetService {

    Pet findById(Long id);

    Pet save(Pet pet);

    Set<Pet> findAll();
}
