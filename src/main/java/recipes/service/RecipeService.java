package recipes.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import recipes.dao.RecipeDao;
import recipes.entity.Category;
import recipes.entity.Ingredient;
import recipes.entity.Recipe;
import recipes.entity.Step;
import recipes.entity.Unit;
import recipes.exception.DbException;

public class RecipeService {
	private static final String SCHEMA_FILE = "recipe_schema.sql";
	private static final String DATA_FILE = "recipe_data.sql";

	private RecipeDao recipeDao = new RecipeDao();

	public Recipe fetchRecipeById(Integer recipeId) {
		return recipeDao.fetchRecipeById(recipeId)
				.orElseThrow(() -> new NoSuchElementException("Recipe with ID=" + recipeId + " does not exist."));
	}

	public void createAndPopulateTables() {
		loadFromFile(SCHEMA_FILE);
		loadFromFile(DATA_FILE);
	}

	private void loadFromFile(String fileName) {
		String content = readFileContent(fileName);
		List<String> sqlStatements = convertContentToSqlStatements(content);

		recipeDao.executeBatch(sqlStatements);
	}

	private List<String> convertContentToSqlStatements(String content) {
		content = removeComments(content);
		content = replaceWhitespaceSequencesWithSingleSpace(content);

		return extractLinesFromContent(content);
	}

	private List<String> extractLinesFromContent(String content) {
		List<String> lines = new LinkedList<>();

		while (!content.isEmpty()) {
			int semicolon = content.indexOf(";");

			if (semicolon == -1) { // index of -1 means last line (no line feed)
				if (!content.isBlank()) {
					lines.add(content);
				}
				content = "";
			} else {
				lines.add(content.substring(0, semicolon).trim());
				content = content.substring(semicolon + 1);
			}
		}
		return lines;
	}

	private String replaceWhitespaceSequencesWithSingleSpace(String content) {
		return content.replaceAll("\\s+", " "); // \s is a regular expression (regex) for whitespace. Have to double
												// backslash because
												// backslash is an escape character
	}

	private String removeComments(String content) {
		StringBuilder builder = new StringBuilder(content);
		// keep track of comment position
		int commentPos = 0;

		while ((commentPos = builder.indexOf("-- ", commentPos)) != -1) {
			int eolPos = builder.indexOf("\n", commentPos + 1);

			if (eolPos == -1) {
				builder.replace(commentPos, builder.length(), "");
			} else {
				builder.replace(commentPos, eolPos + 1, "");
			}
		}
		return builder.toString();

	}

	private String readFileContent(String fileName) {
		try {
			Path path = Paths.get(getClass().getClassLoader().getResource(fileName).toURI());
			return Files.readString(path);
		} catch (Exception e) {
			throw new DbException(e);
		}
	}

	public Recipe addRecipe(Recipe recipe) {
		return recipeDao.insertRecipe(recipe);
	}

	/*
	 * Modified to use Stream to sort recipes by ID
	 * 
	 * OLD = 	public List<Recipe> fetchRecipes() {
	 * 				return recipeDao.fetchAllRecipes();
	 * 			} // end fetchRecipes
	 *  NEW:
	 */
	public List<Recipe> fetchRecipes() {
		// @formatter:off
		return recipeDao.fetchAllRecipes()
			.stream()
			.sorted((r1, r2) -> r1.getRecipeId() - r2.getRecipeId())
			.collect(Collectors.toList());		
		// @formatter:on
	} // end fetchRecipes

	public List<Unit> fetchUnits() {
		return recipeDao.fetchAllUnits();
	} // end fetchUnits

	public void addIngredient(Ingredient ingredient) {
		recipeDao.addIngredientToRecipe(ingredient);
	}

	public void addStep(Step step) {
		recipeDao.addStepToRecipe(step);
	}

	public List<Category> fetchCategories() {
		return recipeDao.fetchAllCategories();
	} // end fetchCategories

	public void addCategoryToRecipe(Integer recipeId, String category) {
		recipeDao.addCategoryToRecipe(recipeId, category);
	}

	public List<Step> fetchSteps(Integer recipeId) {
		return recipeDao.fetchRecipeSteps(recipeId);

	}

	public void modifyStep(Step step) {
		// Checks to see if step text modification was successful
		// Throws exception here since DAO layer should not throw exception.
		// DAO method will return True if the update affected expected 1 row.
		// Returns False if 0 rows affected, which throws exception
		if (!recipeDao.modifyRecipeStep(step)) {
			throw new DbException("Step with ID=" + step.getStepId() + "  does not exist.");
		}
	}

	public void deleteRecipe(Integer recipeId) {
		if (!recipeDao.deleteRecipe(recipeId)) {
			throw new DbException("Recipe with ID=" + recipeId + " does not exist.");
		}
	} // end deleteRecipe

} // end CLASS
