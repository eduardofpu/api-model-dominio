package br.com.service.imp;

import br.com.repository.AddColumnRepository;
import br.com.repository.NameTableRepository;
import br.com.representation.AddColumnBuilder;
import br.com.representation.AddColumnRepresentation;
import br.com.representation.NameBuilder;
import br.com.representation.NameTableRepresentation;
import br.com.service.ViewService;
import br.com.table.NameTable;
import br.com.table.column.AddColumn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViewServiceImp implements ViewService{

    private NameTableRepository nameTableRepository;
    private AddColumnRepository addColumnRepository;

    @Autowired
    public ViewServiceImp(NameTableRepository nameTableRepository, AddColumnRepository addColumnRepository) {
        this.nameTableRepository = nameTableRepository;
        this.addColumnRepository = addColumnRepository;
    }

    @Override
    public Page<NameTableRepresentation> findAllTable(Pageable pageable) {
        Page<NameTable> nameTables =nameTableRepository.findAll(pageable);
        return NameBuilder.builder().getTables(nameTables).build();
    }

    @Override
    public Page<AddColumnRepresentation> findAllColumn(Pageable pageable) {
        Page<AddColumn> columns =addColumnRepository.findAll(pageable);
        return AddColumnBuilder.builder().getColumn(columns).build();
    }

    @Override
    public List<AddColumn> findBayTable(String nameTable) {
        NameTable idNameTable = nameTableRepository.findBayIdNameTable(nameTable);
        return addColumnRepository.findBayTable(idNameTable);
    }
}
