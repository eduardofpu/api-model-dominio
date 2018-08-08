package br.com.table.column;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DropColumn {
    private String nameTable;
    private String nameColumn;
}
