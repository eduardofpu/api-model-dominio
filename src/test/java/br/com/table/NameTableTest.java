package br.com.table;

import br.com.AbstractTest;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class NameTableTest extends AbstractTest {

    @Test
    public void create() {

        NameTable id = NameTable.create("contato", nameTableRepository);
        NameTable nameCreatede = nameTableRepository.findOne(id.getId());

        Assertions.assertThat(id.getId()).isNotNull();
        Assertions.assertThat(id.getNameTable()).isEqualTo(nameCreatede.getNameTable());

    }

    @Test
    public void updateTable() {

        NameTable id = createNameTable();

        NameTable cod = NameTable.updateTable(id.getId(), "cidade", nameTableRepository, validatorService);
        NameTable nameUpdate = nameTableRepository.findOne(cod.getId());

        Assertions.assertThat(cod.getId()).isNotNull();
        Assertions.assertThat(cod.getNameTable()).isEqualTo(nameUpdate.getNameTable());
    }



    @Test
    public void deleteTable() {

        NameTable id = createNameTable();
        NameTable.deleteTable(id.getId(), nameTableRepository, validatorService);

    }

    private NameTable createNameTable() {
        NameTable id = NameTable.create("contato", nameTableRepository);
        NameTable nameCreatede = nameTableRepository.findOne(id.getId());

        Assertions.assertThat(id.getId()).isNotNull();
        Assertions.assertThat(id.getNameTable()).isEqualTo(nameCreatede.getNameTable());
        return id;
    }

}