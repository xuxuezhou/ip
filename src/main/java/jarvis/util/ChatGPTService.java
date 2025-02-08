package jarvis.util;

import com.theokanning.openai.service.OpenAiService;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import com.theokanning.openai.completion.chat.ChatMessage;

import java.util.Collections;

/**
 * Handles communication with OpenAI's ChatGPT API.
 */
public class ChatGPTService {
    private final OpenAiService openAiService;

    /**
     * Constructs a ChatGPTService instance with the API key.
     */
    public ChatGPTService() {
        String apiKey = System.getenv("OPENAI_API_KEY");
        if (apiKey == null || apiKey.isEmpty()) {
            throw new IllegalStateException("Missing OpenAI API Key. Set OPENAI_API_KEY as an environment variable.");
        }
        this.openAiService = new OpenAiService(apiKey);
    }

    /**
     * Sends a message to ChatGPT and retrieves the response.
     *
     * @param message The user input message.
     * @return The ChatGPT response.
     */
    public String chatWithGPT(String message) {
        ChatMessage userMessage = new ChatMessage("user", message);
        ChatCompletionRequest request = ChatCompletionRequest.builder()
                .model("gpt-4") // 或 "gpt-3.5-turbo"
                .messages(Collections.singletonList(userMessage))
                .maxTokens(100)
                .temperature(0.7)
                .build();

        ChatCompletionResult result = openAiService.createChatCompletion(request);
        return result.getChoices().isEmpty() ? "No response from GPT." : result.getChoices().get(0).getMessage().getContent();
    }
}
