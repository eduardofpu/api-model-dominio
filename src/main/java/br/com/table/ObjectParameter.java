package br.com.table;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class ObjectParameter {

    private String nameTable;
    private List<Object> parameters;
}
