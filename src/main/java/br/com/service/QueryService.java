package br.com.service;

import br.com.table.CurrentModified;
import br.com.table.NameT1IsT2;
import br.com.table.NameTableReq;
import br.com.table.idTableResp;
import br.com.table.column.*;

import java.sql.SQLException;
public interface QueryService {

     idTableResp createTable(NameTableReq nameTable) throws SQLException;
     idColumnResp createColumn(TableColumnDataType parameter) throws SQLException;
     idTableResp updateNameTable(CurrentModified nameTable) throws SQLException;
     idColumnResp updateNameDataTypeIsNameConlumn(TableColumnDataType parameter) throws SQLException;
     idColumnResp addConstraintNotNullConlumn(TableColumnReq parameter) throws  SQLException;
     NameT1IsT2 addForeignKey(NameT1IsT2 parameter) throws SQLException;
     idColumnResp dropConstraintNotNullConlumn(TableColumnReq parameter) throws SQLException;
     void dropColumnOfTheTable(TableColumnReq parameter) throws SQLException;
     void dropTable(NameTableReq parameter) throws SQLException;

}
