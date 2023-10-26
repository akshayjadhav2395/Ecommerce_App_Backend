package com.myEcom.Exceptions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message)
    {
        super(message);
    }

    public ResourceNotFoundException()
    {
        super("Resource with given userId not found");
    }
}
