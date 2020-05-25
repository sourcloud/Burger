import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a burger that has a name and consits of ingredients.
 *
 */
public class Burger {

	public static final int MAX_INGREDIENTS = 12;
	
	private List<Ingredient> ingredientList;
	private String name;
	
	/**
	 * Default constructor initializes all attributes to default values.
	 */
	public Burger() {
		ingredientList = new ArrayList<Ingredient> ();
		name = "";
	}
	
	/**
	 * Getter for Burger name.
	 * 
	 * @return (String) Name of the Burger
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Setter for Burger name.
	 * 
	 * @param name (String) Name to be set.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Checks if one of the ingredients of the burger is a bun.
	 * 
	 * @return (boolean) true if there is a bun in ingredient list, false else.
	 */
	public boolean hasBun() {
		if (ingredientListExistsAndIsNotEmpty())
			for (Ingredient toCheck : ingredientList)
				if (toCheck instanceof Bun) 
					return true;
		return false;
	}
	
	/**
	 * Calculates the price of burger by adding the price of individual
	 * ingredients.
	 * 
	 * @return (float) Price of the burger
	 */
	public float calculatePrice() {
		float price = 0;
		if (ingredientListExistsAndIsNotEmpty()) {			
			for (Ingredient ingredient : ingredientList) {
				price += ingredient.getPrice();
			}
		}
		return price;
	}
	
	/**
	 * Calculates the height of burger by adding the height of individual
	 * ingredients that affect height.
	 * 
	 * @return (int) Height of the burger
	 */
	public int calculateHeight() {
		int height = 0;
		if (ingredientListExistsAndIsNotEmpty()) {			
			for (Ingredient ingredient : ingredientList) {
				if (ingredient instanceof HeightChanger) {
					HeightChanger h = (HeightChanger) ingredient;
					height += h.calculateHeight();
				}
			}
		}
		return height;
	}
	
	/**
	 * Calculates the time to prepare the burger by adding the time needed
	 * to prepare individual ingredients that are not prepared yet.
	 * 
	 * @return (int) Time needed to prepare the burger.
	 */
	public int calculateTime() {
		int time = 0;
		if (ingredientListExistsAndIsNotEmpty()) {			
			for (Ingredient ingredient : ingredientList) {
				if (ingredient instanceof TimeConsumer) {
					TimeConsumer t = (TimeConsumer) ingredient;
					time += t.getTime();
				}
			}
		}
		return time;
	}
	
	/**
	 * Determines if burger is classic by evaluating classic property of
	 * each individual ingredient.
	 * 
	 * @return (boolean) True if each ingredient is classic, false else.
	 */
	public boolean determineClassic() {
		
		if (!ingredientListExistsAndIsNotEmpty()) {
			return false;
					
		} else {
			for (Ingredient ingredient : ingredientList) {
				if (!ingredient.isClassic()) {
					return false;
				}
			}
			return true;
		}
	}
	
	/**
	 * Determines if burger is vegetarian by evaluating vegetarian property of
	 * each individual ingredient.
	 * 
	 * @return (boolean) True if each ingredient is vegetarian, false else.
	 */
	public boolean determineVegetarian() {
		
		if (!ingredientListExistsAndIsNotEmpty()) {
			return false;
			
		} else {
			for (Ingredient ingredient : ingredientList) {
				if (!ingredient.isVegetarian()) {
					return false;
				}
			}
			return true;	
		}
	}
	
	/**
	 * Determines if burger is vegan by evaluating vegan property of
	 * each individual ingredient.
	 * 
	 * @return (boolean) True if each ingredient is vegan, false else.
	 */
	public boolean determineVegan() {
		
		if (ingredientListExistsAndIsNotEmpty()) {
			return false;
			
		} else {
			for (Ingredient ingredient : ingredientList) {
				if (!ingredient.isVegan()) {
					return false;
				}
			}
			return true;
		}
	}
	
	/**
	 * Determines flavors of the burger by evaluating flavor of each sauce.
	 * 
	 * @return (List<String>) List of flavor names.
	 */
	public List<String> determineFlavors() {
		List<String> flavors = new ArrayList<String>();
		if (ingredientListExistsAndIsNotEmpty()) {
			for (Ingredient ingredient : ingredientList) {
				if (ingredient instanceof Sauce) {
					Sauce s = (Sauce) ingredient;
					String flavorName = s.getFlavorName();
					flavors.add(flavorName);
				}
			}
		}
		return flavors;
	}
	
	/**
	 * Tries to add an Ingredient to a Burger.
	 * 
	 * Adds if given input is not null and size of Burger allows additional
	 * Ingredient. If input is of type Bun, it only adds if there is no
	 * Bun in Burger already. If only one Ingredient can be added and there is
	 * no Bun yet, last one has to be Bun.
	 * 
	 * @param toAdd (Ingredient) Ingredient to add to the Burger.
	 */
	public void addIngredient(Ingredient toAdd) {
		
		boolean addingBun = (toAdd instanceof Bun);
		boolean addingLast = (ingredientList.size() >= MAX_INGREDIENTS - 1);
		boolean alreadyMaxed = (ingredientList.size() >= MAX_INGREDIENTS);
		boolean alreadyHasBun = hasBun();

		if (toAdd == null) {
			System.out.println("You cannot add nothing!");
			
		} else if (alreadyMaxed) {
			System.out.println("Burger already reached max ingredients!");
			
		} else if (addingBun && alreadyHasBun) {
			System.out.println("You cannot add a second bun.");
			
		} else if (addingLast && !alreadyHasBun && !addingBun) {
			System.out.println("A Burger needs a bun. Please add it now!");
			
		} else {
			ingredientList.add(toAdd);
		}
	}
	
	/**
	 * Shows how Burger is made by printing the preparation steps
	 * of each ingredient to console individually.
	 */
	public void printRecipe() {
		if (ingredientListExistsAndIsNotEmpty())
			for (Ingredient toPrepare : ingredientList)
				toPrepare.prepare();
	}
	
	/**
	 * Checks if list of ingredients exists and is not empty.
	 * 
	 * @return True if list of ingredient list exists and is not empty, else false.
	 */
	private boolean ingredientListExistsAndIsNotEmpty() {
		return (ingredientList != null && ingredientList.size() > 0);
	}

	@Override
	public String toString() {
		String nameString = name.isBlank() ? "Burger" : name;
		List<String> ingredientNames = new ArrayList<String>();
		for (Ingredient ingredient : ingredientList) {
			ingredientNames.add(ingredient.getName());
		}
		return nameString + ingredientNames;
	}
	
}
