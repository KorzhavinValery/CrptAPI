package crptapi;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        //Экземпляр CrptAPI
        CrptAPI crptAPI = new CrptAPI(TimeUnit.SECONDS, 1, "https://ismp.crpt.ru/api/v3/lk/documents/create");
        String json = "";
        try {
            //чтение из файла
            byte[] bytes = Files.readAllBytes(Paths.get("src/main/resources/document.json"));
            json = new String(bytes, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        //Преобразование json в объект Document
        CrptAPI.Document document = gson.fromJson(json, CrptAPI.Document.class);

        String signature = "signature"; //создание подписи, для вызова createDocument
        crptAPI.createDocument(document, signature);
        crptAPI.shutDown();// отключение работы с API
    }
}
