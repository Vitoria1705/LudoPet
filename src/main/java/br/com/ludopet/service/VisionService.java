package br.com.ludopet.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.net.http.*;
import java.util.*;

@Service
public class VisionService {

    private static final String API_KEY = "692e1c50-3fe7-4583-853d-2b3e9b54405a";

    public List<String> analisarImagem(MultipartFile foto) {
        List<String> labels = new ArrayList<>();

        try {
            // monta multipart manualmente
            String boundary = "----Boundary" + System.currentTimeMillis();
            byte[] fileBytes = foto.getBytes();
            String fileName = foto.getOriginalFilename() != null ?
                    foto.getOriginalFilename() : "foto.jpg";

            byte[] header = ("--" + boundary + "\r\n" +
                    "Content-Disposition: form-data; name=\"image\"; filename=\"" + fileName + "\"\r\n" +
                    "Content-Type: image/jpeg\r\n\r\n").getBytes();

            byte[] footer = ("\r\n--" + boundary + "--\r\n").getBytes();

            byte[] body = new byte[header.length + fileBytes.length + footer.length];
            System.arraycopy(header, 0, body, 0, header.length);
            System.arraycopy(fileBytes, 0, body, header.length, fileBytes.length);
            System.arraycopy(footer, 0, body, header.length + fileBytes.length, footer.length);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.deepai.org/api/densecap"))
                    .header("api-key", API_KEY)
                    .header("Content-Type", "multipart/form-data; boundary=" + boundary)
                    .POST(HttpRequest.BodyPublishers.ofByteArray(body))
                    .build();

            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            String responseBody = response.body().toLowerCase();
            labels.add(responseBody);

            System.out.println("DeepAI response: " + responseBody);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return labels;
    }
}