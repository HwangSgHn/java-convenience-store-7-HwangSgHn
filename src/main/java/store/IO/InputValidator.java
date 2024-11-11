package store.IO;

public class InputValidator {
    public static boolean format(String input) {
        return input.matches("\\[[가-힣]+-\\d+](,\\[[가-힣]+-\\d+])*");
    }
}
