package br.com.table;

import br.com.repository.NameTableRepository;
import br.com.service.ValidatorService;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode
@Entity
public class NameTable {

   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE)
   private Long id;
   private String nameTable;

    public NameTable(Long id) {
        this.id = id;
    }

    public NameTable(String nameTable) {
        this.nameTable = nameTable;
    }

    public static NameTable create(String nameTable, NameTableRepository repository){
       NameTable table = new NameTable(nameTable);
       return repository.save(table);
   }

    public static NameTable updateTable(Long id, String nameTable, NameTableRepository repository, ValidatorService validator){

        validator.verifyINameTableIdExists(id);
        NameTable table = new NameTable(id, nameTable);
        return repository.save(table);
    }

    public static void deleteTable(Long id, NameTableRepository repository, ValidatorService validator){
        validator.verifyINameTableIdExists(id);
        NameTable table = new NameTable(id);
        repository.delete(table.getId());
    }
}
