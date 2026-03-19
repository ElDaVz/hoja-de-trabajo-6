package tienda.online;
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
}
