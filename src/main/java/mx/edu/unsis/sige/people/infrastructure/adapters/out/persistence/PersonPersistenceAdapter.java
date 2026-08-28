package mx.edu.unsis.sige.people.infrastructure.adapters.out.persistence;

import lombok.RequiredArgsConstructor;
import mx.edu.unsis.sige.people.domain.model.Person;
import mx.edu.unsis.sige.people.domain.model.valueobjects.Curp;
import mx.edu.unsis.sige.people.domain.ports.out.PersonRepositoryPort;
import mx.edu.unsis.sige.people.infrastructure.adapters.out.persistence.entity.PersonEntity;
import mx.edu.unsis.sige.people.infrastructure.adapters.out.persistence.mapper.PersonPersistenceMapper;
import mx.edu.unsis.sige.people.infrastructure.adapters.out.persistence.repository.PersonJpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PersonPersistenceAdapter implements PersonRepositoryPort {
    
    private final PersonJpaRepository personJpaRepository;
    private final PersonPersistenceMapper personPersistenceMapper;

    @Override
    @Transactional
    public Person save(Person person) {
        PersonEntity entityToSave = personPersistenceMapper.toEntity(person);
        PersonEntity savedEntity = personJpaRepository.save(entityToSave);
        return personPersistenceMapper.toDomain(savedEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Person> findById(UUID personId) {
        return personJpaRepository.findById(personId)
                .map(personPersistenceMapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Person> findByCurp(Curp curp) {
        return personJpaRepository.findByCurp(curp.getValue())
                .map(personPersistenceMapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByCurp(Curp curp) {
        return personJpaRepository.existsByCurp(curp.getValue());
    }
}