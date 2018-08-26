package br.com.query;

import br.com.AbstractTest;
import br.com.table.NameTable;
import br.com.table.NameTableReq;
import br.com.table.ObjectParameter;
import br.com.table.column.AddColumn;
import br.com.table.column.TableColumnDataType;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreateQueryTest extends AbstractTest {

    private final String NAME_TABLE_CONTATO = "contato";
    private final String NAME_TABLE_CIDADE = "cidade";
    private final String NAME_COLUMN = "name";
    private final String DATA_TYPE = "text";
    private final String DATA_TYPE_VAR = "VARCHAR(45)";

    private NameTableReq nameTableReq = new NameTableReq(NAME_TABLE_CONTATO);
    private ObjectParameter parameter = new ObjectParameter(NAME_TABLE_CONTATO, Arrays.asList("Goku"));
    private ObjectParameter parameter1 = new ObjectParameter(NAME_TABLE_CONTATO, Arrays.asList("Vegeta"));
    private ObjectParameter parameter2 = new ObjectParameter(NAME_TABLE_CONTATO, Arrays.asList("Kuririn"));
    private ObjectParameter parameter3 = new ObjectParameter(NAME_TABLE_CONTATO, Arrays.asList("Mestre Kame"));
    private ObjectParameter parameter4 = new ObjectParameter(NAME_TABLE_CONTATO, Arrays.asList("Titi"));

    private TableColumnDataType columnDataType = new TableColumnDataType(NAME_TABLE_CONTATO, NAME_COLUMN, DATA_TYPE);

    @Test
    public void createTable() throws SQLException {
        CreateQuery.createTable(NAME_TABLE_CONTATO);
        Assertions.assertThat(NAME_TABLE_CONTATO).isEqualTo("contato");

        deleteTable(NAME_TABLE_CONTATO);
    }

    @Test
    public void addConlumnOfTheTable() throws SQLException {
        CreateQuery.createTable(NAME_TABLE_CONTATO);
        CreateQuery.addConlumnOfTheTable(NAME_TABLE_CONTATO, NAME_COLUMN, DATA_TYPE);

        deleteTable(NAME_TABLE_CONTATO);
    }

    @Test
    public void addConstraintNotNullConlumn() throws SQLException {
        CreateQuery.createTable(NAME_TABLE_CONTATO);
        CreateQuery.addConlumnOfTheTable(NAME_TABLE_CONTATO, NAME_COLUMN, DATA_TYPE);
        CreateQuery.addConstraintNotNullConlumn(NAME_TABLE_CONTATO, NAME_COLUMN);

        deleteTable(NAME_TABLE_CONTATO);
    }

    @Test
    public void alterDataTypeIsNameColumn() throws SQLException {
        CreateQuery.createTable(NAME_TABLE_CONTATO);
        CreateQuery.addConlumnOfTheTable(NAME_TABLE_CONTATO, NAME_COLUMN, DATA_TYPE);
        CreateQuery.alterDataTypeIsNameColumn(NAME_TABLE_CONTATO, NAME_COLUMN, DATA_TYPE_VAR);

        deleteTable(NAME_TABLE_CONTATO);
    }

    @Test
    public void alterTable() throws SQLException {
        CreateQuery.createTable(NAME_TABLE_CONTATO);
        CreateQuery.alterTable(NAME_TABLE_CONTATO, NAME_TABLE_CIDADE);

        deleteTable(NAME_TABLE_CIDADE);
    }

    @Test
    public void dropConstraintNotNullConlumn() throws SQLException {
        CreateQuery.createTable(NAME_TABLE_CONTATO);
        CreateQuery.addConlumnOfTheTable(NAME_TABLE_CONTATO, NAME_COLUMN, DATA_TYPE);
        CreateQuery.addConstraintNotNullConlumn(NAME_TABLE_CONTATO, NAME_COLUMN);
        CreateQuery.dropConstraintNotNullConlumn(NAME_TABLE_CONTATO, NAME_COLUMN);

        deleteTable(NAME_TABLE_CONTATO);
    }

    @Test
    public void dropColumnOfTheTable() throws SQLException {
        CreateQuery.createTable(NAME_TABLE_CONTATO);
        CreateQuery.addConlumnOfTheTable(NAME_TABLE_CONTATO, NAME_COLUMN, DATA_TYPE);
        CreateQuery.dropColumnOfTheTable(NAME_TABLE_CONTATO, NAME_COLUMN);

        deleteTable(NAME_TABLE_CONTATO);
    }

    @Test
    public void dropTable() throws SQLException {
        CreateQuery.createTable(NAME_TABLE_CONTATO);
        CreateQuery.dropTable(NAME_TABLE_CONTATO);
    }

    @Test
    public void insertInto() throws SQLException {
        CreateQuery.createTable(NAME_TABLE_CONTATO);
        NameTable idTable = NameTable.create(NAME_TABLE_CONTATO, nameTableRepository);
        CreateQuery.addConlumnOfTheTable(NAME_TABLE_CONTATO, NAME_COLUMN, DATA_TYPE);
        AddColumn.create(NAME_COLUMN, DATA_TYPE, idTable, addColumnRepository);


        NameTable idNameTable = nameTableRepository.findBayIdNameTable(NAME_TABLE_CONTATO);
        List<AddColumn> objectColumn = addColumnRepository.findBayNameColumn(idNameTable);

        CreateQuery.insertInto(parameter.getNameTable(), objectColumn, parameter.getParameters());

        queryService.dropTable(nameTableReq);
    }

    @Test
    public void selectId() throws SQLException {

        saveAll();
        Object id = CreateQuery.selectId(NAME_TABLE_CONTATO, NAME_COLUMN, "Kuririn");

        Assertions.assertThat(id).isEqualToComparingFieldByField(3L);

        queryService.dropTable(nameTableReq);
    }

    @Test
    public void selectIdRg() throws SQLException {
        List<? extends Serializable> array = Arrays.asList(3L, "Kuririn");

        List<Object> list = new ArrayList<>();
        list.add(array);

        Long id = 3L;
        saveAll();
        NameTable idNameTable = nameTableRepository.findBayIdNameTable(NAME_TABLE_CONTATO);
        List<AddColumn> objectColumn = addColumnRepository.findBayNameColumn(idNameTable);
        Object object = CreateQuery.selectIdRg(NAME_TABLE_CONTATO, objectColumn, id);

        Assertions.assertThat(object).isEqualTo(list);

        queryService.dropTable(nameTableReq);
    }

    @Test
    public void selectRg() throws SQLException {
        List<? extends Serializable> array = Arrays.asList(1L, "Goku", 2L, "Vegeta", 3L, "Kuririn", 4L, "Mestre Kame", 5L, "Titi");

        saveAll();
        NameTable idNameTable = nameTableRepository.findBayIdNameTable(NAME_TABLE_CONTATO);
        List<AddColumn> objectColumn = addColumnRepository.findBayNameColumn(idNameTable);
        Object object = CreateQuery.selectRg(NAME_TABLE_CONTATO, objectColumn);

        Assertions.assertThat(object).isEqualTo(array);

        queryService.dropTable(nameTableReq);
    }

    @Test
    public void updateAttributeRg() throws SQLException {
        saveAll();
        CreateQuery.updateAttributeRg(NAME_TABLE_CONTATO, "name",1L, "Super Sayadjin");

        queryService.dropTable(nameTableReq);
    }

    @Test
    public void updateRg() throws SQLException {
        saveAll();
        NameTable idNameTable = nameTableRepository.findBayIdNameTable(NAME_TABLE_CONTATO);
        List<AddColumn> objectColumn = addColumnRepository.findBayNameColumn(idNameTable);
        CreateQuery.updateRg(NAME_TABLE_CONTATO, objectColumn, parameter.getParameters(), 1L );

        queryService.dropTable(nameTableReq);
    }

    @Test
    public void deleteRg()  throws SQLException{
        saveAll();

        CreateQuery.deleteRg(NAME_TABLE_CONTATO, 3l);

        queryService.dropTable(nameTableReq);
    }

    @Test
    public void deleteRgAll()  throws SQLException{
        saveAll();
        CreateQuery.deleteRgAll(NAME_TABLE_CONTATO);

        queryService.dropTable(nameTableReq);
    }

    @Test
    public void deleteAddColumnIdTable() throws SQLException {

        saveAll();
        CreateQuery.deleteAddColumnIdTable(4L);

        queryService.dropTable(nameTableReq);
    }

    private void saveAll() throws SQLException {
        queryService.createTable(nameTableReq);
        queryService.createColumn(columnDataType);
        tableService.insertInto(parameter);
        tableService.insertInto(parameter1);
        tableService.insertInto(parameter2);
        tableService.insertInto(parameter3);
        tableService.insertInto(parameter4);
    }

    private void deleteTable(String nameTable) throws SQLException {
        if (nameTable.equals(NAME_TABLE_CONTATO)) {
            CreateQuery.dropTable(NAME_TABLE_CONTATO);
        } else {
            CreateQuery.dropTable(NAME_TABLE_CIDADE);
        }
    }
}