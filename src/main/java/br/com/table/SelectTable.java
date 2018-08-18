package br.com.table;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class SelectTable {

    private String nameTable;
    private Object parameters;
}
