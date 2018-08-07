package br.com.repository;

import br.com.table.NameTable;
import br.com.table.column.AddColumn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RepositoryAddColumn extends JpaRepository<AddColumn, Long> {
    //select a.id_table_id from public.name_table t inner join public.add_column a on a.id_table_id = t.id and a.name_column = 'name';
    //@Query("select a from  AddColumn a where a.nameColumn = ?")

   //@Query("SELECT c.idTable from NameTable t INNER JOIN AddColumn c ON c.idTable = t.id AND c.nameColumn = ?")
    @Query("select  c.idTable from AddColumn c where c.nameColumn = ?")
    NameTable findBayIdNameDataType(String name);
}
