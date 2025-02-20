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
Got it. I've added this task:
[T][ ] Read book
Now you have X tasks in the list.
```
#### Adding a Deadline
- Command format: `deadline <description> /by <data/time>`
- Example: `deadline Finish report /by Monday`
- Expected outcome:
```angular2html
Got it. I've added this task:
[D][ ] Finish report (by: Monday)
Now you have X tasks in the list.
```
#### Adding an Event
- Command format: `event <description> /from <start> /to <end>`
- Example: `event Team meeting /from 2pm /to 4pm`
- Expected outcome:
```angular2html
Got it. I've added this task:
[E][ ] Team meeting (from: 2pm to: 4pm)
Now you have X tasks in the list.
```

#### Listing All Tasks
- Command format: `list`
- Expected outcome: Displays all tasks in the list with their status.

#### Marking a Task as Done
- Command format: `mark <task index>`
- Example: `mark 2`
- Expected outcome:
```angular2html
Nice! I've marked this task as done:
[T][X] Read book
```
#### Unmarking a Task
- Command format: `unmark <task index>`
- Example: `unmark 2`
- Expected outcome:
```angular2html
OK, I've marked this task as not done yet:
[T][ ] Read book
```
#### Deleting a Task
- Command format: `delete <task index>`
- Example: `delete 1`
- Expected outcome:
```angular2html
Noted. I've removed this task:
[T][ ] Read book
Now you have X tasks in the list.
```
### Predefined Responses
Jarvis can respond to certain hardcoded questions.
#### General Conversations
- Example: `hello` -> `hi!`
- Example: `what's your favorite food?` -> `Matcha!`
- Example: `who is your best friend?` -> `It's you! Xiongmao.`
- Example: `what's your research interest?` -> `I'm interested in magnetic fields!`

#### Physics & Astronomy Questions
- Example: `could you explain maxwell's equation for me?` -> `Sure. Maxwell's equations are four fundamental equations that describe classical electromagnetism.
They explain how electric and magnetic fields interact with each other and with charges and currents.
These equations unify electricity, magnetism, and optics into a single theory.`
- Example: `what is the largest planet in our solar system?` -> `Jupiter is the largest planet in our solar system.`

### Exit the Application
- Command: `bye`
- Expected outcome: Terminates the application directly.

### Error Handling
If you enter an unreconized command, Jarvis will respond with:
```angular2html
I'm sorry, but I don't recognize this command.
```
Ensure your command format follows the correct structure.

## Talk to AI!
Jarvis supports the feature to GPT.

## Notes
- Commands are case-sensitive, but parameters are case-insensitive.
- Ensure correct spacing and command structure to avoid errors.


