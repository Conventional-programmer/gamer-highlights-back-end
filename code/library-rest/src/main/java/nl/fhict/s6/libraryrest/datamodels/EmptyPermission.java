package nl.fhict.s6.libraryrest.datamodels;

import java.util.ArrayList;
import java.util.List;

public class EmptyPermission extends BasePermission {
    public EmptyPermission() {
        super(-1L, "", new ArrayList<>());
    }
}
