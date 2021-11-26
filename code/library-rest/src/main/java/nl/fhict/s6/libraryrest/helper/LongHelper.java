package nl.fhict.s6.libraryrest.helper;

public class LongHelper {
    public Long parseLongOrDefaultValue(String number,Long defaultValue)
    {
        try {
            return Long.parseLong(number);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}
