package br.com.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class CustomErrorType {
    @Getter
    private String errorMessage;

}
