package br.com.Controller;

import br.com.table.CurrentModified;
import br.com.table.NameT1IsT2;
import br.com.table.NameTableReq;
import br.com.table.idTableResp;
import br.com.service.QueryService;
import br.com.table.column.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/")
public class ControllerQuery {

    @Autowired
    private QueryService serviceQuery;


    @PostMapping(path = "create/table")
    @ResponseStatus(HttpStatus.CREATED)
    public idTableResp createTable(@RequestBody NameTableReq nameTable) throws SQLException {
       return serviceQuery.createTable(nameTable);
    }

    @PostMapping(path = "add/column")
    @ResponseStatus(HttpStatus.CREATED)
    public idColumnResp createColumn(@RequestBody TableColumnDataType parameter) throws SQLException {
        return serviceQuery.createColumn(parameter);
    }

    @PutMapping(path = "alter/table")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public idTableResp updateNameTable(@RequestBody CurrentModified parameter) throws SQLException {
        return serviceQuery.updateNameTable(parameter);
    }

    @PutMapping(path = "alter/data/type/column")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public idColumnResp updateNameDataTypeIsNameConlumn(@RequestBody TableColumnDataType parameter) throws SQLException {
        return serviceQuery.updateNameDataTypeIsNameConlumn(parameter);
    }

    @PutMapping(path = "add/not/null")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public idColumnResp addConstraintNotNullConlumn(@RequestBody TableColumnReq parameter) throws SQLException {
        return serviceQuery.addConstraintNotNullConlumn(parameter);
    }

    @PostMapping(path = "add/foreign/key")
    @ResponseStatus(HttpStatus.CREATED)
    public NameT1IsT2 addForeignKey(@RequestBody NameT1IsT2 parameter) throws SQLException {
        return serviceQuery.addForeignKey(parameter);
    }

    @PutMapping(path = "drop/not/null")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public idColumnResp dropConstraintNotNullConlumn(@RequestBody TableColumnReq parameter) throws SQLException {
        return serviceQuery.dropConstraintNotNullConlumn(parameter);
    }

    @DeleteMapping(path = "drop/column")
    @ResponseStatus(HttpStatus.OK)
    public void dropColumnOfTheTable(@RequestBody TableColumnReq parameter) throws SQLException {
        serviceQuery.dropColumnOfTheTable(parameter);
    }

    @DeleteMapping(path = "drop/table")
    @ResponseStatus(HttpStatus.OK)
    public void dropTable(@RequestBody NameTableReq parameter) throws SQLException {
        serviceQuery.dropTable(parameter);
    }
}
