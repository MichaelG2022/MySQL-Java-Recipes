package recipes.entity;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Recipe {
	private Integer recipeId;
	private String recipeName;
	private String notes;
	private Integer numServings;
	private LocalTime prepTime;
	private LocalTime cookTime;
	private LocalDateTime createdAt; // let SQL create time stamp

	private List<Ingredient> ingredients = new LinkedList<>();
	private List<Step> steps = new LinkedList<>();
	private List<Category> categories = new LinkedList<>();

	// getters and setters - Lists are getters only
	public Integer getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(Integer recipeId) {
		this.recipeId = recipeId;
	}

	public String getRecipeName() {
		return recipeName;
	}

	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Integer getNumServings() {
		return numServings;
	}

	public void setNumServings(Integer numServings) {
		this.numServings = numServings;
	}

	public LocalTime getPrepTime() {
		return prepTime;
	}

	public void setPrepTime(LocalTime prepTime) {
		this.prepTime = prepTime;
	}

	public LocalTime getCookTime() {
		return cookTime;
	}

	public void setCookTime(LocalTime cookTime) {
		this.cookTime = cookTime;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public List<Step> getSteps() {
		return steps;
	}

	public List<Category> getCategories() {
		return categories;
	}

	// toString
	@Override
	public String toString() {
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm");
		String createTime = Objects.nonNull(createdAt) ? fmt.format(createdAt) : "(null)";

		String recipe = "";

		recipe += "\n   ID=" + recipeId;
		recipe += "\n   recipeName=" + recipeName;
		recipe += "\n   notes=" + notes;
		recipe += "\n   numServings=" + numServings;
		recipe += "\n   prepTime=" + prepTime;
		recipe += "\n   cookTime=" + cookTime;
		recipe += "\n   createdAt=" + createTime;

		recipe += "\n   Ingredients:";
		for (Ingredient ingredient : ingredients) {
			recipe += "\n   " + ingredient;
		} // end FOR ingredient

		recipe += "\n   Steps:";
		for (Step step : steps) {
			recipe += "\n   " + step;
		} // end FOR step

		recipe += "\n   Categories:";
		for (Category  category : categories) {
			recipe += "\n   " + category;
		} // end FOR category

		return recipe;
	} // end toString

} // end CLASS
