package br.com.table.column;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AlterDataTypeIsColumn {

    private Long id;
    private String nameTable;
    private String nameColumn;
    private String dataType;
}
