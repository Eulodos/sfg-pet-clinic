package com.alward.trainings.sfgpetclinic.services.springdatajpa;

import com.alward.trainings.sfgpetclinic.model.Owner;
import com.alward.trainings.sfgpetclinic.repositories.OwnerRepository;
import com.alward.trainings.sfgpetclinic.repositories.PetRepository;
import com.alward.trainings.sfgpetclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerSpringDataJpaServiceTest {

    private static final String LAST_NAME = "Kartacz";
    private static final long ID = 1L;

    @Mock
    private OwnerRepository ownerRepository;

    @Mock
    private PetRepository petRepository;

    @Mock
    private PetTypeRepository petTypeRepository;

    @InjectMocks
    private OwnerSpringDataJpaService service;


    @BeforeEach
    void setUp() {
    }

    @Test
    void findByLastName() {
        Optional<Owner> owner = Optional.ofNullable(Owner.builder().id(ID).lastName(LAST_NAME).build());
        when(ownerRepository.findByLastName(anyString())).thenReturn(owner);

        Owner retrievedOwner = service.findByLastName(LAST_NAME);

        assertEquals(LAST_NAME, retrievedOwner.getLastName());
        verify(ownerRepository, times(1)).findByLastName(eq(LAST_NAME));
    }

    @Test
    void findAll() {
        Set<Owner> ownerSet = new HashSet<>();
        ownerSet.add(Owner.builder().id(ID).build());
        ownerSet.add(Owner.builder().id(2L).build());

        when(ownerRepository.findAll()).thenReturn(ownerSet);
        Set<Owner> retrievedOwners = service.findAll();

        assertNotNull(retrievedOwners);
        assertEquals(2, retrievedOwners.size());
    }

    @Test
    void findById() {
        Optional<Owner> owner = Optional.ofNullable(Owner.builder().id(ID).lastName(LAST_NAME).build());
        when(ownerRepository.findById(anyLong())).thenReturn(owner);
        Owner retrievedOwner = service.findById(ID);

        assertNotNull(retrievedOwner);
    }

    @Test
    void findByIdNotFound() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());

        Owner retrievedOwner = service.findById(ID);

        assertNull(retrievedOwner);
    }

    @Test
    void save() {
        Owner ownerToSave = Owner.builder().id(ID).build();
        Optional<Owner> owner = Optional.ofNullable(Owner.builder().id(ID).lastName(LAST_NAME).build());

        when(ownerRepository.save(any())).thenReturn(owner.orElse(null));
        Owner savedOwner = service.save(ownerToSave);

        assertNotNull(savedOwner);
        verify(ownerRepository, times(1)).save((eq(ownerToSave)));
    }

    @Test
    void delete() {
        Owner owner = Owner.builder().id(ID).lastName(LAST_NAME).build();
        service.delete(owner);
        verify(ownerRepository, times(1)).delete(eq(owner));
    }

    @Test
    void deleteById() {
        Owner owner = Owner.builder().id(ID).lastName(LAST_NAME).build();
        service.deleteById(owner.getId());
        verify(ownerRepository, times(1)).deleteById(eq(owner.getId()));
    }
}