package com.example.Youx.services.exceptions;

public class ResourceUnprocessableException extends RuntimeException {
    public ResourceUnprocessableException(Object cpf) {
        super("CPF já cadastrado: " + cpf);
    }

}
