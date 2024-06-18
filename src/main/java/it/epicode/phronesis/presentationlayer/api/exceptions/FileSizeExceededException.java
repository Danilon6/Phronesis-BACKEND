package it.epicode.phronesis.presentationlayer.api.exceptions;

public class FileSizeExceededException extends RuntimeException {
    public FileSizeExceededException() {
        super("File size exceeds the maximum allowed size");
    }
}
