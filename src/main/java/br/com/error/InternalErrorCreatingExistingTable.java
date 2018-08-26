package br.com.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalErrorCreatingExistingTable extends RuntimeException{
    public InternalErrorCreatingExistingTable(String messege) {
        super(messege);
    }
}
