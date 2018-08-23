package br.com.table.column;

import br.com.AbstractTest;
import br.com.table.NameTable;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class AddColumnTest extends AbstractTest{

    @Test
    public void create(){
        NameTable id = createTable();

        AddColumn idColumn = AddColumn.create("name","text",id, addColumnRepository);
        AddColumn addCreatede = addColumnRepository.findOne(idColumn.getId());

        Assertions.assertThat(idColumn.getId()).isNotNull();
        Assertions.assertThat(idColumn.getNameColumn()).isEqualTo(addCreatede.getNameColumn());
        Assertions.assertThat(idColumn.getDataType()).isEqualTo(addCreatede.getDataType());
        Assertions.assertThat(idColumn.getIdTable()).isEqualTo(addCreatede.getIdTable());
    }


    @Test
    public void updateDataTypeIsColumn(){
        NameTable id = createTable();
        AddColumn nameCreatede = addColumn(id);

        AddColumn cod = AddColumn.updateDataTypeIsColumn(nameCreatede.getId(),"name","text",id,addColumnRepository,validatorService);
        AddColumn nameUpdate = addColumnRepository.findOne(cod.getId());

        Assertions.assertThat(cod.getId()).isNotNull();
        Assertions.assertThat(cod.getId()).isEqualTo(nameUpdate.getId());
        Assertions.assertThat(cod.getDataType()).isEqualTo(nameUpdate.getDataType());
        Assertions.assertThat(cod.getIdTable()).isEqualTo(nameUpdate.getIdTable());
    }

    @Test
    public void deleteColumn(){

        NameTable id = createTable();

        AddColumn nameCreatede = addColumn(id);
        AddColumn.deleteColumn(nameCreatede.getId(), addColumnRepository, validatorService);

    }

    private AddColumn addColumn(NameTable id) {
        AddColumn idColumn = AddColumn.create("name","text",id,addColumnRepository);
        AddColumn nameCreatede = addColumnRepository.findOne(idColumn.getId());

        Assertions.assertThat(idColumn.getId()).isNotNull();
        Assertions.assertThat(idColumn.getNameColumn()).isEqualTo(nameCreatede.getNameColumn());
        Assertions.assertThat(idColumn.getDataType()).isEqualTo(nameCreatede.getDataType());
        Assertions.assertThat(idColumn.getIdTable()).isEqualTo(nameCreatede.getIdTable());
        return nameCreatede;
    }

    private NameTable createTable() {
        NameTable id = NameTable.create("contato", nameTableRepository);
        NameTable nameCreatede = nameTableRepository.findOne(id.getId());

        Assertions.assertThat(id.getId()).isNotNull();
        Assertions.assertThat(id.getNameTable()).isEqualTo(nameCreatede.getNameTable());
        return id;
    }
}