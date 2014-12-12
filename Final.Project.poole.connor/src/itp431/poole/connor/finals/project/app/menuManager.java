package itp431.poole.connor.finals.project.app;

import java.util.ArrayList;

public final class menuManager {
	private static ArrayList<menuItem> menuEntries;

	public static ArrayList<menuItem> getMenuEntries() {
		return menuEntries;
	}

	public static void setMenuEntries(ArrayList<menuItem> menuEntriesnew) {
		menuEntries = menuEntriesnew;
	}

}
