package zxh.demo.tw.assignment.conference.adapter.exception;

public class IllegalTalkDescriptionException extends RuntimeException {
    public IllegalTalkDescriptionException(String message) {
        super(String.format("Illegal Talk Description: %s", message));
    }
}
