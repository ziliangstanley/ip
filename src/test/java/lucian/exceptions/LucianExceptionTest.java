package lucian.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class LucianExceptionTest {

    @Test
    public void testExceptionMessage() {
        Exception exception = assertThrows(LucianException.class, () -> {
            throw new LucianException("Test error message");
        });

        assertEquals("Test error message", exception.getMessage(), "Exception message should match");
    }
}
