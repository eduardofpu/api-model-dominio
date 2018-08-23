package br.com.service;

import br.com.AbstractTest;
import org.junit.Test;

public class ValidatorServiceTest extends AbstractTest{
    private Long id = 1L;


    @Test
    public void verifyINameTableIdExists(){
        exception.expect(RuntimeException.class);
        validatorService.verifyINameTableIdExists(id);
    }

    @Test(expected = RuntimeException.class)
    public void verifyIfIdAddCollumnExists(){
        validatorService.verifyIfIdAddCollumnExists(id);
    }

}