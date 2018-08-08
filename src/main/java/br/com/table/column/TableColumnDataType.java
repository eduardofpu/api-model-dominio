package br.com.table.column;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NameColumDataTypeReq {

    private String nameTable;
    private String nameColumn;
    private String dataType;
}
