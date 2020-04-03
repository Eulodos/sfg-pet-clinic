package com.alward.trainings.sfgpetclinic.services.map;

import com.alward.trainings.sfgpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerMapServiceTest {

    private OwnerMapService ownerMapService;

    private final long OWNER_ID = 1L;
    private final String LAST_NAME = "KARTACZ";

    @BeforeEach
    void setUp() {
        ownerMapService = new OwnerMapService(new PetTypeMapService(), new PetMapService());

        ownerMapService.save(Owner.builder().id(OWNER_ID).lastName(LAST_NAME).build());
    }

    @Test
    void findAll() {
        Set<Owner> ownerSet = ownerMapService.findAll();

        assertEquals(1, ownerSet.size());
    }

    @Test
    void deleteById() {
        ownerMapService.deleteById(OWNER_ID);
        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void delete() {
        ownerMapService.delete(ownerMapService.findById(OWNER_ID));
        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void saveExistingId() {
        long id = 2L;
        Owner owner = Owner.builder().id(id).build();

        Owner savedOwner = ownerMapService.save(owner);
        assertEquals(id, savedOwner.getId());
    }

    @Test
    void saveNoId() {
        Owner savedOwner = ownerMapService.save(Owner.builder().build());

        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());
    }

    @Test
    void findById() {
        Owner byId = ownerMapService.findById(OWNER_ID);

        assertEquals(OWNER_ID, byId.getId());
    }

    @Test
    void findByLastName() {
        Owner byLastName = ownerMapService.findByLastName(LAST_NAME);

        assertNotNull(byLastName);

        assertEquals(LAST_NAME, byLastName.getLastName());
    }

    @Test
    void findByLastNameNotFound() {
        Owner byLastName = ownerMapService.findByLastName("Foo");

        assertNull(byLastName);
    }
}