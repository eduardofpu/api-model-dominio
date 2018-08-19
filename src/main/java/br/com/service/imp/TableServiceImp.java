package br.com.service.imp;

import br.com.query.CreateQuery;
import br.com.repository.AddColumnRepository;
import br.com.repository.NameTableRepository;
import br.com.service.TableService;
import br.com.table.NameTable;
import br.com.table.ObjectParameter;
import br.com.table.SelectTable;
import br.com.table.UpdateTableReq;
import br.com.table.column.AddColumn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class TableServiceImp implements TableService {

    private NameTableRepository nameTableRepository;
    private AddColumnRepository addColumnRepository;

    @Autowired
    public TableServiceImp(NameTableRepository nameTableRepository, AddColumnRepository addColumnRepository) {
        this.nameTableRepository = nameTableRepository;
        this.addColumnRepository = addColumnRepository;
    }

    @Override
    public Object  findAll(String nameTable) throws SQLException {
        NameTable idNameTable = nameTableRepository.findBayIdNameTable(nameTable);
        List<AddColumn> objectColumn = addColumnRepository.findBayNameColumn(idNameTable);
        return new SelectTable(nameTable, CreateQuery.selectRg(nameTable, objectColumn));
    }

    @Override
    public SelectTable findById(String nameTable, Long id) throws SQLException {

        NameTable idNameTable = nameTableRepository.findBayIdNameTable(nameTable);
        List<AddColumn> objectColumn = addColumnRepository.findBayNameColumn(idNameTable);
        return new SelectTable(nameTable, CreateQuery.selectIdRg(nameTable, objectColumn, id));
    }

    @Override
    public ObjectParameter insertInto(ObjectParameter parameter) throws SQLException {

        NameTable idNameTable = nameTableRepository.findBayIdNameTable(parameter.getNameTable());

        List<AddColumn> objectColumn = addColumnRepository.findBayNameColumn(idNameTable);

        CreateQuery.insertInto(parameter.getNameTable(), objectColumn, parameter.getParameters());
        return new ObjectParameter(parameter.getNameTable(), parameter.getParameters());
    }

    @Override
    public Object updateAttributeRg(UpdateTableReq parameter) throws SQLException {

        CreateQuery.updateAttributeRg(parameter.getNameTable(), parameter.getAttribute(), parameter.getId(), parameter.getParameter());
        return new UpdateTableReq(parameter.getNameTable(), parameter.getAttribute(), parameter.getId(), parameter.getParameter());
    }

    @Override
    public ObjectParameter updateRg(ObjectParameter parameter, Long id) throws SQLException {
        NameTable idNameTable = nameTableRepository.findBayIdNameTable(parameter.getNameTable());

        List<AddColumn> objectColumn = addColumnRepository.findBayNameColumn(idNameTable);

        CreateQuery.updateRg(parameter.getNameTable(), objectColumn, parameter.getParameters(), id);
        return new ObjectParameter(parameter.getNameTable(), parameter.getParameters());
    }

    @Override
    public void deleteRg(String nameTable, Long id) throws SQLException {
        CreateQuery.deleteRg(nameTable, id);
    }

    @Override
    public void deleteRgAll(String nameTable) throws SQLException {
        CreateQuery.deleteRgAll(nameTable);
    }
}

