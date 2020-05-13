import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class IngredientStorage {

	
	private static IngredientStorage storage;	// only instance of IngredientStorage
	
	public static enum Type {bun, patty, salad, vegetable, sauce};
	
	private Map<Integer, Ingredient> storageItems;
	
	// Private constructor avoids Initialization
	private IngredientStorage() {
		
		storageItems = new TreeMap<Integer, Ingredient> ();
		
		// Default Buns
		storageItems.put(10, new Bun("Standard Bun", 10, 0.85f, true, true, false, 27, 90));
		storageItems.put(11, new Bun("Sesame Bun", 11, 0.95f, true, true, false, 28, 90));
		storageItems.put(12, new Bun("Vegan Bun", 12, 0.55f, false, true, true, 34, 240));
		storageItems.put(13, new Bun("Ciabatta", 13, 0.45f, false, true, false, 41, 330));	
		
		// Default Patties
		storageItems.put(20, new Patty("Standard Patty", 20, 1.85f, true, false, false, 25, 270));
		storageItems.put(21, new Patty("Grilled Chicken Patty", 21, 1.15f, false, false, false, 11, 180));
		storageItems.put(22, new Patty("Falafel Patty", 22, 1.45f, false, true, true, 21, 210));
		storageItems.put(23, new Patty("Veggie Patty", 23, 1.75f, false, true, false, 25, 240));
		
		// Default Salads
		storageItems.put(30, new Salad("Iceberg Salad", 30, 0.18f, true));
		storageItems.put(31, new Salad("Arugula Salad", 31, 0.25f, false));
		
		// Default Vegetables
		storageItems.put(40, new Vegetable("Tomato", 40, 0.25f, true, 3, 3));
		storageItems.put(41, new Vegetable("Pickle", 41, 0.15f, true, 4, 2));
		storageItems.put(42, new Vegetable("Red Onion", 42, 0.08f, false, 5, 2));
		storageItems.put(43, new Vegetable("Jalapeno", 43, 0.08f, false, 5, 2));
		
		// Default Sauces
		storageItems.put(50, new Sauce("Ketchup", 50, 0.10f, true, true, true, 5, Sauce.Flavor.normal));
		storageItems.put(51, new Sauce("Sandwich Sauce", 51, 0.15f, true, true, false, 10, Sauce.Flavor.normal));
		storageItems.put(52, new Sauce("Chili Sauce", 52, 0.25f, false, true, true, 8, Sauce.Flavor.hot));
		storageItems.put(53, new Sauce("Honey Mustard Sauce", 54, 0.18f, false, true, false, 8, Sauce.Flavor.sweet));
	}
	
	/**
	 * Only method capable of creating an instance of IngredientStorage.
	 * Avoids multiple initialization to make sure there is only one storage.
	 * 
	 * @return (IngredientStorage) Instance of IngredientStorage
	 */
	public static IngredientStorage getInstance() {
		if (storage == null) {
			storage = new IngredientStorage();
		}
		return storage;
	}
	
	/**
	 * This method allows adding ingredients to the storage at runtime.
	 * 
	 * Checks if ingredient ID to be added is already in storage
	 * and adds it if not.
	 * 
	 * @param toAdd (Ingredient) - Ingredient that should be added to the storage.
	 */
	public void add(Ingredient toAdd) {
		int key = toAdd.getId();
		if (!storageItems.containsKey(key)) {
			storageItems.put(key, toAdd);
		} else {
			System.out.println("Ingredient ID already exists in Storage!");
		}
	}
	
	/**
	 * Gets and returns Ingredient with given ID.
	 * 
	 * @param id (int) - ID of Ingredient.
	 * @see java.util.HashMap#get(Object)
	 */
	public Ingredient get(int id) {
		return storageItems.get(id);
	}
	
	/**
	 * Prints all ingredients in storage to console.
	 */
	public void printAll() {
		for (Ingredient toPrint : storageItems.values()) {
			System.out.println(toPrint);
		}
	}
	
	public void printAllOf(Type type) {
		if (type == Type.bun) {
			for (Ingredient toPrint : storageItems.values()) {
				if (toPrint instanceof Bun) {
					System.out.println(toPrint);
				}
			}
		} else if (type == Type.patty) {
			for (Ingredient toPrint : storageItems.values()) {
				if (toPrint instanceof Patty) {
					System.out.println(toPrint);
				}
			}
		} else if (type == Type.salad) {
			for (Ingredient toPrint : storageItems.values()) {
				if (toPrint instanceof Salad) {
					System.out.println(toPrint);
				}
			}
		} else if (type == Type.vegetable) {
			for (Ingredient toPrint : storageItems.values()) {
				if (toPrint instanceof Vegetable) {
					System.out.println(toPrint);
				}
			}
		} else if (type == Type.sauce) {
			for (Ingredient toPrint : storageItems.values()) {
				if (toPrint instanceof Sauce) {
					System.out.println(toPrint);
				}
			}
		}
	}
	
	
}
