package jarvis.util;

import jarvis.command.AddCommand;
import jarvis.command.Command;
import jarvis.exception.JarvisException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class ParserTest {
    @Test
    void parse_shouldReturnAddCommandForToDo() {
        try {
            Command command = Parser.parse("todo Buy milk");
            assertTrue(command instanceof AddCommand);
            assertEquals("Buy milk", ((AddCommand) command).getTask().getDescription());
        } catch (JarvisException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }
}
