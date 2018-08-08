package br.com.service.imp;

import br.com.query.CreateQuery;
import br.com.repository.AddColumnRepository;
import br.com.repository.NameTableRepository;
import br.com.service.QueryService;
import br.com.service.ValidatorService;
import br.com.table.*;
import br.com.table.column.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class QueryServiceImp implements QueryService {

    private NameTableRepository nameTableRepository;
    private AddColumnRepository addColumnRepository;
    private ValidatorService validator;

    @Autowired
    public QueryServiceImp(NameTableRepository nameTableRepository, AddColumnRepository addColumnRepository, ValidatorService validator) {
        this.nameTableRepository = nameTableRepository;
        this.addColumnRepository = addColumnRepository;
        this.validator = validator;
    }

    @Override
    public idTableResp createTable(NameTableReq nameTable) throws SQLException {
        CreateQuery.createTable(nameTable.getNameTable());
        NameTable table = NameTable.create(nameTable.getNameTable(), nameTableRepository);
        return new idTableResp(table.getId());
    }

    @Override
    public idColumnResp createColumn(TableColumnDataType parameter) throws SQLException {

        NameTable idTable = nameTableRepository.findBayIdNameTable(parameter.getNameTable());
        CreateQuery.addConlumnOfTheTable(parameter.getNameTable(),parameter.getNameColumn(),parameter.getDataType());
        AddColumn column = AddColumn.create(parameter.getNameColumn(), parameter.getDataType(), idTable , addColumnRepository);
        return new idColumnResp(column.getId());
    }

    @Override
    public idTableResp updateNameTable(CurrentModified parameter) throws SQLException {
        NameTable idNameTable = nameTableRepository.findBayIdNameTable(parameter.getNameCurrent());
        CreateQuery.alterTable(parameter.getNameCurrent(), parameter.getNameModified());
        NameTable table = NameTable.updateTable(idNameTable.getId(), parameter.getNameModified(), nameTableRepository, validator);
        return new idTableResp(table.getId());
    }

    @Override
    public idColumnResp updateNameDataTypeIsNameConlumn(TableColumnDataType parameter) throws SQLException {
        AddColumn idColumn = addColumnRepository.findBayIdAddColum(parameter.getNameColumn());
        NameTable idTable = addColumnRepository.findBayIdNameDataType(parameter.getNameColumn());

        CreateQuery.alterDataTypeIsNameColumn(parameter.getNameTable(), parameter.getNameColumn(), parameter.getDataType());
        AddColumn column = AddColumn.updateDataTypeIsColumn(
                idColumn.getId(),
                parameter.getNameColumn(),
                parameter.getDataType(),
                idTable,
                addColumnRepository,
                validator);
        return new idColumnResp(column.getId());
    }

    @Override
    public idColumnResp addConstraintNotNullConlumn(TableColumnReq parameter) throws SQLException {

        NameTable idTable = addColumnRepository.findBayIdNameDataType(parameter.getNameColumn());

        AddColumn columnId = addColumnRepository.findBayAddColum(parameter.getNameColumn());

        CreateQuery.addConstraintNotNullConlumn(parameter.getNameTable(), parameter.getNameColumn());
        AddColumn column = AddColumn.updateDataTypeIsColumn(
                columnId.getId(),
                columnId.getNameColumn(),
                columnId.getDataType().concat(" NOT NULL"),
                idTable,
                addColumnRepository,
                validator);
        return new idColumnResp(column.getId());
    }

    @Override
    public NameT1IsT2 addForeignKey(NameT1IsT2 parameter) throws SQLException {

        CreateQuery.addForeignKey(parameter.getNameTable1(), parameter.getNameTable2());
        return new NameT1IsT2(parameter.getNameTable1(), parameter.getNameTable2());
    }

    @Override
    public idColumnResp dropConstraintNotNullConlumn(TableColumnReq parameter) throws SQLException {
        NameTable idTable = addColumnRepository.findBayIdNameDataType(parameter.getNameColumn());

        AddColumn columnId = addColumnRepository.findBayAddColum(parameter.getNameColumn());

        CreateQuery.dropConstraintNotNullConlumn(parameter.getNameTable(), parameter.getNameColumn());
        AddColumn column = AddColumn.updateDataTypeIsColumn(
                columnId.getId(),
                columnId.getNameColumn(),
                columnId.getDataType().replace("NOT NULL",""),
                idTable,
                addColumnRepository,
                validator);
        return new idColumnResp(column.getId());
    }

    @Override
    public void dropColumnOfTheTable(TableColumnReq parameter) throws SQLException {
        AddColumn columnId = addColumnRepository.findBayIdAddColum(parameter.getNameColumn());
        CreateQuery.dropColumnOfTheTable(parameter.getNameTable(), parameter.getNameColumn());
        AddColumn.deleteColumn(columnId.getId(), addColumnRepository, validator);
    }

    @Override
    public void dropTable(NameTableReq parameter) throws SQLException {

        NameTable idNameTable = nameTableRepository.findBayIdNameTable(parameter.getNameTable());
        CreateQuery.dropTable(parameter.getNameTable());
        NameTable.deleteTable(idNameTable.getId(), nameTableRepository, validator);
    }
}
