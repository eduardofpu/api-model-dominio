package br.com.service;

import br.com.query.CreateQuery;
import br.com.repository.RepositoryAddColumn;
import br.com.repository.RepositoryNameTable;
import br.com.table.*;
import br.com.table.column.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        NameTable idNameTable = repositoryNameTable.findBayIdNameTable(parameter.getNameCurrent());
        CreateQuery.alterTable(parameter.getNameCurrent(), parameter.getNameModified());
        NameTable table = NameTable.updateTable(idNameTable.getId(), parameter.getNameModified(), repositoryNameTable, validator);
        return new NameTableResponse(table.getId());
    }

    @Override
    public AddColumnResponse updateNameDataTypeIsNameConlumn(AlterDataTypeIsColumn parameter) throws SQLException {
        AddColumn idColumn = repositoryAddColumn.findBayIdAddColum(parameter.getNameColumn());
        NameTable idTable = repositoryAddColumn.findBayIdNameDataType(parameter.getNameColumn());

        CreateQuery.alterDataTypeIsNameColumn(parameter.getNameTable(), parameter.getNameColumn(), parameter.getDataType());
        AddColumn column = AddColumn.updateDataTypeIsColumn(
                idColumn.getId(),
                parameter.getNameColumn(),
                parameter.getDataType(),
                idTable,
                repositoryAddColumn,
                validator);
        return new AddColumnResponse(column.getId());
    }

    @Override
    public AddColumnResponse addConstraintNotNullConlumn(AddColumnNameTableReq parameter) throws SQLException {

        NameTable idTable = repositoryAddColumn.findBayIdNameDataType(parameter.getNameColumn());

        AddColumn columnId = repositoryAddColumn.findBayAddColum(parameter.getNameColumn());

        CreateQuery.addConstraintNotNullConlumn(parameter.getNameTable(), parameter.getNameColumn());
        AddColumn column = AddColumn.updateDataTypeIsColumn(
                columnId.getId(),
                columnId.getNameColumn(),
                columnId.getDataType().concat(" NOT NULL"),
                idTable,
                repositoryAddColumn,
                validator);
        return new AddColumnResponse(column.getId());
    }

    @Override
    public NameTableReq addForeignKey(NameTableReq parameter) throws SQLException {

        CreateQuery.addForeignKey(parameter.getNameTable1(), parameter.getNameTable2());
        return new NameTableReq(parameter.getNameTable1(), parameter.getNameTable2());
    }

    @Override
    public AddColumnResponse dropConstraintNotNullConlumn(AddColumnNameTableReq parameter) throws SQLException {
        NameTable idTable = repositoryAddColumn.findBayIdNameDataType(parameter.getNameColumn());

        AddColumn columnId = repositoryAddColumn.findBayAddColum(parameter.getNameColumn());

        CreateQuery.dropConstraintNotNullConlumn(parameter.getNameTable(), parameter.getNameColumn());
        AddColumn column = AddColumn.updateDataTypeIsColumn(
                columnId.getId(),
                columnId.getNameColumn(),
                columnId.getDataType().replace("NOT NULL",""),
                idTable,
                repositoryAddColumn,
                validator);
        return new AddColumnResponse(column.getId());
    }

    @Override
    public void dropColumnOfTheTable(DropColumn parameter) throws SQLException {
        AddColumn columnId = repositoryAddColumn.findBayIdAddColum(parameter.getNameColumn());
        CreateQuery.dropColumnOfTheTable(parameter.getNameTable(), parameter.getNameColumn());
        AddColumn.deleteColumn(columnId.getId(), repositoryAddColumn, validator);
    }

    @Override
    public void dropTable(NameTableRequest parameter) throws SQLException {

        NameTable idNameTable = repositoryNameTable.findBayIdNameTable(parameter.getNameTable());
        CreateQuery.dropTable(parameter.getNameTable());
        NameTable.deleteTable(idNameTable.getId(),repositoryNameTable, validator);
    }
}
