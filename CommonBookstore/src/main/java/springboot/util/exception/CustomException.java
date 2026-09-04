package springboot.util.exception;


import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {

    private final MessageException messageException;

    public CustomException(MessageException messageException) {
        super(messageException.getCode());
        this.messageException = messageException;
    }

    public MessageException getErrorCode() {
        return messageException;
    }

}
