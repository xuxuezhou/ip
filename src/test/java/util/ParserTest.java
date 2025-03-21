package jarvis.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import jarvis.command.AddCommand;
import jarvis.command.Command;
import jarvis.exception.JarvisException;

class ParserTest {
    @Test
    void parse_shouldReturnAddCommandForToDo() {
        try {
            Command command = Parser.parseCommand("todo Buy milk");
            assertTrue(command instanceof AddCommand);
            assertEquals("Buy milk", ((AddCommand) command).getTask().getDescription());
        } catch (JarvisException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }
}
