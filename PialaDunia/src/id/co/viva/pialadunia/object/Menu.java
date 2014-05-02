package id.co.viva.pialadunia.object;

public class Menu {
	private String name;
	private String description;
	private int icon;
	private boolean selected;
	
	public Menu(String name, String description, int icon, boolean selected) {
		this.name = name;
		this.description = description;
		this.icon = icon;
		this.selected = selected;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getIcon() {
		return icon;
	}

	public void setIcon(int icon) {
		this.icon = icon;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}
