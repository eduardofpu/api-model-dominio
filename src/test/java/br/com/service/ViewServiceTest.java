package br.com.service;

import br.com.AbstractTest;
import br.com.representation.AddColumnRepresentation;
import br.com.representation.NameTableRepresentation;
import br.com.table.NameTableReq;
import br.com.table.column.AddColumn;
import br.com.table.column.TableColumnDataType;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ViewServiceTest extends AbstractTest{

    private NameTableReq nameTableReq = new NameTableReq("contato");
    private NameTableReq nameTableReq2 = new NameTableReq("cidade");

    private TableColumnDataType column = new TableColumnDataType(nameTableReq.getNameTable(), "name", "text");
    private TableColumnDataType column2 = new TableColumnDataType(nameTableReq2.getNameTable(), "name", "text");


    @Test
    public void findAllTable() throws SQLException {

        createTable();
        Page<NameTableRepresentation> names = viewService.findAllTable(new PageRequest(0, 100));
        Assertions.assertThat(names.getTotalElements()).isEqualTo(nameTableRepository.count());

        drope();
    }

    @Test
    public void findAllColumn() throws SQLException {

        createTable();
        createColumn();

        Page<AddColumnRepresentation> names = viewService.findAllColumn(new PageRequest(0, 100));
        Assertions.assertThat(names.getTotalElements()).isEqualTo(addColumnRepository.count());

        drope();
    }

    @Test
    public void findBayTable() throws SQLException {

        createTable();
        createColumn();
        List<AddColumn> contato = viewService.findBayTable("contato");

        Optional<AddColumn> first = contato.stream().filter(s -> s.getNameColumn().equals("name")).findFirst();
        Assert.assertNotNull(first);

        drope();
    }


    private void createTable() throws SQLException {
        queryService.createTable(nameTableReq);
        queryService.createTable(nameTableReq2);
    }

    private void createColumn() throws SQLException {
        queryService.createColumn(column);
        queryService.createColumn(column2);
    }


    private void drope() throws SQLException {
        queryService.dropTable(nameTableReq);
        queryService.dropTable(nameTableReq2);
    }
}