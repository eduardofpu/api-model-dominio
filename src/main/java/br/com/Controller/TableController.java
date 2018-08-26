package br.com.Controller;

import br.com.service.TableService;
import br.com.table.ObjectParameter;
import br.com.table.SelectTable;
import br.com.table.UpdateTableReq;
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

    @GetMapping(path = "get/{nameTable}")
    @ResponseStatus(HttpStatus.OK)
    public Object findAll(@PathVariable("nameTable") String nameTable) throws SQLException {
        return tableService.findAll(nameTable);
    }

    @GetMapping(path = "get/{nameTable}/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SelectTable findById(@PathVariable("nameTable") String nameTable, @PathVariable("id") Long id) throws SQLException {

        return tableService.findById(nameTable, id);
    }

    @PostMapping(path = "insert/table")
    @ResponseStatus(HttpStatus.OK)
    public ObjectParameter insertInto(@RequestBody ObjectParameter parameter) throws SQLException {
        return tableService.insertInto(parameter);
    }

    @PutMapping(path = "update")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Object updateAttributeRg(@RequestBody UpdateTableReq parameter) throws SQLException {
        return tableService.updateAttributeRg(parameter);
    }

    @PutMapping(path = "update/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ObjectParameter updateRg(@RequestBody ObjectParameter parameter,@PathVariable("id") Long id) throws SQLException {
        return tableService.updateRg(parameter, id);
    }

    @DeleteMapping(path = "delete/{nameTable}/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteRg(@PathVariable("nameTable") String nameTable, @PathVariable("id") Long id) throws SQLException {
        tableService.deleteRg(nameTable, id);
    }

    @DeleteMapping(path = "delete/{nameTable}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteRgAll(@PathVariable("nameTable") String nameTable) throws SQLException {
        tableService.deleteRgAll(nameTable);
    }
}
