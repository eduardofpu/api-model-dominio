package br.com.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode
//@Entity
public class Contato {

   // @Id
    //@GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;

    public Contato(Long id) {
        this.id = id;
    }

    public Contato(String name) {
        this.name = name;
    }
}
