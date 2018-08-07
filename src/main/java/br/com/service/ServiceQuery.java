package br.com.service;

import br.com.table.NameModifiedTable;
import br.com.table.NameTableRequest;
import br.com.table.NameTableResponse;
import br.com.table.column.AddColumnRequest;
import br.com.table.column.AddColumnResponse;
import br.com.table.column.AlterDataTypeIsColumn;

import java.sql.SQLException;
public interface ServiceQuery {

     NameTableResponse createTable(NameTableRequest nameTable) throws SQLException;
     AddColumnResponse createColumn(AddColumnRequest parameter) throws SQLException;
     NameTableResponse updateNameTable(NameModifiedTable nameTable) throws SQLException;
     AddColumnResponse updateNameDataTypeIsNameConlumn(AlterDataTypeIsColumn parameter) throws SQLException;

}
