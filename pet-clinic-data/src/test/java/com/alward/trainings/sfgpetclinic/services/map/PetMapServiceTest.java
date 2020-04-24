package com.alward.trainings.sfgpetclinic.services.map;

import com.alward.trainings.sfgpetclinic.model.Owner;
import com.alward.trainings.sfgpetclinic.model.Pet;
import com.alward.trainings.sfgpetclinic.model.PetType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PetMapServiceTest {

    private final Long petId = 1L;

    private PetMapService petMapService;

    private final LocalDate now = LocalDate.now();
    private final Pet pet = Pet.builder().id(petId).birthDate(now).name("Doggo")
            .petType(PetType.builder().id(1L).name("Dog").build())
            .owner(Owner.builder().id(1L).firstName("Krzywans").build())
            .build();

    @BeforeEach
    void setUp() {
        petMapService = new PetMapService();
        petMapService.save(pet);
    }

    @Test
    void findAll() {
        //when
        Set<Pet> pets = petMapService.findAll();

        //then
        assertEquals(1, pets.size());
    }

    @Test
    void deleteById() {
        //when
        petMapService.deleteById(petId);

        //then
        assertTrue(petMapService.findAll().isEmpty());
    }

    @Test
    void delete() {
        //when
        petMapService.delete(pet);

        //then
        assertNull(petMapService.findById(pet.getId()));
    }

    @Test
    void deleteWithWrongId() {
        //when
        Pet pet = Pet.builder().id(5L).build();

        petMapService.delete(pet);

        //then
        assertEquals(1, petMapService.findAll().size());
    }

    @Test
    void deleteWithNullId() {
        //when
        Pet pet = Pet.builder().build();

        petMapService.delete(pet);

        //then
        assertEquals(1, petMapService.findAll().size());
    }

    @Test
    void deleteNull() {
        //when
        petMapService.delete(null);

        //then
        assertEquals(1, petMapService.findAll().size());
    }

    @Test
    void deleteByIdWrongId() {
        //when
        petMapService.deleteById(5L);

        //then
        assertEquals(1, petMapService.findAll().size());
    }

    @Test
    void deleteByIdNullId() {
        //when
        petMapService.deleteById(null);

        //then
        assertEquals(1, petMapService.findAll().size());
    }

    @Test
    void save() {
        //given
        LocalDate birthDate = LocalDate.now();
        Pet anotherPet = Pet.builder().id(100L).name("DoggyDog").birthDate(birthDate)
                .owner(Owner.builder().id(20L).firstName("JoJo").build()).build();

        //when
        Pet savedPet = petMapService.save(anotherPet);

        //then
        assertNotNull(savedPet);
        assertEquals(birthDate, savedPet.getBirthDate());
        assertEquals("DoggyDog", savedPet.getName());
        assertEquals(100L, savedPet.getId());
        assertEquals(20L, savedPet.getOwner().getId());
        assertEquals("JoJo", savedPet.getOwner().getFirstName());
    }

    @Test
    void saveDuplicateId() {
        //given
        LocalDate birthDate = now.minusDays(10);
        Pet anotherPet = Pet.builder().id(petId).birthDate(birthDate).name("Doge")
                .petType(PetType.builder().id(1L).name("Dog").build())
                .owner(Owner.builder().id(1L).firstName("Krzywans").build())
                .build();

        //when
        Pet savedPet = petMapService.save(anotherPet);

        //then
        assertNotNull(savedPet);
        assertEquals(birthDate, savedPet.getBirthDate());
        assertEquals(petId, savedPet.getPetType().getId());
        assertEquals("Dog", savedPet.getPetType().getName());
        assertEquals("Doge", savedPet.getName());
        assertEquals("Krzywans", savedPet.getOwner().getFirstName());
        assertEquals(1L, savedPet.getOwner().getId());
        assertEquals(1, petMapService.findAll().size());
    }

    @Test
    void saveNoId() {
        //when
        Pet savedPet = petMapService.save(Pet.builder().build());

        //then
        assertNotNull(savedPet);
        assertNotNull(savedPet.getId());
        assertEquals(2, petMapService.findAll().size());
    }

    @Test
    void findById() {
        //when
        Pet petById = petMapService.findById(petId);

        //then
        assertNotNull(petById);
        assertEquals(now, petById.getBirthDate());
        assertEquals(petId, petById.getPetType().getId());
        assertEquals("Dog", petById.getPetType().getName());
        assertEquals("Doggo", petById.getName());
        assertEquals("Krzywans", petById.getOwner().getFirstName());
        assertEquals(1L, petById.getOwner().getId());
    }

    @Test
    void findByNonExistingId() {
        //when
        Pet byId = petMapService.findById(9999999L);

        //then
        assertNull(byId);
    }

    @Test
    void findByIdWithNullId() {
        //when
        Pet byId = petMapService.findById(null);

        //then
        assertNull(byId);
    }
}