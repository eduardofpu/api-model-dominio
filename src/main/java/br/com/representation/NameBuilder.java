package br.com.representation;

import br.com.table.NameTable;
import org.springframework.data.domain.Page;

public class NameBuilder {
    private Page<NameTable> nameTables;

    public NameBuilder getTables(Page<NameTable> nameTables) {
        this.nameTables = nameTables;
        return this;
    }

    public static NameBuilder builder() {

        return new NameBuilder();
    }

    public Page<NameTableRepresentation> build() {
        return  this.nameTables.map(nameTable -> new NameTableRepresentation(
                nameTable.getId(),
                nameTable.getNameTable()));
    }

}
