package br.com.Controller;

import br.com.table.NameModifiedTable;
import br.com.table.NameTableRequest;
import br.com.table.NameTableResponse;
import br.com.service.ServiceQuery;
import br.com.table.column.AddColumnRequest;
import br.com.table.column.AddColumnResponse;
import br.com.table.column.AlterDataTypeIsColumn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/")
public class ControllerQuery {

    @Autowired
    private ServiceQuery serviceQuery;


    @PostMapping(path = "create/table")
    @ResponseStatus(HttpStatus.CREATED)
    public NameTableResponse createTable(@RequestBody NameTableRequest nameTable) throws SQLException {
       return serviceQuery.createTable(nameTable);
    }

    @PostMapping(path = "add/column")
    @ResponseStatus(HttpStatus.CREATED)
    public AddColumnResponse createColumn(@RequestBody AddColumnRequest parameter) throws SQLException {
        return serviceQuery.createColumn(parameter);
    }

    @PutMapping(path = "alter/table")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public NameTableResponse updateNameTable(@RequestBody NameModifiedTable parameter) throws SQLException {
        return serviceQuery.updateNameTable(parameter);
    }

    @PutMapping(path = "alter/data/type/column")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public AddColumnResponse updateNameDataTypeIsNameConlumn(@RequestBody AlterDataTypeIsColumn parameter) throws SQLException {
        return serviceQuery.updateNameDataTypeIsNameConlumn(parameter);
    }
}
