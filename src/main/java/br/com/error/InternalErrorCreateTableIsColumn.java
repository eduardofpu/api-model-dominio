package br.com.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalErrorCreateTableIsColumn extends RuntimeException{
    public InternalErrorCreateTableIsColumn(String messege) {
        super(messege);
    }
}
