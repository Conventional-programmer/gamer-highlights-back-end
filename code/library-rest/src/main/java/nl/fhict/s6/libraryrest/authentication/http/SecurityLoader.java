package nl.fhict.s6.libraryrest.authentication.http;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@ComponentScan
@Import({LoadUserFromHeaderFilter.class})
public class SecurityLoader {
}
