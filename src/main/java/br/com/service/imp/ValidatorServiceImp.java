package br.com.service;

import br.com.error.ResourceNotFoundException;
import br.com.repository.AddColumnRepository;
import br.com.repository.NameTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidatorServiceImp implements ValidatorService {

    private NameTableRepository repository;
    private AddColumnRepository addColumnRepository;

    @Autowired
    public ValidatorServiceImp(NameTableRepository repository, AddColumnRepository addColumnRepository) {
        this.repository = repository;
        this.addColumnRepository = addColumnRepository;
    }

    @Override
    public void verifyINameTableIdExists(Long id) {
        if(repository.findOne(id) == null){
            throw new ResourceNotFoundException("Name not found for ID: "+id);
        }
    }

    @Override
    public void verifyIfIdAddCollumnExists(Long id) {
        if(addColumnRepository.findOne(id) == null){
            throw new ResourceNotFoundException("Column not found for ID: "+id);
        }
    }
}
