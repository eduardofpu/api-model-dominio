package br.com.service.imp;

import br.com.query.CreateQuery;
import br.com.repository.AddColumnRepository;
import br.com.repository.NameTableRepository;
import br.com.service.TableService;
import br.com.service.ValidatorService;
import br.com.table.NameTable;
import br.com.table.ObjectParameter;
import br.com.table.SelectTable;
import br.com.table.UpdateTableReq;
import br.com.table.column.AddColumn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class TableServiceImp implements TableService {

    private NameTableRepository nameTableRepository;
    private AddColumnRepository addColumnRepository;
    private ValidatorService validator;

    @Autowired
    public TableServiceImp(NameTableRepository nameTableRepository, AddColumnRepository addColumnRepository, ValidatorService validator) {
        this.nameTableRepository = nameTableRepository;
        this.addColumnRepository = addColumnRepository;
        this.validator = validator;
    }

    @Override
    public Object  findAll(String nameTable) throws SQLException {
        String name = validator.verifyNameDoesNotExist(nameTable);
        NameTable idNameTable = nameTableRepository.findBayIdNameTable(name);
        List<AddColumn> objectColumn = addColumnRepository.findBayNameColumn(idNameTable);
        return new SelectTable(name, CreateQuery.selectRg(name, objectColumn));
    }

    @Override
    public SelectTable findById(String nameTable, Long id) throws SQLException {
        String name = validator.verifyNameDoesNotExist(nameTable);
        NameTable idNameTable = nameTableRepository.findBayIdNameTable(name);
        List<AddColumn> objectColumn = addColumnRepository.findBayNameColumn(idNameTable);
        return new SelectTable(nameTable, CreateQuery.selectIdRg(name, objectColumn, id));
    }

    @Override
    public ObjectParameter insertInto(ObjectParameter parameter) throws SQLException {

        String name = validator.verifyNameDoesNotExist(parameter.getNameTable());

        NameTable idNameTable = nameTableRepository.findBayIdNameTable(name);

        List<AddColumn> objectColumn = addColumnRepository.findBayNameColumn(idNameTable);

        CreateQuery.insertInto(name, objectColumn, parameter.getParameters());
        return new ObjectParameter(name, parameter.getParameters());
    }

    @Override
    public Object updateAttributeRg(UpdateTableReq parameter) throws SQLException {
        String name = validator.verifyNameDoesNotExist(parameter.getNameTable());

        CreateQuery.updateAttributeRg(name, parameter.getAttribute(), parameter.getId(), parameter.getParameter());
        return new UpdateTableReq(name, parameter.getAttribute(), parameter.getId(), parameter.getParameter());
    }

    @Override
    public ObjectParameter updateRg(ObjectParameter parameter, Long id) throws SQLException {
        String name = validator.verifyNameDoesNotExist(parameter.getNameTable());
        NameTable idNameTable = nameTableRepository.findBayIdNameTable(name);

        List<AddColumn> objectColumn = addColumnRepository.findBayNameColumn(idNameTable);

        CreateQuery.updateRg(name, objectColumn, parameter.getParameters(), id);
        return new ObjectParameter(name, parameter.getParameters());
    }

    @Override
    public void deleteRg(String nameTable, Long id) throws SQLException {
        String name = validator.verifyNameDoesNotExist(nameTable);
        CreateQuery.deleteRg(name, id);
    }

    @Override
    public void deleteRgAll(String nameTable) throws SQLException {
        String name = validator.verifyNameDoesNotExist(nameTable);
        CreateQuery.deleteRgAll(name);
    }
}

