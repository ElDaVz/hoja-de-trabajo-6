import tienda.online.MapFactory;
import tienda.online.Reader;
import tienda.online.Store;

import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Selección de implementación
        System.out.println("Seleccione implementación de Map:");
        System.out.println("1. HashMap");
        System.out.println("2. TreeMap");
        System.out.println("3. LinkedHashMap");

        Map<String, String> inventory;
        try {
            int mapOption = readInt(sc, "Opción: ");
            inventory = MapFactory.getMap(mapOption);
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
            return;
        }

        // Cargar inventario
        Map<String, String> loaded = Reader.readFile("ListadoProducto.txt");
        inventory.putAll(loaded);

        // El carrito siempre usa HashMap (no necesita Factory)
        Map<String, Integer> cart = new java.util.HashMap<>();

        Store store = new Store(inventory, cart);

        // Menú principal
        boolean running = true;
        while (running) {
            System.out.println("\n===== TIENDA ONLINE =====");
            System.out.println("1. Agregar producto al carrito");
            System.out.println("2. Mostrar categoría de un producto");
            System.out.println("3. Ver carrito");
            System.out.println("4. Ver carrito ordenado por categoría");
            System.out.println("5. Ver inventario completo");
            System.out.println("6. Ver inventario ordenado por categoría");
            System.out.println("0. Salir");

            int option;
            try {
                option = readInt(sc, "Opción: ");
            } catch (NumberFormatException  e) {
                System.err.println("Ingrese un número válido.");
                continue;
            }

            switch (option) {
                case 1 -> {
                    System.out.print("Ingrese categoría: ");
                    String category = sc.nextLine().trim().toLowerCase();
                    System.out.print("Ingrese producto: ");
                    String product = sc.nextLine().trim().toLowerCase();
                    try {
                        int quantity = readInt(sc, "Ingrese cantidad: ");
                        store.addProductByCategory(category, product, quantity);
                        System.out.println("Producto agregado correctamente.");
                    } catch (IllegalArgumentException e) {
                        System.err.println("Error: " + e.getMessage());
                    }
                }
                case 2 -> {
                    System.out.print("Ingrese nombre del producto: ");
                    String product = sc.nextLine().trim().toLowerCase();
                    try {
                        String category = store.showCategoriesByProduct(product);
                        System.out.println("Categoría: " + category);
                    } catch (IllegalArgumentException e) {
                        System.err.println("Error: " + e.getMessage());
                    }
                }
                case 3 -> store.showCart();
                case 4 -> store.showCartSortedByCategory();
                case 5 -> store.showInventory();
                case 6 -> store.showInventorySortedByCategory();
                case 0 -> running = false;
                default -> System.err.println("Opción no válida.");
            }
        }

        System.out.println("Hasta luego.");
        sc.close();
    }

    private static int readInt(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.err.println("Entrada inválida. Ingrese un número.");
            }
        }
    }
}
