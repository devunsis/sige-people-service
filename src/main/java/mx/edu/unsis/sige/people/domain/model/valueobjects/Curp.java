package mx.edu.unsis.sige.people.domain.model.valueobjects;

import mx.edu.unsis.sige.people.domain.exception.InvalidCurpException;

import java.util.Objects;
import java.util.regex.Pattern;

public class Curp {

    private static final Pattern CURP_PATTERN = Pattern.compile(
            "^[A-Z]{1}[AEIOU]{1}[A-Z]{2}[0-9]{2}(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01])[HM]{1}(AS|BC|BS|CC|CL|CM|CS|CH|DF|DG|GT|GR|HG|JC|MC|MN|MS|NT|NL|OC|PL|QT|QR|SP|SL|SR|TC|TS|TL|VZ|YN|ZS|NE)[B-DF-HJ-NP-TV-Z]{3}[0-9A-Z]{1}[0-9]{1}$");

    private final String value;

    public Curp(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new InvalidCurpException("La CURP no puede estar vacía");
        }
        String normalized = value.trim().toUpperCase();
        if (!CURP_PATTERN.matcher(normalized).matches()) {
            throw new InvalidCurpException("El formato de la CURP es inválido: " + value);
        }
        this.value = normalized;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Curp curp = (Curp) o;
        return Objects.equals(value, curp.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
