import java.util.ArrayList;
import java.util.List;

public class Burger {

	private final int MAX_INGREDIENTS = 12;
	
	private List<Ingredient> ingredients;
	private String name;
	private float price;
	private int height;
	private int timeToCook;
	
	/**
	 * Default constructor initializes all attributes to default values.
	 */
	public Burger() {
		ingredients = new ArrayList<Ingredient> ();
		name = "";
		price = 0;
		height = 0;
		timeToCook = 0;
	}
	
	/**
	 * This method tries to add an Ingredient to a Burger.
	 * 
	 * Adds if given input is not null and size of Burger allows additional
	 * Ingredient. If input is of type Bun, it only adds if there is no
	 * Bun in Burger already. If only one Ingredient can be added and there is
	 * no Bun yet, last one has to be Bun.
	 * 
	 * If Ingredient is added, Burger's price, height and time attributes will be
	 * updated if possible.
	 * 
	 * @param toAdd (Ingredient) Ingredient to add to the Burger.
	 */
	public void addIngredient(Ingredient toAdd) {

		if (toAdd == null) {
			System.out.println("You cannot add nothing!");
			
		} else if (ingredients.size() >= MAX_INGREDIENTS) {
			System.out.println("Burger already reached max height!");
			
		} else if (toAdd instanceof Bun && hasBun()) {
			System.out.println("You cannot add a second bun.");
			
		} else if (ingredients.size() == MAX_INGREDIENTS - 1 && !hasBun() && !(toAdd instanceof Bun)) {
			System.out.println("A Burger needs a bun. Please add it now!");
			
		} else {
			ingredients.add(toAdd);
			this.price += toAdd.price;
			if (toAdd instanceof HeightChanger) {
				HeightChanger h = (HeightChanger) toAdd;
				this.height += h.calculateHeight();
			}
			if (toAdd instanceof TimeConsumer) {
				TimeConsumer t = (TimeConsumer) toAdd;
				this.timeToCook += t.getTime();
			}
		}
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
	 * Getter for Burger price.
	 * 
	 * @return (float) Price of the Burger
	 */
	public float getPrice() {
		return price;
	}
	
	/**
	 * Getter for Burger height.
	 * 
	 * @return (int) Height of the Burger in mm
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * Getter for cooking time of Burger.
	 * 
	 * @return (int) Time to cook the Burger in seconds
	 */
	public int getTime() {
		return timeToCook;
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
	 * Shows how Burger is made by showing the preparation steps
	 * of each ingredient individually.
	 */
	public void showRecipe() {
		for (Ingredient toPrepare : ingredients)
			toPrepare.prepare();
	}
	
	private boolean hasBun() {
		if (!isEmpty())
			for (Ingredient toCheck : ingredients)
				if (toCheck instanceof Bun) 
					return true;
		return false;
	}
	
	private boolean isEmpty() {
		return ingredients == null || ingredients.size() == 0;
	}
	
	@Override
	public String toString() {
		String description = ((name == null || name.equals("")) ? "Burger" : name) + "[";
		if (!isEmpty()) { 
			for (int i = 0; i < ingredients.size(); i++) {
				description += ingredients.get(i).getName();
				if (i < ingredients.size() - 1) {
					description += ", ";
				}
			}
		}
		description += "]";
		return description;
	}
	
}
