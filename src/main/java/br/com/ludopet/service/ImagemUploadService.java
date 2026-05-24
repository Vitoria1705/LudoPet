package br.com.ludopet.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;

@Service
public class ImagemUploadService {

    private static final Path PASTA_UPLOADS = Paths.get("uploads", "produtos");
    private static final Set<String> EXTENSOES_PERMITIDAS = Set.of("jpg", "jpeg", "png", "webp", "gif");

    public String salvarImagemProduto(MultipartFile arquivo) throws IOException {
        if (arquivo == null || arquivo.isEmpty()) {
            return null;
        }

        String contentType = arquivo.getContentType();
        if (contentType == null || !contentType.toLowerCase(Locale.ROOT).startsWith("image/")) {
            throw new IllegalArgumentException("Envie um arquivo de imagem valido.");
        }

        String nomeOriginal = StringUtils.cleanPath(arquivo.getOriginalFilename() == null ? "" : arquivo.getOriginalFilename());
        String extensao = extrairExtensao(nomeOriginal);

        if (!EXTENSOES_PERMITIDAS.contains(extensao)) {
            throw new IllegalArgumentException("Formato de imagem nao permitido. Use JPG, PNG, WEBP ou GIF.");
        }

        Files.createDirectories(PASTA_UPLOADS);

        String nomeArquivo = UUID.randomUUID() + "." + extensao;
        Path destino = PASTA_UPLOADS.resolve(nomeArquivo).normalize();

        Files.copy(arquivo.getInputStream(), destino, StandardCopyOption.REPLACE_EXISTING);

        return "/uploads/produtos/" + nomeArquivo;
    }

    private String extrairExtensao(String nomeArquivo) {
        int ponto = nomeArquivo.lastIndexOf('.');

        if (ponto == -1 || ponto == nomeArquivo.length() - 1) {
            throw new IllegalArgumentException("A imagem precisa ter extensao.");
        }

        return nomeArquivo.substring(ponto + 1).toLowerCase(Locale.ROOT);
    }
}
