package com.alward.trainings.sfgpetclinic.services.map;

import com.alward.trainings.sfgpetclinic.model.Visit;
import com.alward.trainings.sfgpetclinic.services.VisitService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Profile({"default", "map"})
public class VisitMapService extends AbstractMapService<Visit, Long> implements VisitService {

    @Override
    public Set<Visit> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Visit object) {
        super.delete(object);
    }

    @Override
    public Visit save(Visit object) {
        if (object.getPet() == null || object.getPet().getOwner() == null ||
                object.getPet().getId() == null || object.getPet().getOwner().getId() == null) {
            throw new RuntimeException("Invalid Visit");
        }
        return super.save(object);
    }

    @Override
    public Visit findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Set<Visit> findByPetId(Long petId) {
        return findAll().stream()
                .filter(visit -> visit.getPet().getId().equals(petId))
                .collect(Collectors.toSet());
    }
}
