package br.com.service;


import br.com.AbstractTest;
import br.com.table.CurrentModified;
import br.com.table.NameTableReq;
import br.com.table.column.TableColumnDataType;
import br.com.table.column.TableColumnReq;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.sql.SQLException;

public class QueryServiceTest extends AbstractTest{

    @Test
    public void createTable()throws SQLException {

        NameTableReq nameTableReq = saveTable();

        deleteTable(nameTableReq);
    }

    @Test
    public void createColumn()throws SQLException {

        NameTableReq nameTableReq = saveTable();

        TableColumnDataType parameter = new TableColumnDataType(nameTableReq.getNameTable(),"name","text");
        queryService.createColumn(parameter);
        Assertions.assertThat(parameter.getNameTable()).isEqualTo("contato");
        Assertions.assertThat(parameter.getNameColumn()).isEqualTo("name");
        Assertions.assertThat(parameter.getDataType()).isEqualTo("text");

        deleteTable(nameTableReq);
    }

    @Test
    public void updateNameTable()throws SQLException {

        NameTableReq nameTableReq = saveTable();

        CurrentModified currentModified = new CurrentModified(nameTableReq.getNameTable(),"cidade");
        queryService.updateNameTable(currentModified);
        Assertions.assertThat(currentModified.getNameCurrent()).isEqualTo(nameTableReq.getNameTable());
        Assertions.assertThat(currentModified.getNameModified()).isEqualTo(currentModified.getNameModified());

        NameTableReq nameModified = new NameTableReq(currentModified.getNameModified());

        queryService.dropTable(nameModified);
        Assertions.assertThat(nameModified.getNameTable()).isEqualTo("cidade");
    }

    @Test
    public void updateNameDataTypeIsNameConlumn()throws SQLException {

        NameTableReq nameTableReq = saveTable();

        TableColumnDataType parameter = new TableColumnDataType(nameTableReq.getNameTable(),"name","text");
        queryService.createColumn(parameter);
        Assertions.assertThat(parameter.getNameTable()).isEqualTo("contato");
        Assertions.assertThat(parameter.getNameColumn()).isEqualTo("name");
        Assertions.assertThat(parameter.getDataType()).isEqualTo("text");

        TableColumnDataType dataType = new TableColumnDataType(nameTableReq.getNameTable(),parameter.getNameColumn(),"VARCHAR(45)");
        queryService.updateNameDataTypeIsNameConlumn(dataType);
        Assertions.assertThat(dataType.getNameTable()).isEqualTo(nameTableReq.getNameTable());
        Assertions.assertThat(dataType.getNameColumn()).isEqualTo(parameter.getNameColumn());
        Assertions.assertThat(dataType.getDataType()).isEqualTo("VARCHAR(45)");

        deleteTable(nameTableReq);
    }

    @Test
    public void addConstraintNotNullConlumn()throws SQLException {

        NameTableReq nameTableReq = saveTable();

        TableColumnDataType parameter = new TableColumnDataType(nameTableReq.getNameTable(),"name","text");
        queryService.createColumn(parameter);

        Assertions.assertThat(parameter.getNameTable()).isEqualTo("contato");
        Assertions.assertThat(parameter.getNameColumn()).isEqualTo("name");
        Assertions.assertThat(parameter.getDataType()).isEqualTo("text");

        TableColumnReq columnReq = new TableColumnReq(nameTableReq.getNameTable(),parameter.getNameColumn());
        queryService.addConstraintNotNullConlumn(columnReq);
        Assertions.assertThat(columnReq.getNameTable()).isEqualTo(nameTableReq.getNameTable());
        Assertions.assertThat(columnReq.getNameColumn()).isEqualTo(parameter.getNameColumn());

        deleteTable(nameTableReq);
    }

    @Test
    public void dropConstraintNotNullConlumn()throws SQLException {

        NameTableReq nameTableReq = saveTable();

        TableColumnDataType parameter = new TableColumnDataType(nameTableReq.getNameTable(),"name","text");
        queryService.createColumn(parameter);

        Assertions.assertThat(parameter.getNameTable()).isEqualTo("contato");
        Assertions.assertThat(parameter.getNameColumn()).isEqualTo("name");
        Assertions.assertThat(parameter.getDataType()).isEqualTo("text");

        TableColumnReq columnReq = new TableColumnReq(nameTableReq.getNameTable(),parameter.getNameColumn());
        queryService.addConstraintNotNullConlumn(columnReq);
        queryService.dropConstraintNotNullConlumn(columnReq);
        Assertions.assertThat(columnReq.getNameTable()).isEqualTo(nameTableReq.getNameTable());
        Assertions.assertThat(columnReq.getNameColumn()).isEqualTo(parameter.getNameColumn());

        deleteTable(nameTableReq);

    }

    @Test
    public void dropColumnOfTheTable()throws SQLException {

        NameTableReq nameTableReq = saveTable();

        TableColumnDataType parameter = new TableColumnDataType(nameTableReq.getNameTable(),"name","text");
        queryService.createColumn(parameter);

        Assertions.assertThat(parameter.getNameTable()).isEqualTo("contato");
        Assertions.assertThat(parameter.getNameColumn()).isEqualTo("name");
        Assertions.assertThat(parameter.getDataType()).isEqualTo("text");

        TableColumnReq columnReq = new TableColumnReq(nameTableReq.getNameTable(),parameter.getNameColumn());
        queryService.dropColumnOfTheTable(columnReq);

        Assertions.assertThat(columnReq.getNameTable()).isEqualTo("contato");
        Assertions.assertThat(columnReq.getNameColumn()).isEqualTo(parameter.getNameColumn());

        deleteTable(nameTableReq);
    }

    @Test
    public void dropTable()throws SQLException {

        NameTableReq nameTableReq = saveTable();

        deleteTable(nameTableReq);

    }

    private void deleteTable(NameTableReq nameTableReq) throws SQLException {
        queryService.dropTable(nameTableReq);
        Assertions.assertThat(nameTableReq.getNameTable()).isEqualTo("contato");
    }

    private NameTableReq saveTable() throws SQLException {
        NameTableReq nameTableReq = new NameTableReq("contato");

        queryService.createTable(nameTableReq);
        Assertions.assertThat(nameTableReq.getNameTable()).isEqualTo("contato");
        return nameTableReq;
    }
}