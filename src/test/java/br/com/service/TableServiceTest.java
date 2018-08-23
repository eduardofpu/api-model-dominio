package br.com.service;

import br.com.AbstractTest;
import br.com.table.NameTableReq;
import br.com.table.ObjectParameter;
import br.com.table.SelectTable;
import br.com.table.UpdateTableReq;
import br.com.table.column.TableColumnDataType;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Arrays;

public class TableServiceTest extends AbstractTest {

    private final String nameTable = "contato";
    private final Long id = 2L;
    NameTableReq nameTableReq = new NameTableReq(nameTable);

    @Test
    public void findAll() throws SQLException {
        insertThree();
        Object contatos = tableService.findAll("contato");
        Assertions.assertThat(contatos).isNotNull();
        dropTable();
    }

    @Test
    public void findById() throws SQLException {
        insertThree();
        SelectTable byId = tableService.findById(nameTable, 1L);

        Assertions.assertThat(byId.getNameTable()).isEqualTo("contato");
        Assertions.assertThat(byId.getParameters()).isNotNull();
        dropTable();
    }

    @Test
    public void insertInto() throws SQLException {
        createTable();
        ObjectParameter objectParameter = new ObjectParameter(nameTable, Arrays.asList("Goku", "21", "M"));
        tableService.insertInto(objectParameter);
        Assertions.assertThat(objectParameter.getNameTable()).isEqualTo(nameTable.toString());
        Assertions.assertThat(objectParameter.getParameters()).isNotNull();
        dropTable();
    }


    @Test
    public void updateAttributeRg() throws SQLException {
        insertThree();
        UpdateTableReq updateTableReq = new UpdateTableReq(nameTable, "name", 1L, "Super Sayajin");
        tableService.updateAttributeRg(updateTableReq);
        dropTable();
    }

    @Test
    public void updateRg() throws SQLException {

        insertThree();
        ObjectParameter objectParameter = new ObjectParameter(nameTable, Arrays.asList("Vegeta", "27", "M"));
        tableService.updateRg(objectParameter, id);

        Assertions.assertThat(objectParameter.getNameTable()).isEqualTo(nameTable);
        Assertions.assertThat(objectParameter.getParameters()).isNotNull();
        Assertions.assertThat(id).isEqualTo(2L);

        dropTable();
    }

    @Test
    public void deleteRg() throws SQLException {

        insertThree();
        tableService.deleteRg(nameTable, id);
        Assertions.assertThat(nameTable).isEqualTo("contato");
        Assertions.assertThat(id).isEqualTo(2L);
        dropTable();
    }

    @Test
    public void deleteRgAll() throws SQLException {
        insertThree();
        tableService.deleteRgAll(nameTable);
        Assertions.assertThat(nameTable).isEqualTo("contato");
        dropTable();
    }


    private void createTable() throws SQLException {
        queryService.createTable(nameTableReq);
        createThreeColumn();
    }

    private void createThreeColumn() throws SQLException {
        TableColumnDataType parameter = new TableColumnDataType(nameTableReq.getNameTable(),"name","text");
        queryService.createColumn(parameter);
        Assertions.assertThat(parameter.getNameTable()).isEqualTo("contato");
        Assertions.assertThat(parameter.getNameColumn()).isEqualTo("name");
        Assertions.assertThat(parameter.getDataType()).isEqualTo("text");

        TableColumnDataType parameter2 = new TableColumnDataType(nameTableReq.getNameTable(),"idade","text");
        queryService.createColumn(parameter2);
        Assertions.assertThat(parameter2.getNameTable()).isEqualTo("contato");
        Assertions.assertThat(parameter2.getNameColumn()).isEqualTo("idade");
        Assertions.assertThat(parameter2.getDataType()).isEqualTo("text");

        TableColumnDataType parameter3 = new TableColumnDataType(nameTableReq.getNameTable(),"sexo","text");
        queryService.createColumn(parameter3);
        Assertions.assertThat(parameter3.getNameTable()).isEqualTo("contato");
        Assertions.assertThat(parameter3.getNameColumn()).isEqualTo("sexo");
        Assertions.assertThat(parameter3.getDataType()).isEqualTo("text");
    }

    private void insertThree() throws SQLException {
        createTable();

        ObjectParameter objectParameter = new ObjectParameter(nameTable, Arrays.asList("Goku", "21", "M"));
        tableService.insertInto(objectParameter);

        ObjectParameter objectParameter2 = new ObjectParameter(nameTable, Arrays.asList("Mestre Kame", "70", "M"));
        tableService.insertInto(objectParameter2);

        ObjectParameter objectParameter3 = new ObjectParameter(nameTable, Arrays.asList("Kuririn", "22", "M"));
        tableService.insertInto(objectParameter3);
    }

    private void dropTable() throws SQLException {
        queryService.dropTable(nameTableReq);
        Assertions.assertThat(nameTableReq.getNameTable()).isEqualTo("contato");
    }

}