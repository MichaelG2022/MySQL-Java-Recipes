DROP TABLE IF EXISTS ingredient;
DROP TABLE IF EXISTS step;
DROP TABLE IF EXISTS recipe_category;
DROP TABLE IF EXISTS unit;
DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS recipe;

CREATE TABLE recipe (
  recipe_id INT AUTO_INCREMENT NOT NULL,
  recipe_name VARCHAR(128) NOT NULL,
  notes TEXT,
  num_servings INT,
  prep_time TIME,
  cook_time TIME,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (recipe_id)
);

CREATE TABLE category (
  category_id INT AUTO_INCREMENT NOT NULL,
  category_name VARCHAR(64) NOT NULL,
  PRIMARY KEY (category_id)
);

CREATE TABLE unit (
  unit_id INT AUTO_INCREMENT NOT NULL,
  unit_name_singular VARCHAR(32) NOT NULL,
  unit_name_plural VARCHAR(34) NOT NULL,
  PRIMARY KEY (unit_id)
);

CREATE TABLE recipe_category (
  recipe_id INT NOT NULL,
  category_id INT NOT NULL,
  FOREIGN KEY (recipe_id) REFERENCES recipe (recipe_id) ON DELETE CASCADE,
  FOREIGN KEY (category_id) REFERENCES category (category_id) ON DELETE CASCADE,
  UNIQUE KEY (recipe_id, category_id)
);

CREATE TABLE step (
  step_id INT AUTO_INCREMENT NOT NULL,
  recipe_id INT NOT NULL,
  step_order INT NOT NULL,
  step_text TEXT NOT NULL,
  PRIMARY KEY (step_id),
  FOREIGN KEY (recipe_id) REFERENCES recipe (recipe_id) ON DELETE CASCADE
);

CREATE TABLE ingredient (
  ingredient_id INT AUTO_INCREMENT NOT NULL,
  recipe_id INT NOT NULL,
  unit_id INT,
  ingredient_name VARCHAR(64) NOT NULL,
  instruction VARCHAR(64),
  ingredient_order INT NOT NULL,
  amount DECIMAL(7, 2),
  PRIMARY KEY (ingredient_id),
  FOREIGN KEY (recipe_id) REFERENCES recipe (recipe_id) ON DELETE CASCADE,
  FOREIGN KEY (unit_id) REFERENCES unit (unit_id)
);