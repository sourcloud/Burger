import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.hsrm.mi.prog.util.StaticScanner;

/**
 * This class controls user interaction for creating and submitting burgers
 * to the competition. Use it to get and interpret user input from command line.
 *
 */
public class Controller {
	
	public static final int MAX_COMMITS = 5;
	
	/**
	 * Valid commands that can be used by user.
	 */
	private enum Command {
		ADD,
		COMMIT,
		FINISH,
		HELP,
		MENU,
		NAME,
		NONE,
		QUIT,
		SHOW;
		
		/**
		 * Tries to match a given String with Command by comparing String
		 * with name. Returns matching Command if possible, NONE else.
		 * 
		 * @param command (String) Given String to compare with.
		 * @return Command that matches given String, NONE else. 
		 */
		public static Command fromString(String command) {
			if (command != null && !command.isBlank()) {
				for (Command toCompare : Command.values()) {
					String name = toCompare.name();
					if (name.equalsIgnoreCase(command)) {
						return toCompare;
					}
				}
			}
			return NONE;
		}
	
	}
	
	private List<Burger> commitedBurgers;

	private IngredientStorage storage;
	private Burger burgerInCreation;
	private boolean quitWanted;

	
	/**
	 * Default constructor initializes all attributes to default values.
	 */
	public Controller() {
		commitedBurgers = new ArrayList<Burger>();
		storage = IngredientStorage.getInstance();
		burgerInCreation = new Burger();
		quitWanted = false;
	}

	/**
	 * This method answers, if application should end.
	 * 
	 * @return (boolean) Answering if application should end.
	 */
	public boolean quitWanted() {
		return quitWanted;
	}

	/**
	 * This methods gets user input from console and tries to interpret it.
	 * 
	 * @see StaticScanner#nextString()
	 * @see Controller#interpretInput(String)
	 */
	public void getAndInterpretUserInput() {
		System.out.print(">> ");
		String userInput = StaticScanner.nextString();
		interpretInput(userInput);
	}

	/**
	 * Prints introduction text to console.
	 */
	public void printIntro() {
		System.out.println("Welcome to the competition!");
		System.out.println();
		System.out.println("You can now create your own burger!");
		System.out.println("A burger consits of exactly one bun and up to "
				+ Burger.MAX_INGREDIENTS + " other ingredients.");
		System.out.println();
		System.out.println("To get the recipe for your burger or to commit it"
				+ " to the competition, you need to name it first.");
		System.out.println();
		// TODO: write intro text
	}

	/**
	 * Prints list of Ingredients in Storage to console.
	 */
	public void printChoice() {
		System.out.println("You can choose between following ingredients:");
		storage.printAll();
		System.out.println();
	}

	/**
	 * Prints list of commands to console.
	 */
	public void printHelp() {
		System.out.println("You can use the following commands:");
		System.out.println();
		
		System.out.print(Command.ADD);
		System.out.print(" [Ingredient ID] - ");
		System.out.println("Adds ingredient to your burger. Multiple IDs possible.");
		
		System.out.print(Command.COMMIT);
		System.out.print("\t\t    - ");
		System.out.println("Commits current state of your burger to competition.");
		
		System.out.print(Command.FINISH);
		System.out.print("\t\t    - ");
		System.out.println("Finishes the competition and quits application.");
		
		System.out.print(Command.HELP);
		System.out.print("\t\t    - ");
		System.out.println("Opens this list of commands.");
		
		System.out.print(Command.MENU);
		System.out.print("\t\t    - ");
		System.out.println("Shows all ingredients in storage.");
		
		System.out.print(Command.NAME);
		System.out.print(" [name]");
		System.out.print("\t    - ");
		System.out.println("Assigns a name to your burger.");
		
		System.out.print(Command.QUIT);
		System.out.print("\t\t    - ");
		System.out.println("Quits application.");
		
		System.out.print(Command.SHOW);
		System.out.print("\t\t    - ");
		System.out.println("Shows current state of your burger.");
		
		System.out.println();
	}

	/**
	 * Interprets user input. First word of input is command, rest are 
	 * arguments. Checks command and executes if valid. If needed for 
	 * execution, will pass and process arguments.
	 */
	private void interpretInput(String userInput) {
		
		boolean noInputExists = (userInput == null);
		boolean inputEmpty = (userInput.isBlank());
		
		if (noInputExists || inputEmpty) {
			System.out.println("No input provided!");
			
		} else {
			
			// interprets input string as list of words
			String[] input = userInput.split(" ");
			List<String> toParse = new ArrayList<String>(Arrays.asList(input));
			
			// separates command from arguments
			String commandInput = toParse.remove(0);
			
			// tries to execute command
			Command toExecute = Command.fromString(commandInput);
			switch (toExecute) {
				case ADD:
					executeAdd(toParse);
					break;
				case COMMIT:
					commit();
					break;
				case FINISH:
					finishCurrentBurger();
					break;
				case HELP:
					printHelp();
					break;
				case MENU:
					printChoice();
					break;
				case NAME:
					executeName(toParse);
					break;
				case QUIT:
					quitWanted = true;
					break;
				case SHOW:
					printCurrentBurger();
					break;
				default:
					System.out.printf("Unknown command '%s'! ", commandInput);
					System.out.println("Type 'help' to see a list of commands!");
			}
		}
	}

	/**
	 * Tries to add a List of Ingredients to Burger.
	 * List consits of Strings, that will be interpreted as integers.
	 * 
	 * @param ingredientIDs (List<String>) List of IDs
	 */
	private void executeAdd(List<String> ingredientIDs) {
		boolean listNotExists = (ingredientIDs == null);
		boolean listEmpty = (ingredientIDs.size() == 0);
		
		if (listNotExists || listEmpty) {
			System.out.println("No IDs to add provided!");
			
		} else {
			for (String number : ingredientIDs) {
				try {
					int id = Integer.parseInt(number);
					Ingredient toAdd = storage.get(id);
					if (toAdd == null) {
						System.out.printf("ID %d is not in storage!\n", id);
					} else {
						burgerInCreation.addIngredient(toAdd);
					}
				} catch (NumberFormatException e) {
					System.out.printf("Ignored argument '%s' ", number);
					System.out.print("because it is not a number!\n");
				}
			}
		}
	}

	/**
	 * Creates String out of provided List and sets it as name of the burger.
	 * 
	 * @param namingParts (List<String>) List of Strings that will be the name.
	 */
	private void executeName(List<String> namingParts) {
		boolean listNotExists = (namingParts == null);
		boolean listEmpty = (namingParts.size() == 0);
		
		if (listNotExists || listEmpty) {
			System.out.println("No name passed!");
			
		} else {
			String name = "";
			for (String part : namingParts) {
				name += part + " ";
			}
			burgerInCreation.setName(name);
		}
	}
	
	/**
	 * Adds Burger to List of commited Burgers
	 */
	private void commit() {
		if (meetsRequirements()) {
			commitedBurgers.add(burgerInCreation);
			boolean reachedLimit = (commitedBurgers.size() >= MAX_COMMITS);
			if (reachedLimit) {
				finishCompetition();
			} else {				
				burgerInCreation = new Burger(); // start from scratch after commit
			}
		}
	}

	/**
	 * If burger in creation meet requirements for commiting, commits
	 * and finishes competition.
	 * 
	 * @see Controller#meetsRequirements()
	 * @see Controller#finishCompetition()
	 */
	private void finishCurrentBurger() {
		if (meetsRequirements()) {	
			commitedBurgers.add(burgerInCreation);
			finishCompetition();
		}
	}
	
	/**
	 * Summarizes every commited burger and sets quit status to true.
	 */
	private void finishCompetition() {
		System.out.println("Finished!");
		System.out.println("You commited following recipes: ");
		for (Burger burger : commitedBurgers) {
			printSummary(burger);
		}
		quitWanted = true;
	}
	
	/**
	 * Checks if burger in creation meets requirements for commiting.
	 * Commiting requires burger to have a name and a bun.
	 * 
	 * @return (boolean) true if requirements are met, else false
	 */
	private boolean meetsRequirements() {
		String burgerName = burgerInCreation.getName();
		boolean burgerHasBun = burgerInCreation.hasBun();
		boolean nameExists = (burgerName != null);
		boolean nameNotEmpty = (!burgerName.isBlank());
		if (!(nameExists && nameNotEmpty)) {
			System.out.println("Please name your burger first");
			return false;
		} else if (!burgerHasBun) {
			System.out.println("Please add a bun first!");
			return false;
		}
		return true;
	}

	/**
	 * Prints burger in creation to console.
	 */
	private void printCurrentBurger() {
		System.out.println("Your current burger: ");
		System.out.println(burgerInCreation);
	}

	/**
	 * Prints summary of a Burger to console.
	 * 
	 * @param toSummarize (Burger) Burger to summarize
	 */
	private void printSummary(Burger toSummarize) {
		String burgerName = toSummarize.getName();
		float burgerPrice = toSummarize.calculatePrice();
		int burgerHeight = toSummarize.calculateHeight();
		int burgerTime = toSummarize.calculateTime();
		int minutes = burgerTime / 60;
		int seconds = burgerTime % 60;
		
		String classicString = toSummarize.determineClassic() ? " classic" : "";
		String veggieString = "";
		
		if (toSummarize.determineVegan()) {
			veggieString = " vegan";
		} else if (toSummarize.determineVegetarian()) {
			veggieString = " vegetarian";
		}
		
		System.out.println();
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.printf("This is your recipe for %s:\n", burgerName);
		System.out.println("-----------------------------");
		toSummarize.printRecipe();
		System.out.println("-----------------------------");
		System.out.printf("It takes %d minute(s) and %d seconds to prepare\n", minutes, seconds);
		System.out.printf("and will be %d mm high!\n", burgerHeight);
		System.out.println("-----------------------------");
		System.out.printf("It costs %.2f EUR.\n", burgerPrice);
		System.out.println("-----------------------------");
		System.out.println("You'll get a" + veggieString + classicString + " Burger!");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println();
	}

}
