// HỆ MÃ CAESAR
public class CaesarCipher {
    // Hàm mã hoá Caesar Cipher
    public static String encrypt(String plaintext, int shift) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < plaintext.length(); i++) {
            char ch = plaintext.charAt(i);
            if (Character.isLetter(ch)) { // Kiểm tra xem ký tự có phải là chữ cái hay không
                char base = Character.isUpperCase(ch) ? 'A' : 'a'; // Xác định xem chữ cái là chữ cái in hoa hay in thường
                char shiftedChar = (char) (((ch - base + shift) % 26) + base); // Mã hoá ký tự
                result.append(shiftedChar); // Thêm ký tự đã mã hoá vào chuỗi kết quả
            } else {
                result.append(ch); // Nếu không phải là chữ cái, giữ nguyên ký tự
            }
        }

        return result.toString(); // Trả về chuỗi kết quả sau khi đã mã hoá
    }

    // Hàm giải mã Caesar Cipher
    public static String decrypt(String ciphertext, int shift) {
        return encrypt(ciphertext, 26 - shift); // Để giải mã, ta di chuyển ngược lại (tức là dịch phải thêm 26 - shift)
    }

    public static void main(String[] args) {
        String plaintext = "See U Again"; // Chuỗi thông điệp ban đầu
        int shift = 4; // Số lần di chuyển (shift) để mã hoá

        // Mã hoá
        String encryptedText = encrypt(plaintext, shift); // Gọi hàm mã hoá với thông điệp và số lần di chuyển đã cho
        System.out.println("Mã hoá: " + encryptedText); // In ra thông điệp đã được mã hoá

        // Giải mã
        String decryptedText = decrypt(encryptedText, shift); // Gọi hàm giải mã với thông điệp đã được mã hoá và số lần di chuyển đã cho
        System.out.println("Giải mã: " + decryptedText); // In ra thông điệp đã được giải mã
    }
}
