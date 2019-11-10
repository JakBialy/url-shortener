package url.shortener.makeitshort.engines.utilities;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class Base64 {
    private static final List<Character> BASE_64_URL_CHARS = Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
            'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd',
            'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-', '_');
    private static final int SIZE_OF_ARRAY = BASE_64_URL_CHARS.size();

    /**
     * Converts positive decimal value into base64url value
     * @param number decimal value to convert
     * @return String with base64 value
     */
    public String decimalToBase64URL(int number) {
        StringBuilder stringBuilder = new StringBuilder();
        while (number >= 64) {
            number = divedAndAppendIntSb(stringBuilder, number);
        }

        return stringBuilder.append(BASE_64_URL_CHARS.get(number)).toString();
    }

    private int divedAndAppendIntSb(StringBuilder stringBuilder, int value) {
        int divedValue = value / SIZE_OF_ARRAY;
        if (divedValue >= 64) {
            divedValue = divedAndAppendIntSb(stringBuilder, divedValue);
        }

        stringBuilder.append(BASE_64_URL_CHARS.get(divedValue));
        return value % SIZE_OF_ARRAY;
    }

}