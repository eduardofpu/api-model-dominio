package br.com.service;

public interface ServiceValidator {
    void verifyINameTableIdExists(Long id);
    void verifyIfIdAddCollumnExists(Long id);
}
