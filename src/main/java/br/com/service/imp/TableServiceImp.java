package br.com.service.imp;

import br.com.query.CreateQuery;
import br.com.repository.AddColumnRepository;
import br.com.repository.NameTableRepository;
import br.com.service.TableService;
import br.com.table.NameTable;
import br.com.table.ObjectParameter;
import br.com.table.column.AddColumn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TableServiceImp implements TableService{

    private NameTableRepository nameTableRepository;
    private AddColumnRepository addColumnRepository;

    @Autowired
    public TableServiceImp(NameTableRepository nameTableRepository, AddColumnRepository addColumnRepository) {
        this.nameTableRepository = nameTableRepository;
        this.addColumnRepository = addColumnRepository;
    }

    @Override
    public ObjectParameter insertInto(ObjectParameter parameter) throws SQLException {

        NameTable idNameTable = nameTableRepository.findBayIdNameTable(parameter.getNameTable());

        List<AddColumn> objectColumn = addColumnRepository.findBayNameColumn(idNameTable);

        CreateQuery.insertInto(parameter.getNameTable(),objectColumn, parameter.getParameters());
        return new ObjectParameter(parameter.getNameTable(), parameter.getParameters());
    }
}

