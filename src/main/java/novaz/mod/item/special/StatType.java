package novaz.mod.item.special;

public class StatType {
	public String name = "";
	public String description = "";
	public int maxLevel = 50;
	public int baseCost = 1;
	public double costPerLevel = 0;

	public StatType(String name) {
		this.name = name;
		this.description = name;
	}

	public StatType(String name, int maxLevel) {
		this.name = name;
		this.description = name;
		this.maxLevel = maxLevel;
	}
	public StatType(String name,String description, int maxLevel,int baseCost, double costPerLevel){
		this.name = name;
		this.description = description;
		this.maxLevel = maxLevel;
		this.baseCost = baseCost;
		this.costPerLevel = costPerLevel;
	}
}
