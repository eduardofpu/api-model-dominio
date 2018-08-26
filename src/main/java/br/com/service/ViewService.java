package br.com.service;

import br.com.representation.NameTableRepresentation;
import br.com.representation.AddColumnRepresentation;
import br.com.table.column.AddColumn;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ViewService {
    Page<NameTableRepresentation> findAllTable(Pageable pageable);

    Page<AddColumnRepresentation> findAllColumn(Pageable pageable);

    List<AddColumn> findBayTable(String nameTable);
}
