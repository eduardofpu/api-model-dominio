package br.com.service.imp;

import br.com.error.*;
import br.com.error.InternalError;
import br.com.repository.AddColumnRepository;
import br.com.repository.NameTableRepository;
import br.com.service.ValidatorService;
import br.com.table.NameTable;
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

    @Override
    public String verifyIfNameAlredyExists(String nameTable) {

        NameTable idNameTable = repository.findBayIdNameTable(nameTable);

        if(idNameTable!=null) {
            NameTable name = repository.findOne(idNameTable.getId());
            if (nameTable.equals(name.getNameTable())) {
                throw new InternalError(nameTable + " already exists in database");
            }
        }

        return nameTable.toString();
    }

    @Override
    public String verifyNameDoesNotExist(String nameTable) {

        NameTable idNameTable = repository.findBayIdNameTable(nameTable);
        if(idNameTable==null) {
                throw new InternalError("Table " +nameTable+ " does not exist");
        }
        return nameTable.toString();
    }

    @Override
    public String verifyNameColumn(String nameTable, NameTable id, String nameColumn) {

        String name = addColumnRepository.findBayNameColumnReplay(id, nameColumn);

        if(name!=null) {
            if (nameColumn.equals(name)) {
                throw new InternalError("Column: "+nameColumn + " already exists in table: "+nameTable);
            }
        }

        return nameColumn;
    }
}
