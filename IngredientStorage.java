import java.util.Map;
import java.util.TreeMap;


/**
 * This class manages ingredients to put on a burger. Ingredients can be 
 * accessed by their ID and new ingredients can be added.
 *
 */
public class IngredientStorage {
	
	// only instance of IngredientStorage
	private static IngredientStorage storage; 
	
	private Map<Integer, Ingredient> storageItems;
	
	// Private constructor avoids Initialization
	private IngredientStorage() {
		
		storageItems = new TreeMap<Integer, Ingredient> ();
		
		// Default Buns
		add(new Bun("Standard Bun", 10, 0.85f, true, true, false, 27, 90));
		add(new Bun("Sesame Bun", 11, 0.95f, true, true, false, 28, 90));
		add(new Bun("Vegan Bun", 12, 0.55f, false, true, true, 34, 240));
		add(new Bun("Ciabatta", 13, 0.45f, false, true, false, 41, 330));	
		
		// Default Patties
		add(new Patty("Standard Patty", 20, 1.85f, true, false, false, 25, 270));
		add(new Patty("Grilled Chicken Patty", 21, 1.15f, false, false, false, 11, 180));
		add(new Patty("Falafel Patty", 22, 1.45f, false, true, true, 21, 210));
		add(new Patty("Veggie Patty", 23, 1.75f, false, true, false, 25, 240));
		
		// Default Salads
		add(new Salad("Iceberg Salad", 30, 0.18f, true));
		add(new Salad("Arugula Salad", 31, 0.25f, false));
		
		// Default Vegetables
		add(new Vegetable("Tomato", 40, 0.25f, true, 3, 3));
		add(new Vegetable("Pickle", 41, 0.15f, true, 4, 2));
		add(new Vegetable("Red Onion", 42, 0.08f, false, 5, 2));
		add(new Vegetable("Jalapeno", 43, 0.08f, false, 5, 2));
		
		// Default Sauces
		add(new Sauce("Ketchup", 50, 0.10f, true, true, true, 5, 
				Sauce.Flavor.normal));
		add(new Sauce("Sandwich Sauce", 51, 0.15f, true, true, false, 10, 
				Sauce.Flavor.normal));
		add(new Sauce("Chili Sauce", 52, 0.25f, false, true, true, 8, 
				Sauce.Flavor.hot));
		add(new Sauce("Honey Mustard Sauce", 53, 0.18f, false, true, false, 8, 
				Sauce.Flavor.sweet));
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
	 * @param toAdd (Ingredient) - Ingredient that should be added to the storage
	 */
	public void add(Ingredient toAdd) {
		int key = toAdd.getId();
		boolean keyOccupied = storageItems.containsKey(key); 
		if (!keyOccupied) {
			storageItems.put(key, toAdd);
		} else {
			System.out.println("Ingredient ID already exists in Storage!");
		}
	}
	
	/**
	 * Gets and returns Ingredient with given ID.
	 * 
	 * @param id (int) - ID of Ingredient.
	 * @see java.util.TreeMap#get(Object)
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
		
}
