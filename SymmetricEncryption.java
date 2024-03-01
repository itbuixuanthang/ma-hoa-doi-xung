import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

// MÃ HOÁ ĐỐI XỨNG
public class SymmetricEncryption{
    public static void main(String[] args) throws Exception {
        // Chuỗi văn bản gốc cần được mã hoá
        String plainText = "This is the message to be encrypted";

        // Khóa và vectơ khởi tạo
        String key = "0123456789abcdef"; // 16-byte key
        String iv = "1234567890abhdef"; // 16-byte initialization vector

        // Mã hoá
        byte[] encrypted = encrypt(plainText, key, iv);

        // Giải mã
        String decrypted = decrypt(encrypted, key, iv);

        // In ra kết quả
        System.out.println("Bản mẫu: " + plainText);
        System.out.println("Mã hoá: " + Base64.getEncoder().encodeToString(encrypted));
        System.out.println("Giải mã: " + decrypted);
    }

    // Hàm thực hiện mã hoá văn bản
    public static byte[] encrypt(String plainText, String key, String iv) throws Exception {
        // Khởi tạo đối tượng Cipher với thuật toán AES/CBC/PKCS5Padding
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

        // Tạo một đối tượng SecretKeySpec từ khóa
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

        // Tạo một đối tượng IvParameterSpec từ vectơ khởi tạo
        IvParameterSpec ivSpec = new IvParameterSpec(iv.getBytes("UTF-8"));

        // Khởi tạo Cipher để mã hoá với khóa và IV đã cho
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);

        // Mã hoá văn bản thành một mảng byte và trả về
        return cipher.doFinal(plainText.getBytes("UTF-8"));
    }

    // Hàm thực hiện giải mã văn bản đã được mã hoá
    public static String decrypt(byte[] cipherText, String key, String iv) throws Exception {
        // Khởi tạo đối tượng Cipher với thuật toán AES/CBC/PKCS5Padding
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

        // Tạo một đối tượng SecretKeySpec từ khóa
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

        // Tạo một đối tượng IvParameterSpec từ vectơ khởi tạo
        IvParameterSpec ivSpec = new IvParameterSpec(iv.getBytes("UTF-8"));

        // Khởi tạo Cipher để giải mã với khóa và IV đã cho
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);

        // Giải mã mảng byte thành văn bản và trả về
        byte[] decryptedBytes = cipher.doFinal(cipherText);
        return new String(decryptedBytes);
    }
}


