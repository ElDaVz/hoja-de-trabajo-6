package tienda.online;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class Store {
    private final Map<String, String> productCategory;
    private final Map<String, Integer> cart;

    public Store(Map<String, String> inventory, Map<String, Integer> cart) {
        this.productCategory = inventory;
        this.cart = cart;
    }

    public void addProductByCategory(String category, String product, int quantity) {

        if (!productCategory.containsKey(product)) {
            throw new IllegalArgumentException("El producto no existe en el inventario");
        }

        var realCategory = productCategory.get(product);

        if (!realCategory.equals(category)) {
            throw new IllegalArgumentException("El producto " + product + " no pertenece a " + category +
                    " pertenece a: " + realCategory);
        }

        if (quantity <= 0) {
            throw new IllegalArgumentException("El producto " + product + " no puede ser menor que 0");
        }

        cart.put(product, cart.getOrDefault(product, 0) + quantity);
    }

    public String showCategoriesByProduct(String product) {
        var category = productCategory.get(product);

        if (category == null) {
            throw new IllegalArgumentException("El producto " + product + " no existe en el inventario");
        }
        return category;
    }

    public void showCart() {
        if (cart.isEmpty()) {
            System.out.println("El carrito está vacío.");
            return;
        }
        System.out.printf("%-35s %-25s %s%n", "Producto", "Categoría", "Cantidad");
        System.out.println("-".repeat(70));
        for (Map.Entry<String, Integer> entry : cart.entrySet()) {
            String product  = entry.getKey();
            String category = productCategory.get(product);
            System.out.printf("%-35s %-25s %d%n", product, category, entry.getValue());
        }
    }


    public void showCartSortedByCategory() {
        if (cart.isEmpty()) {
            System.out.println("El carrito está vacío.");
            return;
        }
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(cart.entrySet());
        entries.sort(Comparator.comparing(e -> productCategory.get(e.getKey())));

        System.out.printf("%-35s %-25s %s%n", "Producto", "Categoría", "Cantidad");
        System.out.println("-".repeat(70));
        for (Map.Entry<String, Integer> entry : entries) {
            String product  = entry.getKey();
            String category = productCategory.get(product);
            System.out.printf("%-35s %-25s %d%n", product, category, entry.getValue());
        }
    }

    public void showInventory() {
        System.out.printf("%-35s %-25s%n", "Producto", "Categoría");
        System.out.println("-".repeat(60));
        for (Map.Entry<String, String> entry : productCategory.entrySet()) {
            System.out.printf("%-35s %-25s%n", entry.getKey(), entry.getValue());
        }
    }

    public void showInventorySortedByCategory() {
        List<Map.Entry<String, String>> entries = new ArrayList<>(productCategory.entrySet());
        entries.sort(Map.Entry.comparingByValue());

        System.out.printf("%-35s %-25s%n", "Producto", "Categoría");
        System.out.println("-".repeat(60));
        for (Map.Entry<String, String> entry : entries) {
            System.out.printf("%-35s %-25s%n", entry.getKey(), entry.getValue());
        }
    }
}
