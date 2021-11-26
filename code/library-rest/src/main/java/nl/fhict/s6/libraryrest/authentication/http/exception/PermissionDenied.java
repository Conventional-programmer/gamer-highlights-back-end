package nl.fhict.s6.libraryrest.authentication.http.exception;

public class PermissionDenied extends Exception{
    public PermissionDenied(String message) {
        super(message);
    }
}
