package nl.fhict.s6.libraryrest.datamodels;

import java.util.List;

public interface Permission {
    Long getUserId();
    String getSubject();
    List<String> getRoles();
}
