package br.com.table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode
public class UpdateTableReq {

    private String nameTable;
    private String attribute;
    private Long id;
    private String parameter;

}
