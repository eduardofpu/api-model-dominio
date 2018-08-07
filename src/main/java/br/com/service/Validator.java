package br.com.service;

public interface Validator {
    void verifyINameTableIdExists(Long id);
    void verifyIfIdAddCollumnExists(Long id);
}
