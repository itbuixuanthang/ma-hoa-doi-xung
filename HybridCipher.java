import java.util.Scanner;

// MÃ TÍCH SỬ DỤNG CAESAR VÀ RAILFENCE
public class HybridCipher {

    // Hàm mã hóa văn bản sử dụng hệ mã Caesar
    public static String caesarEncrypt(String plaintext, int shift) {
        StringBuilder ciphertext = new StringBuilder();
        
        for (int i = 0; i < plaintext.length(); i++) {
            char ch = plaintext.charAt(i);
            if (Character.isLetter(ch)) { // Kiểm tra xem ký tự có phải là chữ cái hay không
                char base = Character.isLowerCase(ch) ? 'a' : 'A'; // Xác định xem chữ cái là chữ cái in hoa hay in thường
                ch = (char) (((ch - base + shift) % 26) + base); // Mã hoá ký tự
            }
            ciphertext.append(ch);  // Thêm ký tự đã mã hoá vào chuỗi kết quả
            
            // System.out.println(ciphertext);
        }
        return ciphertext.toString();
    }

    // Hàm giải mã văn bản đã được mã hóa bằng hệ mã Caesar
    public static String caesarDecrypt(String ciphertext, int shift) {
        return caesarEncrypt(ciphertext, 26 - shift); // Đối với hệ mã Caesar, giải mã là việc dịch ngược lại
    }

    // Hàm mã hóa văn bản sử dụng hệ mã Rail Fence
    public static String railFenceEncrypt(String plaintext, int rails) {
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
                
                // In ra dòng hiện tại
                // System.out.println("Dòng " + (rail + 1) + ": " + railFence[rail]);

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

    // Hàm giải mã văn bản đã được mã hóa bằng hệ mã Rail Fence
    public static String railFenceDecrypt(String ciphertext, int rails) {
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

        return plaintext.toString();    }

    // Hàm mã tích kết hợp cả hai hệ mã Caesar và Rail Fence
    public static String hybridEncrypt(String plaintext, int shift, int rails) {
        // Mã hóa bằng hệ mã Caesar
        String caesarCipher = caesarEncrypt(plaintext, shift);
        // System.out.println("1");
        // Mã hóa kết quả từ hệ mã Caesar bằng hệ mã Rail Fence
        return railFenceEncrypt(caesarCipher, rails);
    }

    // Hàm giải mã văn bản đã được mã hóa bằng mã tích
    public static String hybridDecrypt(String ciphertext, int shift, int rails) {
        // Giải mã với hệ mã Rail Fence trước
        String railFenceCipher = railFenceDecrypt(ciphertext, rails);
        // System.out.println(railFenceCipher);
        // Giải mã kết quả từ hệ mã Rail Fence bằng hệ mã Caesar
        return caesarDecrypt(railFenceCipher, shift);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Nhập thông điệp và các tham số cho mã hóa
        //System.out.print("Nhập thông điệp: ");
        //String plaintext = sc.nextLine();
        String plaintext = "Hello Word";

        // System.out.print("Nhập số dịch chuyển cho hệ mã Caesar: ");
        // int shift = sc.nextInt();
        int shift = 3;

        // System.out.print("Nhập số hàng cho hệ mã Rail Fence: ");
        // int rails = sc.nextInt();
        int rails = 3;

        // Mã hóa thông điệp bằng mã tích
        String ciphertext = hybridEncrypt(plaintext, shift, rails);
        System.out.println("Mã hoá: " + ciphertext);

        // Giải mã thông điệp đã được mã hóa bằng mã tích
        String decryptedText = hybridDecrypt(ciphertext, shift, rails);
        System.out.println("Giải mã: " + decryptedText);
    }
}

