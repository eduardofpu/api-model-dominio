package br.com.table.column;

import br.com.repository.RepositoryAddColumn;
import br.com.service.Validator;
import br.com.table.NameTable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class AddColumn {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String nameColumn;
    private String dataType;

    @ManyToOne
    NameTable idTable;


    public AddColumn(String nameColumn, String dataType, NameTable idTable) {
        this.nameColumn = nameColumn;
        this.dataType = dataType;
        this.idTable = idTable;
    }

    public static AddColumn create(String nameColumn, String dataType,NameTable idTable, RepositoryAddColumn repository){
        AddColumn column = new AddColumn(nameColumn, dataType, idTable);
        return repository.save(column);
    }

    public static AddColumn updateDataTypeIsColumn(Long id, String nameColumn, String dataType, NameTable idTable, RepositoryAddColumn repository, Validator validator){

        validator.verifyIfIdAddCollumnExists(id);
        AddColumn table = new AddColumn(id, nameColumn, dataType, idTable);
        return repository.save(table);
    }


}
