package jarvis.util;

import jarvis.command.Command;
import jarvis.command.AddCommand;
import jarvis.task.ToDo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ParserTest {
    @Test
    void parse_shouldReturnAddCommandForToDo() {
        Command command = Parser.parse("todo Buy milk");
        assertTrue(command instanceof AddCommand);
        assertEquals("Buy milk", ((AddCommand) command).getTask().getDescription());
    }
}
