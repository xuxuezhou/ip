# Jarvis User Guide
## Introduction
Jarvis is a simple personal assistant chatbot built with Java and JavaFX.  
It can help you:
- Manage tasks (todos, deadlines, events)
- Provide quick responses to certain questions (e.g., about astronomy, physics)
- Interact through a friendly GUI

## Getting Started
### Running Jarvis
1. Ensure you have Java (JDK-17) installed on your system.
2. Run the Jarvis application via the provided JAR file.
3. Interact with Jarvis through the command input field.

## Available Commands
Jarvis supports a variety of commands for managing tasks and retrieving predefined responses.
### Task Management

#### Adding a To-Do
- Command format: `todo <description>`
- Example: `todo Read book`
- Expected outcome:
```angular2html
Added: [T][ ] Read book
```
#### Adding a Deadline
- Command format: `deadline <description> /by <data/time>`
- Example: `deadline Finish report /by Monday`
- Expected outcome:
```angular2html
Added: [D][ ] Finish report (by: Monday)
```
#### Adding an Event
- Command format: `event <description> /from <start> /to <end>`
- Example: `event Team meeting /from 2pm /to 4pm`
- Expected outcome:
```angular2html
Added: [E][ ] Team meeting (from: 2pm to: 4pm)
```

#### Listing All Tasks
- Command format: `list`
- Expected outcome: Displays all tasks in the list with their status.

#### Marking a Task as Done
- Command format: `mark <task index>`
- Example: `mark 2`
- Expected outcome:
```angular2html
Task marked as done: [T][X] Read book
```
#### Unmarking a Task
- Command format: `unmark <task index>`
- Example: `unmark 2`
- Expected outcome:
```angular2html
Task unmarked: [T][ ] Read book
```
#### Deleting a Task
- Command format: `delete <task index>`
- Example: `delete 1`
- Expected outcome:
```angular2html
Removed: [T][ ] Read book
```
### Predefined Responses
Jarvis can respond to certain hardcoded questions.
#### General Conversations
- Example: `hello` -> `hi!`
### Exit the Application
- Command: `bye`
- Expected outcome: Terminates the application directly.

### Error Handling
If you enter an unreconized command, Jarvis will respond with:
```angular2html
I'm sorry, but I don't recognize this command.
```
Ensure your command format follows the correct structure.

## Run the Jar file
- Command: `java -jar Jarvis.jar`
## Notes
- Commands are case-sensitive, but parameters are case-insensitive.
- Ensure correct spacing and command structure to avoid errors.


