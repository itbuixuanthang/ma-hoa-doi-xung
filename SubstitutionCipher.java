import java.util.HashMap;
import java.util.Map;

// THAY THẾ ĐƠN BẢNG
public class SubstitutionCipher {
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz"; // Bảng chữ cái tiếng Anh
    private static final String KEY = "DKVQFIBJWPESCXHTMYAUOLRGZN"; // Bảng chữ cái khóa

    // Hàm tạo map ánh xạ từ chữ cái gốc đến chữ cái mã hoá
    private static Map<Character, Character> createSubstitutionMap() {
        Map<Character, Character> substitutionMap = new HashMap<>();
        for (int i = 0; i < ALPHABET.length(); i++) {
            char originalChar = ALPHABET.charAt(i);
            char substitutedChar = KEY.charAt(i);
            substitutionMap.put(originalChar, substitutedChar);
        }
        return substitutionMap;
    }

    // Hàm mã hoá bằng phương pháp thay thế đơn bảng
    public static String encrypt(String plaintext) {
        StringBuilder result = new StringBuilder();

        Map<Character, Character> substitutionMap = createSubstitutionMap();

        for (int i = 0; i < plaintext.length(); i++) {
            char ch = plaintext.charAt(i);
            char encryptedChar = substitutionMap.getOrDefault(Character.toLowerCase(ch), ch); // Lấy ký tự đã mã hoá hoặc giữ nguyên nếu không phải là chữ cái
            result.append(Character.isUpperCase(ch) ? Character.toUpperCase(encryptedChar) : encryptedChar); // Giữ nguyên kiểu chữ (hoa/thường)
        }

        return result.toString();
    }

    // Hàm giải mã bằng phương pháp thay thế đơn bảng
    public static String decrypt(String ciphertext) {
        StringBuilder result = new StringBuilder();

        Map<Character, Character> substitutionMap = createSubstitutionMap();

        for (int i = 0; i < ciphertext.length(); i++) {
            char ch = ciphertext.charAt(i);
            char decryptedChar = findOriginalChar(substitutionMap, ch); // Tìm ký tự gốc tương ứng với ký tự đã mã hoá
            result.append(decryptedChar); // Thêm ký tự đã giải mã vào chuỗi kết quả
        }

        return result.toString();
    }

    // Hàm tìm ký tự gốc tương ứng với ký tự đã mã hoá
    private static char findOriginalChar(Map<Character, Character> substitutionMap, char encryptedChar) {
        for (Map.Entry<Character, Character> entry : substitutionMap.entrySet()) {
            if (entry.getValue() == encryptedChar) {
                return entry.getKey();
            }
        }
        return encryptedChar; // Trả về ký tự gốc nếu không tìm thấy
    }


    public static void main(String[] args) {
        String plaintext = "ifwewishtoreplaceletters"; // Chuỗi thông điệp ban đầu

        // Mã hoá
        String encryptedText = encrypt(plaintext); // Gọi hàm mã hoá với thông điệp ban đầu
        System.out.println("Mã hoá: " + encryptedText); // In ra thông điệp đã được mã hoá

        // Giải mã
        String decryptedText = decrypt(encryptedText); // Gọi hàm giải mã với thông điệp đã được mã hoá
        System.out.println("Giải mã: " + decryptedText); // In ra thông điệp đã được giải mã
    }
}
