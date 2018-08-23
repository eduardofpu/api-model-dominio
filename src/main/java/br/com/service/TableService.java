package br.com.service;

import br.com.table.ObjectParameter;
import br.com.table.SelectTable;
import br.com.table.UpdateTableReq;

import java.sql.SQLException;

public interface TableService {
   Object  findAll(String nameTable) throws SQLException;
   SelectTable findById(String nameTable, Long id) throws SQLException;
   ObjectParameter insertInto(ObjectParameter parameter) throws SQLException;
   Object updateAttributeRg(UpdateTableReq parameter) throws SQLException;
   ObjectParameter updateRg(ObjectParameter parameter, Long id) throws SQLException;
   void deleteRg(String nameTable, Long id) throws SQLException;
   void deleteRgAll(String nameTable) throws SQLException;
}
