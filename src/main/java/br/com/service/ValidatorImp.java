package br.com.service;

import br.com.error.ResourceNotFoundException;
import br.com.repository.RepositoryAddColumn;
import br.com.repository.RepositoryNameTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidatorImp implements Validator {

    private RepositoryNameTable repository;
    private RepositoryAddColumn repositoryAddColumn;

    @Autowired
    public ValidatorImp(RepositoryNameTable repository, RepositoryAddColumn repositoryAddColumn) {
        this.repository = repository;
        this.repositoryAddColumn = repositoryAddColumn;
    }

    @Override
    public void verifyINameTableIdExists(Long id) {
        if(repository.findOne(id) == null){
            throw new ResourceNotFoundException("Name not found for ID: "+id);
        }
    }

    @Override
    public void verifyIfIdAddCollumnExists(Long id) {
        if(repositoryAddColumn.findOne(id) == null){
            throw new ResourceNotFoundException("Column not found for ID: "+id);
        }
    }
}
