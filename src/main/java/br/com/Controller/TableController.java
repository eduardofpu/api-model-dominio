package br.com.Controller;

import br.com.service.TableService;
import br.com.table.NameTableReq;
import br.com.table.ObjectParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/")
public class TableController {

    private TableService tableService;

    @Autowired
    public TableController(TableService tableService) {
        this.tableService = tableService;
    }

    @PostMapping(path = "insert/table")
    @ResponseStatus(HttpStatus.OK)
    public ObjectParameter insertInto(@RequestBody ObjectParameter parameter) throws SQLException {
        return tableService.insertInto(parameter);
    }
}
