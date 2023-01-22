package recipes.entity;

public class Unit {
	private Integer unitId;
	private String unitNameSingular;
	private String unitNamePlural;

	// getters and setters
	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public String getUnitNameSingular() {
		return unitNameSingular;
	}

	public void setUnitNameSingular(String unitNameSingular) {
		this.unitNameSingular = unitNameSingular;
	}

	public String getUnitNamePlural() {
		return unitNamePlural;
	}

	public void setUnitNamePlural(String unitNamePlural) {
		this.unitNamePlural = unitNamePlural;
	}

	// toString()
	@Override
	public String toString() {
		return "Unit [unitId=" + unitId + ", unitNameSingular=" + unitNameSingular + ", unitNamePlural="
				+ unitNamePlural + "]";
	}

} // end CLASS
