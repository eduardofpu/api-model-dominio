package br.com.representation;

import br.com.table.NameTable;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AddColumnRepresentation {
    private Long id;
    private String nameColumn;
    private String dataType;
    private NameTable idTable;
}
