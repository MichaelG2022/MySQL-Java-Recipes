package recipes.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import provided.util.DaoBase;
import recipes.entity.Category;
import recipes.entity.Ingredient;
import recipes.entity.Recipe;
import recipes.entity.Step;
import recipes.entity.Unit;
import recipes.exception.DbException;

public class RecipeDao extends DaoBase {

	// constants
	private static final String CATEGORY_TABLE = "category";
	private static final String INGREDIENT_TABLE = "ingredient";
	private static final String RECIPE_TABLE = "recipe";
	private static final String RECIPE_CATEGORY_TABLE = "recipe_category";
	private static final String STEP_TABLE = "step";
	private static final String UNIT_TABLE = "unit";
	
	public Optional<Recipe> fetchRecipeById(Integer recipeId) {
		String sql = "SELECT * FROM " + RECIPE_TABLE + " WHERE recipe_id = ?";
		
		try(Connection conn = DbConnection.getConnection()) {
			startTransaction(conn);
			
			try {
				Recipe recipe = null;
				
				try(PreparedStatement stmt = conn.prepareStatement(sql)) {
					setParameter(stmt, 1, recipeId, Integer.class);
					
					try(ResultSet rs = stmt.executeQuery()) {
						if(rs.next()) {
							recipe = extract(rs, Recipe.class);
						}
					}
				}
				if(Objects.nonNull(recipe)) {
					recipe.getIngredients().addAll(fetchRecipeIngredients(conn, recipeId));					
					recipe.getSteps().addAll(fetchRecipeSteps(conn, recipeId));					
					recipe.getCategories().addAll(fetchRecipeCategories(conn, recipeId));					
				}
				commitTransaction(conn);
				return Optional.ofNullable(recipe);
			}
			catch(Exception e) {
				rollbackTransaction(conn);
				throw new DbException(e);
			}
		}
		catch(SQLException e) {
			throw new DbException(e);
		}
		
	}

	private List<Category> fetchRecipeCategories(Connection conn, Integer recipeId) throws SQLException {
		// @formatter:off
		String sql = ""
			+ "SELECT c.* "
			+ "FROM " + RECIPE_CATEGORY_TABLE + " rc "
			+ "JOIN " + CATEGORY_TABLE + " c USING (category_id) "
			+ "WHERE recipe_id = ? "
			+ "ORDER BY c.category_name";
		// @formatter:on
		
		try(PreparedStatement stmt = conn.prepareStatement(sql)) {
			setParameter(stmt, 1, recipeId,  Integer.class);
			
			try(ResultSet rs = stmt.executeQuery()) {
				List<Category> categories = new LinkedList<Category>();
				
				while(rs.next()) {
					categories.add(extract(rs, Category.class));
				}
				return categories;
				
			}
		}

	}

	private List<Step> fetchRecipeSteps(Connection conn, Integer recipeId) throws SQLException {
		String sql = "SELECT * FROM " + STEP_TABLE + " s WHERE s.recipe_id = ?";
		
		try(PreparedStatement stmt = conn.prepareStatement(sql)) {
			setParameter(stmt, 1, recipeId, Integer.class);
			
			try(ResultSet rs = stmt.executeQuery()) {
				List<Step> steps = new LinkedList<Step>();
				
				while(rs.next()) {
					steps.add(extract(rs, Step.class));
				}
				return steps;
			}
		}
	}

	/*
	 * i. means alias i for ingredient table
	 * u. means alias u for unit table
	 * Fixed red squigglies while typing by letting Java add "throws SQLException to method name
	 */
	private List<Ingredient> fetchRecipeIngredients(Connection conn, Integer recipeId) throws SQLException {
		// @formatter:off
		String sql = ""
			+ "SELECT i.*, u.unit_name_singular, u.unit_name_plural "
			+ "FROM " + INGREDIENT_TABLE + " i "
			+ "LEFT JOIN " + UNIT_TABLE + " u USING (unit_id) "
			+ "WHERE  recipe_id = ? "
			+ "ORDER BY i.ingredient_order";
		// @formatter:on
		
		 try(PreparedStatement stmt = conn.prepareStatement(sql)) {
			 setParameter(stmt, 1, recipeId, Integer.class);
			 
			 try(ResultSet rs = stmt.executeQuery()) {
				 List<Ingredient> ingredients = new LinkedList<Ingredient>();
				 
				 while(rs.next()) {
					 Ingredient ingredient = extract(rs, Ingredient.class);
					 Unit unit = extract(rs, Unit.class);
					 
					 ingredient.setUnit(unit);
					 ingredients.add(ingredient);
				 }
				 return ingredients;
			 }
		 }
	}

	public List<Recipe> fetchAllRecipes() {
		String sql = "SELECT * FROM " + RECIPE_TABLE + " ORDER BY recipe_name";

		try (Connection conn = DbConnection.getConnection()) {
			startTransaction(conn);

			try (PreparedStatement stmt = conn.prepareStatement(sql)) {

				try (ResultSet rs = stmt.executeQuery()) {
					List<Recipe> recipes = new LinkedList<>();

					while (rs.next()) {
						recipes.add(extract(rs, Recipe.class));
					}
					return recipes;
				}
			} catch (Exception e) {
				rollbackTransaction(conn);
				throw new DbException(e);
			}
		} catch (SQLException e) {
			throw new DbException(e);
		}
	} // end fetchAllRecipes

	public Recipe insertRecipe(Recipe recipe) {
		// @formatter:off
		String sql = "" + "INSERT INTO " + RECIPE_TABLE + " "
			+ "(recipe_name, notes, num_servings, prep_time, cook_time) "
			+ "VALUES"
			+ "(?, ?, ?, ?, ?)";
		// @formatter:on	

		try (Connection conn = DbConnection.getConnection()) {
			startTransaction(conn);

			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				setParameter(stmt, 1, recipe.getRecipeName(), String.class);
				setParameter(stmt, 2, recipe.getNotes(), String.class);
				setParameter(stmt, 3, recipe.getNumServings(), Integer.class);
				setParameter(stmt, 4, recipe.getPrepTime(), LocalTime.class);
				setParameter(stmt, 5, recipe.getCookTime(), LocalTime.class);

				stmt.executeUpdate();
				// fetch autogenerated recipe_id
				Integer recipeId = getLastInsertId(conn, RECIPE_TABLE);

				commitTransaction(conn);

				recipe.setRecipeId(recipeId);
				return recipe;
			} catch (Exception e) {
				rollbackTransaction(conn);
				throw new DbException(e);
			}
		} catch (SQLException e) {
			throw new DbException(e);
		}
	} // end insertRecipe

	public void executeBatch(List<String> sqlBatch) {
		try (Connection conn = DbConnection.getConnection()) {
			startTransaction(conn);

			try (Statement stmt = conn.createStatement()) {
				for (String sql : sqlBatch) {
					stmt.addBatch(sql);
				}
				stmt.executeBatch();
				commitTransaction(conn);
			} catch (Exception e) {
				rollbackTransaction(conn);
				throw new DbException();
			}
		} catch (SQLException e) {
			throw new DbException(e);
		}
	}

} // end CLASS
