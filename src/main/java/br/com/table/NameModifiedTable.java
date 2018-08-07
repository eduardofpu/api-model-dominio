package br.com.table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NameModifiedTable {

    private Long id;

    private String nameCurrent;

    private String nameModified;
}
