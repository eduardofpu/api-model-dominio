package br.com.service;

public interface ValidatorService {
    void verifyINameTableIdExists(Long id);
    void verifyIfIdAddCollumnExists(Long id);
}
