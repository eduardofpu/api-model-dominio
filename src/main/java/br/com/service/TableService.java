package br.com.service;

import br.com.table.NameTableReq;
import br.com.table.ObjectParameter;

import java.sql.SQLException;

public interface TableService {

   ObjectParameter insertInto(ObjectParameter parameter) throws SQLException;
}
