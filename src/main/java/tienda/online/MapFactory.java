package tienda.online;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class MapFactory {
    public static Map<String, String> getMap(int option) {
        return switch (option) {
            case 1 -> new HashMap<>();
            case 2 -> new LinkedHashMap<>();
            case 3 -> new TreeMap<>();
            default -> throw new IllegalArgumentException("Opción " + option + " inválida. Intente de nuevo");
        };
    }
}
