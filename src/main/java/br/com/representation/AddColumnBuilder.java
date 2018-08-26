package br.com.representation;

import br.com.table.column.AddColumn;
import org.springframework.data.domain.Page;

public class AddColumnBuilder {
    private Page<AddColumn> columns;

    public AddColumnBuilder getColumn(Page<AddColumn> columns) {
        this.columns = columns;
        return this;
    }

    public static AddColumnBuilder builder() {

        return new AddColumnBuilder();
    }

    public Page<AddColumnRepresentation> build() {
        return  this.columns.map(column -> new AddColumnRepresentation(
                column.getId(),
                column.getNameColumn(),
                column.getDataType(),
                column.getIdTable()));
    }
}
