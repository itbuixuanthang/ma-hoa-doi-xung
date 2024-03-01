import java.util.Scanner;

// HỆ MÃ HOÁN VỊ
public class PermutationCipher {
    // Hàm mã hoá văn bản
    public static String encrypt(String plaintext, String permutationInput) {
        // Tách chuỗi hoán vị thành các số nguyên
        String[] permutationTokens = permutationInput.split(" ");
        int[] permutation = new int[permutationTokens.length];
        for (int i = 0; i < permutationTokens.length; i++) {
            permutation[i] = Integer.parseInt(permutationTokens[i]);
        }

        // Tính kích thước của mỗi nhóm và số lượng nhóm
        int blockSize = permutation.length;
        int numBlocks = (int) Math.ceil((double) plaintext.length() / blockSize);
        StringBuilder ciphertext = new StringBuilder();

        // Mã hoá từng nhóm ký tự
        for (int block = 0; block < numBlocks; block++) {

            // Tính số lượng ký tự cần thêm vào để đảm bảo đủ ký tự cho mỗi nhóm
            int paddingLength = blockSize - (plaintext.length() % blockSize);
            // Thêm các ký tự vào cuối của plaintext
            for (int i = 0; i < paddingLength; i++) {
                plaintext += "*"; // Thêm dấu * hoặc bất kỳ ký tự nào bạn muốn vào đây
            }

            // Xác định vị trí bắt đầu và kết thúc của nhóm ký tự
            int startIndex = block * blockSize;
            int endIndex = Math.min(startIndex + blockSize, plaintext.length());
            // Lấy nhóm ký tự từ bản rõ
            String blockText = plaintext.substring(startIndex, endIndex);
            
            // Mã hoá từng ký tự trong nhóm
            StringBuilder encryptedBlock = new StringBuilder();
            for (int i = 0; i < blockSize; i++) {
                // Tính vị trí mới của ký tự trong nhóm
                int newIndex = permutation[i] - 1;
                // Kiểm tra xem vị trí mới có hợp lệ không và thêm ký tự tương ứng vào chuỗi mã hoá
                if (newIndex < blockText.length()) {
                    char character = blockText.charAt(newIndex);
                    // Nếu gặp khoảng trống, thay thế bằng dấu *
                    if (character == ' ') {
                        character = '*';
                    }
                    encryptedBlock.append(character);
                }
            }
            //System.out.println(encryptedBlock);
            // Nối chuỗi mã hoá của nhóm vào chuỗi kết quả
            ciphertext.append(encryptedBlock);
        }
        return ciphertext.toString();
    }

    // Hàm giải mã văn bản
    public static String decrypt(String ciphertext, String permutationInput) {
        // Tách chuỗi hoán vị thành các số nguyên
        String[] permutationTokens = permutationInput.split(" ");
        int[] permutation = new int[permutationTokens.length];
        for (int i = 0; i < permutationTokens.length; i++) {
            permutation[i] = Integer.parseInt(permutationTokens[i]);
        }

        // Tính kích thước của mỗi nhóm và số lượng nhóm
        int blockSize = permutation.length;
        int numBlocks = (int) Math.ceil((double) ciphertext.length() / blockSize);
        StringBuilder plaintext = new StringBuilder();

        // Tạo mảng chứa vị trí của từng số thứ tự trong chuỗi hoán vị
        int[] indexes = new int[blockSize];
        for (int j = 0; j < blockSize; j++) {
            indexes[permutation[j] - 1] = j;
        }

        // Giải mã từng nhóm ký tự
        for (int block = 0; block < numBlocks; block++) {
            // Xác định vị trí bắt đầu và kết thúc của nhóm ký tự
            int startIndex = block * blockSize;
            int endIndex = Math.min(startIndex + blockSize, ciphertext.length());
            // Lấy nhóm ký tự từ bản mã
            String blockText = ciphertext.substring(startIndex, endIndex);
            
            // Giải mã từng ký tự trong nhóm
            StringBuilder decryptedBlock = new StringBuilder();
            for (int i = 0; i < blockSize; i++) {
                // Tìm vị trí của ký tự gốc trong nhóm đã mã hoá và thêm vào chuỗi giải mã
                int originalIndex = indexes[i];
                if (originalIndex < blockText.length()) {
                    char character = blockText.charAt(originalIndex);
                    // Nếu gặp dấu *, thay thế bằng khoảng trắng
                    if (character == '*') {
                        character = ' ';
                    }
                    decryptedBlock.append(character);
                }
            }

            // Nối chuỗi giải mã của nhóm vào chuỗi kết quả
            plaintext.append(decryptedBlock);
        }
        return plaintext.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Nhập bản rõ từ người dùng
        //System.out.print("Nhập Bản Rõ: ");
        String plaintext = "shesellseashellsbytheseashore";

        // Nhập chuỗi hoán vị từ người dùng
        //System.out.print("Nhập Vào Hoán Vị: ");
        String permutationInput = "3 5 1 6 4 2";
        scanner.close();

        // Mã hoá bản rõ và in ra bản mã
        String ciphertext = encrypt(plaintext, permutationInput);
        System.out.println("Bản Mã: " + ciphertext);

        // Giải mã bản mã và in ra bản rõ
        String decryptedText = decrypt(ciphertext, permutationInput);
        System.out.println("Bản Rõ: " + decryptedText);
    }
}
