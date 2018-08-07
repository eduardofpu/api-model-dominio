package br.com.repository;

import br.com.table.NameTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RepositoryNameTable extends JpaRepository<NameTable, Long>{
   @Query("select t.id from NameTable t where t.nameTable = ?")
   NameTable findBayIdNameTable(String nameTable);
}
