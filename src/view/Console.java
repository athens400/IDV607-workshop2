package view;

import java.util.Scanner;

public class Console {

	private Scanner input = new Scanner(System.in);
	
	public enum Event {
		NEW_MEMBER,
		DELETE_MEMBER,
		EDIT_MEMBER,
		VIEW_MEMBER,
		LIST_MEMBERS_COMPACT,
		LIST_MEMBERS_VERBOSE,
		REGISTER_BOAT,
		DELETE_BOAT,
		EDIT_BOAT,
		QUIT,
		NONE
	}
	
	public void displayWelcome() {
		displayHeadline("Welcome to the Happy Pirate!");
	}
	
	public void displayMenu() {
		waitForEnter();
		blankLines(2);
		breakStar();
		System.out.println("Enter a number in the menu and press enter.");
		breakStar();
		int i = 1;
		for(Event e : Event.values()) {
			if(e != Event.NONE) {
				System.out.println(i + ".\t" + e.toString().replaceAll("_", " "));
			}
			++i;
		}
		breakLine();
	}
	
	public void displayBoatTypeMenu() {
		int i = 1;
		for(model.Boat.BoatType type : model.Boat.BoatType.values()) {
			System.out.println(i + ". " + type);
			++i;
		}
	}
	
	public Event getEvent() {
		int i = getInt();
		Event e;
		blankLines(2);
		switch(i) {
		case 1: 
			e = Event.NEW_MEMBER;
			displayHeadline("Register new member");
			break;
		case 2:
			e = Event.DELETE_MEMBER;
			displayHeadline("Delete a member");
			break;
		case 3:
			e = Event.EDIT_MEMBER;
			displayHeadline("Edit member information");
			break;
		case 4:
			e = Event.VIEW_MEMBER;
			displayHeadline("View member information");
			break;
		case 5: 
			e = Event.LIST_MEMBERS_COMPACT;
			displayHeadline("Compact list of members");
			break;
		case 6:
			e = Event.LIST_MEMBERS_VERBOSE;
			displayHeadline("Verbose list of members");
			break;
		case 7:
			e = Event.REGISTER_BOAT;
			displayHeadline("Register new boat");
			break;
		case 8:
			e = Event.DELETE_BOAT;
			displayHeadline("Delete boat");
			break;
		case 9:
			e = Event.EDIT_BOAT;
			displayHeadline("Edit boat information");
			break;
		case 10:
			e = Event.QUIT;
			displayHeadline("QUIT");
			input.close();
			break;
		default: 
			e = Event.NONE;
			break;
		}
		return e;
	}
	
	public void displayMemberCompact(model.Member member) {
		System.out.println("Member ID: " + member.getId() + ". " + member.getName() + " (" + member.getPNr() + "), " +
				member.getNrOfBoats() + " boat(s)");
		breakLine();
	}
	
	public void displayMemberVerbose (model.Member member) {
		breakLine();
		System.out.println("Member ID:\t" + member.getId());
		System.out.println("Name:\t\t" + member.getName());
		System.out.println("Personal ID nr:\t" + member.getPNr());	
		breakLine();
	}
	
	public void displayBoat(model.Boat boat, int boatNr) {
		System.out.println("Boat " + boatNr + ":\t\t" + boat.getType() + ", " + boat.getLength() + "m");
		breakLine();
	}
	
	public String getName() {
		displayCommand("Enter full name and press enter...");
		return input.nextLine();
	}
	
	public long getPNr() {
		displayCommand("Enter personal identification number (10 digits) and press enter...");
		long pNr = getLong();
		if(Long.toString(pNr).length() != 10) {
			getPNr();
		}
		return pNr;
	}
	
	public int whichMember() {
		displayCommand("Enter member ID...");
		return getInt();
	}
	
	public int whichBoat() {
		displayCommand("Enter boat number...");
		return getInt() - 1;
	}
	
	public model.Boat.BoatType getBoatType() {
		displayCommand("Enter boat type (by number)...");
		int i = getInt();
		breakLine();
		return model.Boat.BoatType.values()[i - 1];
	}
	
	public double getBoatLength() {
		displayCommand("Enter boat length");
		return getDouble();
	}
	
	public void failMember() {
		displayCommand("No member with that id");
	}
	
	public void failBoat() {
		displayCommand("No boat with that number");
	}
	
	public void success() {
		displayCommand("Success");
	}
	
	
	
	// Place in superclass if views multiplies
	
	private void displayHeadline(String headline) {
		breakStar();
		System.out.println(headline);
		breakStar();
	}
	
	private void displayCommand(String command) {
		breakLine();
		System.out.println(command);
		breakLine();
	}
	
	private void breakStar() {
		System.out.println("******************************************************************************");
	}
	
	private void breakLine() {
		System.out.println("------------------------------------------------------------------------------");
	}
	
	private void blankLines(int nrOfLines) {
		for(int i = 0; i < nrOfLines; i++) {
			System.out.println();
		}
	}
	
	private void waitForEnter() {
		blankLines(1);
		System.out.println("Press Enter to continue...");
		input.nextLine();
	}
	
	private int getInt() {
		int i;
		try {
			i = Integer.parseInt(input.nextLine());
		}
		catch (NumberFormatException e) {
			System.out.println("Please enter a number (no decimals)");
			i = getInt();
		}
		return i;
	}
	
	private long getLong() {
		long l;
		try {
			l = Long.parseLong(input.nextLine());
		}
		catch (NumberFormatException e) {
			System.out.println("Please enter a number (no decimals)");
			l = getLong();
		}
		return l;
	}
	
	private double getDouble() {
		double d;
		try {
			d = Double.parseDouble(input.nextLine().replace(",", "."));
		}
		catch (NumberFormatException e) {
			System.out.println("Please enter a number");
			d = getDouble();
		}
		return d;
	}
	
}
