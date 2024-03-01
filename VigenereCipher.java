import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

// HỆ MÃ VIGENRE
public class VigenereCipher {
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; // Bảng chữ cái tiếng Anh
    private static final Map<Character, String> VIGENERE_TABLE = createVigenereTable(); // Bảng mã Vigenere

    // Hàm tạo bảng mã Vigenere
    private static Map<Character, String> createVigenereTable() {
        Map<Character, String> vigenereTable = new HashMap<>(); // Khởi tạo một HashMap để lưu trữ bảng mã Vigenere
        for (int i = 0; i < ALPHABET.length(); i++) { // Duyệt qua từng ký tự trong bảng chữ cái
            char keyChar = ALPHABET.charAt(i); // Lấy ký tự khóa tương ứng với vị trí hiện tại
            StringBuilder row = new StringBuilder(); // Tạo một StringBuilder để xây dựng hàng trong bảng mã Vigenere
            for (int j = 0; j < ALPHABET.length(); j++) { // Duyệt qua từng ký tự trong bảng chữ cái
                int shift = (i + j) % ALPHABET.length(); // Tính toán vị trí của ký tự trong hàng Vigenere bằng cách dịch chuyển
                row.append(ALPHABET.charAt(shift)); // Thêm ký tự đã dịch chuyển vào hàng Vigenere
            }
            vigenereTable.put(keyChar, row.toString()); // Đưa hàng Vigenere vào bảng mã Vigenere với khóa tương ứng
        }
        return vigenereTable; // Trả về bảng mã Vigenere đã tạo
    }

    // Hàm mã hoá Vigenere Cipher
    public static String encrypt(String plaintext, String key) {
        StringBuilder ciphertext = new StringBuilder(); // StringBuilder để xây dựng chuỗi mã hoá
        plaintext = plaintext.toUpperCase(); // Chuyển chuỗi bản rõ thành chữ hoa
        key = key.toUpperCase(); // Chuyển chuỗi khóa thành chữ hoa

        for (int i = 0, j = 0; i < plaintext.length(); i++) { // Duyệt qua từng ký tự trong chuỗi bản rõ
            char ch = plaintext.charAt(i); // Lấy ký tự hiện tại
            if (Character.isLetter(ch)) { // Kiểm tra xem ký tự có phải là chữ cái không
                char keyChar = key.charAt(j % key.length()); // Lấy ký tự khóa tương ứng với vị trí hiện tại (lặp lại nếu cần)
                String vigenereRow = VIGENERE_TABLE.get(keyChar); // Lấy hàng Vigenere tương ứng với ký tự khóa
                int index = ALPHABET.indexOf(ch); // Lấy chỉ số của ký tự trong bảng chữ cái
                ciphertext.append(vigenereRow.charAt(index)); // Thêm ký tự đã mã hoá vào chuỗi mã hoá
                j++; // Tăng chỉ số của ký tự khóa
            } else {
                ciphertext.append(ch); // Nếu không phải là chữ cái, giữ nguyên ký tự
            }
        }
        return ciphertext.toString(); // Trả về chuỗi mã hoá
    }

    // Hàm giải mã Vigenere Cipher
    public static String decrypt(String ciphertext, String key) {
        StringBuilder plaintext = new StringBuilder(); // StringBuilder để xây dựng chuỗi bản rõ
        ciphertext = ciphertext.toUpperCase(); // Chuyển chuỗi mã hoá thành chữ hoa
        key = key.toUpperCase(); // Chuyển chuỗi khóa thành chữ hoa

        for (int i = 0, j = 0; i < ciphertext.length(); i++) { // Duyệt qua từng ký tự trong chuỗi mã hoá
            char ch = ciphertext.charAt(i); // Lấy ký tự hiện tại
            if (Character.isLetter(ch)) { // Kiểm tra xem ký tự có phải là chữ cái không
                char keyChar = key.charAt(j % key.length()); // Lấy ký tự khóa tương ứng với vị trí hiện tại (lặp lại nếu cần)
                String vigenereRow = VIGENERE_TABLE.get(keyChar); // Lấy hàng Vigenere tương ứng với ký tự khóa
                int index = vigenereRow.indexOf(ch); // Lấy chỉ số của ký tự trong hàng Vigenere
                plaintext.append(ALPHABET.charAt(index)); // Thêm ký tự đã giải mã vào chuỗi bản rõ
                j++; // Tăng chỉ số của ký tự khóa
            } else {
                plaintext.append(ch); // Nếu không phải là chữ cái, giữ nguyên ký tự
            }
        }
        return plaintext.toString(); // Trả về chuỗi bản rõ
    }

    // Hàm tạo khóa ngẫu nhiên có độ dài bằng độ dài của mã rõ
    public static String generateRandomKey(int length) {
        StringBuilder key = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            key.append(ALPHABET.charAt(random.nextInt(ALPHABET.length())));
        }
        return key.toString();
    }

    // Hàm main để kiểm tra
    public static void main(String[] args) {
        // String plaintext = "hello"; // Chuỗi bản rõ
        String plaintext = "wearediscoveredsaveyourself"; // Chuỗi bản rõ
        // String key = "key"; // Khóa

        Scanner sc = new Scanner(System.in);

        // System.out.print("Nhập thông điệp: ");
        // String plaintext = sc.nextLine();

        System.out.print("Nhập khóa: ");
        String key = sc.nextLine();

        // Tạo khóa ngẫu nhiên có độ dài bằng độ dài của thông điệp
        // String key = generateRandomKey(plaintext.length());
        // System.out.println("Khóa ngẫu nhiên: " + key);

        sc.close();

        String encryptedText = "BLWPOODEMJFBTZNVJNJQOJORGGU";
        // String encryptedText = encrypt(plaintext, key); // Mã hoá chuỗi bản rõ
        // System.out.println("\nMã hoá: " + encryptedText); // In ra chuỗi mã hoá

        String decryptedText = decrypt(encryptedText, key); // Giải mã chuỗi mã hoá
        System.out.println("Giải mã: " + decryptedText); // In ra chuỗi bản rõ đã giải mã
    }
}
