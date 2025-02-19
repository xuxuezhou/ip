// JarvisException.java
package jarvis.exception;

/**
 * Custom exception class for Jarvis chatbot errors.
 */
public class JarvisException extends Exception {
    public JarvisException(String message) {
        super(message);
    }
}