import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

public class Main {

    public static void main(String[] args) {
        String studentId = "12345"; // Ganti dengan ID mahasiswa yang valid
        String apiUrl = "https://akademik.stmik-im.ac.id/api/login/" + studentId;

        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try {
            // Membuat objek URL dari alamat API
            URL url = new URL(apiUrl);
            // Membuka koneksi HTTP
            connection = (HttpURLConnection) url.openConnection();
            // Menetapkan metode request
            connection.setRequestMethod("GET");

            // Menyertakan kredensial otentikasi (contoh: otentikasi dasar)
            String username = "362201008"; // Ganti dengan username Anda
            String password = "13102001"; // Ganti dengan password Anda
            String authString = username + ":" + password;
            String encodedAuthString = Base64.getEncoder().encodeToString(authString.getBytes());
            connection.setRequestProperty("Authorization", "Basic " + encodedAuthString);

            // Membaca respon dari koneksi
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            // Menampilkan respon
            System.out.println("Data KRS:");
            System.out.println(response.toString());

        } catch (IOException e) {
            // Menampilkan pesan kesalahan jika terjadi IOException
            e.printStackTrace();
        } finally {
            // Menutup sumber daya
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
