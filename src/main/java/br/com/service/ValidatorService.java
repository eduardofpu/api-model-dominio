package br.com.service;

import br.com.table.NameTable;

public interface ValidatorService {
    void verifyINameTableIdExists(Long id);
    void verifyIfIdAddCollumnExists(Long id);
    String verifyIfNameAlredyExists(String nameTable);
    String verifyNameDoesNotExist(String nameTable);
    String verifyNameColumn(String nameTable, NameTable id, String nameColumn);
}
