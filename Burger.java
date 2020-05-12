import java.util.ArrayList;
import java.util.List;

public class Burger {

	private List<Ingredient> ingredients;
	private String name;
	private float price;
	private int height;
	private int timeToCook;
	
	public Burger() {
		ingredients = new ArrayList<Ingredient> ();
		name = "";
		price = 0;
		height = 0;
		timeToCook = 0;
	}
	
	public void addIngredient(Ingredient ingredient) {
		ingredients.add(ingredient);
	}
	
	public String getName() {
		return name;
	}
	
	public float getPrice() {
		calculatePrice();
		return price;
	}
	
	public int getHeight() {
		calculateHeight();
		return height;
	}
	
	public int getTime() {
		calculateTime();
		return timeToCook;
	}
	
	public void calculatePrice() {
		if (ingredients == null || ingredients.size() == 0) {
			System.out.println("Nothing to calculate!");
		} else {
			float price = 0;
			for (Ingredient ingredient : ingredients) {
				price += ingredient.getPrice();
			}
			this.price = price;
		}
	}
	
	public void calculateHeight() {
		if (ingredients == null || ingredients.size() == 0) {
			System.out.println("Nothing to calculate!");
		} else {
			int height = 0;
			for (Ingredient ingredient : ingredients) {
				height += ingredient.calculateHeight();
			}
			this.height = height;
		}
	}
	
	public void calculateTime() {
		if (ingredients == null || ingredients.size() == 0) {
			System.out.println("Nothing to calculate!");
		} else {
			int cookingTime = 0;
			for (Ingredient ingredient : ingredients) {
				cookingTime += ingredient.prepare();
			}
			this.timeToCook = cookingTime;
		}
	}
	
}
