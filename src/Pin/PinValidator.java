package Pin;

public class PinValidator {
    private final String correctPin;

    public PinValidator(String correctPin) {
        this.correctPin = correctPin;
    }

    public boolean validate(String pin) {
        return correctPin.equals(pin);
    }
}

