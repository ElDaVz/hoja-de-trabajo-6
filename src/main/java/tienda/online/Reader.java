package tienda.online;
import java.io.IOException;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Reader {
    public static Map<String, String> readFile(String path){

        Map<String, String> map = new HashMap<>();

        try (Scanner sc = new Scanner(new File(path))) {

            while (sc.hasNextLine()) {
                var line = sc.nextLine().trim();

                if (!line.contains("|")) {
                    continue;
                }

                var data =  line.split("\\|", 2);
                var category = data[0].trim();
                var product = data[1].trim();

                map.put(product, category);

            }

        } catch (IOException e) {
            throw new RuntimeException("Error al leer el archivo");
        }
        return map;
    }
}