package br.com.service;

import br.com.query.CreateQuery;
import br.com.repository.RepositoryAddColumn;
import br.com.repository.RepositoryNameTable;
import br.com.table.NameModifiedTable;
import br.com.table.NameTable;
import br.com.table.NameTableRequest;
import br.com.table.NameTableResponse;
import br.com.table.column.AddColumn;
import br.com.table.column.AddColumnRequest;
import br.com.table.column.AddColumnResponse;
import br.com.table.column.AlterDataTypeIsColumn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.sql.SQLException;

@Service
public class ServiceImp implements ServiceQuery{

    private RepositoryNameTable repositoryNameTable;
    private RepositoryAddColumn repositoryAddColumn;
    private Validator validator;

    @Autowired
    public ServiceImp(RepositoryNameTable repositoryNameTable, RepositoryAddColumn repositoryAddColumn, Validator validator) {
        this.repositoryNameTable = repositoryNameTable;
        this.repositoryAddColumn = repositoryAddColumn;
        this.validator = validator;
    }

    @Override
    public NameTableResponse createTable(NameTableRequest nameTable) throws SQLException {
        CreateQuery.createTable(nameTable.getNameTable());
        NameTable table = NameTable.create(nameTable.getNameTable(), repositoryNameTable);
        return new NameTableResponse(table.getId());
    }

    @Override
    public AddColumnResponse createColumn(AddColumnRequest parameter) throws SQLException {

        NameTable idTable = repositoryNameTable.findBayIdNameTable(parameter.getNameTable());
        CreateQuery.addConlumnOfTheTable(parameter.getNameTable(),parameter.getNameColumn(),parameter.getDataType());
        AddColumn column = AddColumn.create(parameter.getNameColumn(), parameter.getDataType(), idTable ,repositoryAddColumn);
        return new AddColumnResponse(column.getId());
    }

    @Override
    public NameTableResponse updateNameTable(NameModifiedTable parameter) throws SQLException {

        CreateQuery.alterTable(parameter.getNameCurrent(), parameter.getNameModified());
        NameTable table = NameTable.updateTable(parameter.getId(), parameter.getNameModified(), repositoryNameTable, validator);
        return new NameTableResponse(table.getId());
    }

    @Override
    public AddColumnResponse updateNameDataTypeIsNameConlumn(AlterDataTypeIsColumn parameter) throws SQLException {
        NameTable idTable = repositoryAddColumn.findBayIdNameDataType(parameter.getNameColumn());

        CreateQuery.alterDataTypeIsNameColumn(parameter.getNameTable(), parameter.getNameColumn(), parameter.getDataType());
        AddColumn column = AddColumn.updateDataTypeIsColumn(
                parameter.getId(),
                parameter.getNameColumn(),
                parameter.getDataType(),
                idTable,
                repositoryAddColumn,
                validator);
        return new AddColumnResponse(column.getId());
    }

}
