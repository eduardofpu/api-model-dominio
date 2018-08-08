package br.com.service;

import br.com.table.NameModifiedTable;
import br.com.table.NameTableReq;
import br.com.table.NameTableRequest;
import br.com.table.NameTableResponse;
import br.com.table.column.*;

import java.sql.SQLException;
public interface ServiceQuery {

     NameTableResponse createTable(NameTableRequest nameTable) throws SQLException;
     AddColumnResponse createColumn(AddColumnRequest parameter) throws SQLException;
     NameTableResponse updateNameTable(NameModifiedTable nameTable) throws SQLException;
     AddColumnResponse updateNameDataTypeIsNameConlumn(AlterDataTypeIsColumn parameter) throws SQLException;
     AddColumnResponse addConstraintNotNullConlumn(AddColumnNameTableReq parameter) throws  SQLException;
     NameTableReq addForeignKey(NameTableReq parameter) throws SQLException;
     AddColumnResponse dropConstraintNotNullConlumn(AddColumnNameTableReq parameter) throws SQLException;
     void dropColumnOfTheTable(DropColumn parameter) throws SQLException;
     void dropTable(NameTableRequest parameter) throws SQLException;

}
