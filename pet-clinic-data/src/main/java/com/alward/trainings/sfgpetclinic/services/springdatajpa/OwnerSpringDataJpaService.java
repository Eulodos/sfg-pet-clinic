package com.alward.trainings.sfgpetclinic.services.springdatajpa;

import com.alward.trainings.sfgpetclinic.model.Owner;
import com.alward.trainings.sfgpetclinic.repositories.OwnerRepository;
import com.alward.trainings.sfgpetclinic.repositories.PetRepository;
import com.alward.trainings.sfgpetclinic.repositories.PetTypeRepository;
import com.alward.trainings.sfgpetclinic.services.OwnerService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class OwnerSpringDataJpaService implements OwnerService {

    private final OwnerRepository ownerRepository;
    private final PetRepository petRepository;
    private final PetTypeRepository petTypeRepository;

    public OwnerSpringDataJpaService(OwnerRepository ownerRepository, PetRepository petRepository,
                                     PetTypeRepository petTypeRepository) {
        this.ownerRepository = ownerRepository;
        this.petRepository = petRepository;
        this.petTypeRepository = petTypeRepository;
    }

    @Override
    public Owner findByLastName(String lastName) {
        return ownerRepository.findByLastName(lastName)
                .orElseThrow(() -> new RuntimeException("No owner matching provided lastName was found"));
    }

    @Override
    public List<Owner> findAllByLastNameContaining(String lastName) {
        return ownerRepository.findAllByLastNameContainingIgnoreCase(lastName);
    }

    @Override
    public Set<Owner> findAll() {
        Set<Owner> owners = new HashSet<>();
        ownerRepository.findAll().forEach(owners::add);
        return owners;
    }

    @Override
    public Owner findById(Long aLong) {
        return ownerRepository.findById(aLong).orElse(null);
    }

    @Override
    public Owner save(Owner object) {
        return ownerRepository.save(object);
    }

    @Override
    public void delete(Owner object) {
        ownerRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        ownerRepository.deleteById(aLong);
    }
}
