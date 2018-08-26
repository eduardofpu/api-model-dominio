package br.com.Controller;

import br.com.representation.AddColumnRepresentation;
import br.com.representation.NameTableRepresentation;
import br.com.service.ViewService;
import br.com.table.column.AddColumn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ViewController {
    @Autowired
    private ViewService viewService;

    @GetMapping(path = "/tables")
    @ResponseStatus(HttpStatus.OK)
    Page<NameTableRepresentation> findAllTables(Pageable pageable) {
        return viewService.findAllTable(pageable);
    }

    @GetMapping(path = "/columns")
    @ResponseStatus(HttpStatus.OK)
    Page<AddColumnRepresentation> findAllColumn(Pageable pageable) {
        return viewService.findAllColumn(pageable);
    }

    @GetMapping(path = "/{nameTable}")
    @ResponseStatus(HttpStatus.OK)
    List<AddColumn> findBayTable(@PathVariable("nameTable") String nameTable) {
        return viewService.findBayTable(nameTable);
    }
}
