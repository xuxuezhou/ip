//package jarvis.command;
//
//import jarvis.util.ChatGPTService;
//import jarvis.util.Storage;
//import jarvis.util.TaskList;
//import jarvis.util.Ui;
//
///**
// * Represents a command to interact with ChatGPT.
// */
//public class GPTCommand extends Command {
//    private final ChatGPTService chatGPTService;
//    private final String prompt;
//
//    /**
//     * Constructs a GPTCommand with a given user prompt and ChatGPT service.
//     *
//     * @param prompt         The user input for ChatGPT.
//     * @param chatGPTService The ChatGPT API service.
//     */
//    public GPTCommand(String prompt, ChatGPTService chatGPTService) {
//        this.chatGPTService = chatGPTService;
//        this.prompt = prompt;
//    }
//
//    @Override
//    public String execute(TaskList tasks, Ui ui, Storage storage) {
//        String response = chatGPTService.chatWithGPT(prompt);
//        return "ChatGPT: " + response;
//    }
//}
