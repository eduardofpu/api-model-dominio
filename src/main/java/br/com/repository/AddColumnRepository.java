package br.com.repository;

import br.com.table.NameTable;
import br.com.table.column.AddColumn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AddColumnRepository extends JpaRepository<AddColumn, Long> {

    @Query("select  c.idTable from AddColumn c where c.nameColumn = ?")
    NameTable findBayIdNameDataType(String name);

    @Query("from AddColumn c where c.nameColumn = ?")
    AddColumn findBayAddColum(String name);

    @Query("select c.id from AddColumn c where c.nameColumn = ?")
    AddColumn findBayIdAddColum(String name);

    @Query("select c.id from AddColumn c where c.nameColumn = ? AND c.idTable = ?")
    AddColumn findBayIdTable(String nameColumn, NameTable id);

    @Query("select c.nameColumn from AddColumn c where c.idTable = ?")
    List<AddColumn> findBayNameColumn(NameTable id);

    @Query("select c from AddColumn c where c.idTable = ?")
    List<AddColumn> findBayTable(NameTable id);

    @Query("select c.nameColumn from AddColumn c where c.idTable = ? and c.nameColumn = ?")
    String findBayNameColumnReplay(NameTable id, String nameColumn);
}
