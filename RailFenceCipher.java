import java.util.Scanner;

// HỆ MÃ RAIL FENCE
public class RailFenceCipher {

    // Hàm mã hoá văn bản rõ sử dụng hệ mã Rail Fence
    public static String encrypt(String plaintext, int rails) {
        if (plaintext == null || plaintext.isEmpty() || rails < 2) {
            return "";
        }

        StringBuilder[] railFence = new StringBuilder[rails]; // Mảng các StringBuilder để lưu trữ từng dòng
        for (int i = 0; i < rails; i++) {
            railFence[i] = new StringBuilder(); // Khởi tạo mỗi dòng là một StringBuilder
        }

        int rail = 0;
        //boolean goingDown = false;
        for (char ch : plaintext.toCharArray()) {
            // Chỉ xét và xử lý các ký tự là chữ cái
            if (Character.isLetter(ch)) {
                railFence[rail].append(ch); // Thêm ký tự vào dòng hiện tại

                // Nếu đến dòng cuối, gán lại vòng đầu tiên
                if (rail == rails - 1) {
                    rail = 0;
                } else {
                    rail++;
                }
            }
        }

        // Ghép từng chữ cái của từng dòng vào văn bản mã theo đúng thứ tự
        StringBuilder ciphertext = new StringBuilder();
        for (StringBuilder sb : railFence) {
            ciphertext.append(sb);
        }

        return ciphertext.toString();
    }

    // Hàm giải mã văn bản mã sử dụng hệ mã Rail Fence
    public static String decrypt(String ciphertext, int rails) {
        if (ciphertext == null || ciphertext.isEmpty() || rails < 2) {
            return "";
        }

        int totalLength = ciphertext.length();
        int fullSegments = totalLength / rails; // Số phần tách đầy đủ trên mỗi hàng
        int remainder = totalLength % rails; // Số ký tự dư ra

        int[] lengths = new int[rails]; // Mảng lưu độ dài của từng dòng
        // Xác định độ dài của từng dòng
        for (int i = 0; i < rails; i++) {
            lengths[i] = (i < remainder) ? fullSegments + 1 : fullSegments;
        }

        // Tạo mảng để lưu trữ các dòng
        String[] railFence = new String[rails];
        int index = 0;
        // Tách văn bản mã thành các dòng
        for (int i = 0; i < rails; i++) {
            railFence[i] = ciphertext.substring(index, index + lengths[i]);
            index += lengths[i];
            // System.out.println(railFence[i]);
        }

        StringBuilder plaintext = new StringBuilder();
        int[] indices = new int[rails]; // Mảng lưu chỉ số hiện tại của mỗi dòng

        // Duyệt qua các ký tự trong mỗi dòng theo thứ tự từng dòng
        while (plaintext.length() < totalLength) {
            for (int i = 0; i < rails; i++) {
                // Nếu dòng hiện tại có chỉ số nhỏ hơn độ dài của nó
                if (indices[i] < lengths[i]) {
                    // Thêm ký tự vào văn bản rõ từ dòng hiện tại
                    plaintext.append(railFence[i].charAt(indices[i]));
                    // Tăng chỉ số cho dòng hiện tại
                    indices[i]++;
            }
        }
        // In ra văn bản rõ ở mỗi bước
        // System.out.println("\n" + plaintext);
        }

        return plaintext.toString();
    }

    public static void main(String[] args) {
        String plaintext = "hello world"; // Văn bản rõ
        // int rails = 3; // Số hàng
        Scanner sc = new Scanner(System.in);

        // System.out.print("Nhập thông điệp: ");
        // String plaintext = sc.nextLine();

        System.out.print("Nhập số hàng: ");
        int rails = sc.nextInt();

        // Mã hoá
        String ciphertext = encrypt(plaintext, rails);
        System.out.println("Mã hoá: " + ciphertext);

        // Giải mã
        String decryptedText = decrypt(ciphertext, rails);
        System.out.println("Giải mã: " + decryptedText);
    }
}