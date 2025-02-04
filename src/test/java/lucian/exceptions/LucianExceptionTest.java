package lucian.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LucianExceptionTest {

    @Test
    public void testExceptionMessage() {
        Exception exception = assertThrows(LucianException.class, () -> {
            throw new LucianException("Test error message");
        });

        assertEquals("Test error message", exception.getMessage(), "Exception message should match");
    }
}
