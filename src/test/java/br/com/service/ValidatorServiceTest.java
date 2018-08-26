package br.com.service;

import br.com.AbstractTest;
import br.com.error.InternalErrorCreateTableIsColumn;
import br.com.error.ResourceNotFoundException;
import org.junit.Test;

import java.sql.SQLException;
public class ValidatorServiceTest extends AbstractTest {
    private Long id = 1L;


    @Test
    public void verifyINameTableIdExists() throws SQLException {
        exception.expect(ResourceNotFoundException.class);
        exception.expectMessage("Name not found for ID: " + id);
        validatorService.verifyINameTableIdExists(id);
    }

    @Test(expected = RuntimeException.class)
    public void verifyIfIdAddCollumnExists() {
        validatorService.verifyIfIdAddCollumnExists(id);
    }

    @Test
    public void verifyNameDoesNotExist() {
        exception.expect(InternalErrorCreateTableIsColumn.class);
        validatorService.verifyNameDoesNotExist(null);
    }
}