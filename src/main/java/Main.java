import tienda.online.Reader;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String, String> inventario = Reader.readFile("ListadoProducto.txt");

        for (Map.Entry<String, String> entrada : inventario.entrySet()) {
            System.out.println("Producto: " + entrada.getKey() + " | Categoría: " + entrada.getValue());
        }

        System.out.println("\nTotal productos leídos: " + inventario.size());
    }
}
