-- Insert into the unit table
INSERT INTO unit (unit_name_singular, unit_name_plural) VALUES ('teaspoon', 'teaspoons');
INSERT INTO unit (unit_name_singular, unit_name_plural) VALUES ('tablespoon', 'tablespoons');
INSERT INTO unit (unit_name_singular, unit_name_plural) VALUES ('cup', 'cups');
INSERT INTO unit (unit_name_singular, unit_name_plural) VALUES ('ounce', 'ounces');
INSERT INTO unit (unit_name_singular, unit_name_plural) VALUES ('pound', 'pounds');
INSERT INTO unit (unit_name_singular, unit_name_plural) VALUES ('gram', 'grams');
INSERT INTO unit (unit_name_singular, unit_name_plural) VALUES ('milliliter', 'milliliters');
INSERT INTO unit (unit_name_singular, unit_name_plural) VALUES ('small', 'small');
INSERT INTO unit (unit_name_singular, unit_name_plural) VALUES ('container', 'containers');
INSERT INTO unit (unit_name_singular, unit_name_plural) VALUES ('large', 'large');

-- Insert into the category table
INSERT INTO category (category_name) VALUES ('Asian');
INSERT INTO category (category_name) VALUES ('Beef');
INSERT INTO category (category_name) VALUES ('Smoker');
INSERT INTO category (category_name) VALUES ('Bread');
INSERT INTO category (category_name) VALUES ('Breakfast');
INSERT INTO category (category_name) VALUES ('Fish and Seafood');
INSERT INTO category (category_name) VALUES ('Italian');
INSERT INTO category (category_name) VALUES ('Kid Food');
INSERT INTO category (category_name) VALUES ('Low Carb');
INSERT INTO category (category_name) VALUES ('Main Dish');
INSERT INTO category (category_name) VALUES ('Mediterranean');
INSERT INTO category (category_name) VALUES ('Mexican');
INSERT INTO category (category_name) VALUES ('Pork');
INSERT INTO category (category_name) VALUES ('Poultry');
INSERT INTO category (category_name) VALUES ('Salad');
INSERT INTO category (category_name) VALUES ('Sandwiches and Wraps');
INSERT INTO category (category_name) VALUES ('Sauces and Rubs');
INSERT INTO category (category_name) VALUES ('Side Dish');
INSERT INTO category (category_name) VALUES ('Slow Cooker');
INSERT INTO category (category_name) VALUES ('Soup');
INSERT INTO category (category_name) VALUES ('Tex-Mex');
INSERT INTO category (category_name) VALUES ('Vegetarian');
INSERT INTO category (category_name) VALUES ('Veggies');
INSERT INTO category (category_name) VALUES ('Fruit');

-- Kitty Litter Cake
INSERT INTO recipe (recipe_name, notes, num_servings, prep_time, cook_time) VALUES ('Kitty Litter Cake', 'This kitty litter cake recipe has been around for quite a few years. If you want some gross Halloween party food, the kitty litter cake is always a hit!', 40, '00:20', '00:50');

INSERT INTO ingredient (recipe_id, unit_id, ingredient_name, instruction, ingredient_order, amount) VALUES (1, 4, 'chocolate cake mix', null, 1, 16.25);
INSERT INTO ingredient (recipe_id, unit_id, ingredient_name, instruction, ingredient_order, amount) VALUES (1, 4, 'yellow cake mix', null, 2, 16.25);
INSERT INTO ingredient (recipe_id, unit_id, ingredient_name, instruction, ingredient_order, amount) VALUES (1, 4, 'vanilla instant pudding mix', null, 3, 5.1);
INSERT INTO ingredient (recipe_id, unit_id, ingredient_name, instruction, ingredient_order, amount) VALUES (1, 4, 'vanilla sandwich cookies', null, 4, 25);
INSERT INTO ingredient (recipe_id, unit_id, ingredient_name, instruction, ingredient_order, amount) VALUES (1, null, 'green food coloring', null, 5, null);
INSERT INTO ingredient (recipe_id, unit_id, ingredient_name, instruction, ingredient_order, amount) VALUES (1, 8, 'Tootsie Rolls', null, 6, 15);
INSERT INTO ingredient (recipe_id, unit_id, ingredient_name, instruction, ingredient_order, amount) VALUES (1, null, 'new kitty litter pan', null, 7, 1);
INSERT INTO ingredient (recipe_id, unit_id, ingredient_name, instruction, ingredient_order, amount) VALUES (1, null, 'new Pooper Scooper', null, 8, 1);

INSERT INTO step (recipe_id, step_order, step_text) VALUES (1, 1, 'Prepare cake mixes and bake according to directions (any size pans but 13×9 is easiest). Prepare pudding mix and chill until ready to assemble. Crumble sandwich cookies in small batches in food processor, scraping often. Set aside all but about 1/4 cup. To the 1/4 cup cookie crumbs, add a few drops green food coloring and mix using fingers until an even green color.');
INSERT INTO step (recipe_id, step_order, step_text) VALUES (1, 2, 'When cakes are cooled to room temperature, crumble into the new litter box. Toss with half the remaining cookie crumbs and the chilled pudding. Mix in just enough of the pudding to moisten it.');
INSERT INTO step (recipe_id, step_order, step_text) VALUES (1, 3, 'You don’t want it soggy. Combine gently.');
INSERT INTO step (recipe_id, step_order, step_text) VALUES (1, 4, 'Put three unwrapped Tootsie rolls in a microwave safe dish and heat until soft and pliable. Shape ends so they are no longer blunt, curving slightly. Repeat with 3 more Tootsie rolls and bury in mixture. Sprinkle the other half of cookie crumbs over top.');
INSERT INTO step (recipe_id, step_order, step_text) VALUES (1, 5, 'Scatter the green cookie crumbs lightly over the top. (This is supposed to look like the chlorophyll in kitty litter.) Heat 3 Tootsie Rools in the microwave until almost melted. Scrape them on top of the cake. Sprinkle with cookie crumbs. Spread remaining Tootsie Rolls over the top. Take one and heat until pliable. Hang it over the side of the kitty litter box, sprinkling it lightly with cookie crumbs. Serve with new pooper scooper.');

INSERT INTO recipe_category (recipe_id, category_id) VALUES (1, 4);
INSERT INTO recipe_category (recipe_id, category_id) VALUES (1, 8);
INSERT INTO recipe_category (recipe_id, category_id) VALUES (1, 18);
INSERT INTO recipe_category (recipe_id, category_id) VALUES (1, 22);

-- Apple Monsters
INSERT INTO recipe (recipe_name, notes, num_servings, prep_time, cook_time) VALUES ('Apple Monsters', 'These silly apple monsters make for a healthy snack any time of year', 4, '00:20', '00:00');

INSERT INTO ingredient (recipe_id, unit_id, ingredient_name, instruction, ingredient_order, amount) VALUES (2, null, 'green apple', 'cut in quarters', 1, 1);
INSERT INTO ingredient (recipe_id, unit_id, ingredient_name, instruction, ingredient_order, amount) VALUES (2, 2, 'creamy peanut butter', null, 2, 2);
INSERT INTO ingredient (recipe_id, unit_id, ingredient_name, instruction, ingredient_order, amount) VALUES (2, null, 'strawberries', 'cut in half', 3, 2);
INSERT INTO ingredient (recipe_id, unit_id, ingredient_name, instruction, ingredient_order, amount) VALUES (2, null, 'sunflower seeds', null, 4, null);
INSERT INTO ingredient (recipe_id, unit_id, ingredient_name, instruction, ingredient_order, amount) VALUES (2, null, 'edible candy eyes', null, 5, 8);

INSERT INTO step (recipe_id, step_order, step_text) VALUES (2, 1, 'Cut the apple into quarters.');
INSERT INTO step (recipe_id, step_order, step_text) VALUES (2, 2, 'Lay the apple quarters down, and cut a 1 inch wedge in the middle of the peel side of each quarter. Do not cut all the way through because you want each quarter to stay in one piece. It will look like a mouth.');
INSERT INTO step (recipe_id, step_order, step_text) VALUES (2, 3, 'Cut the strawberries in slices lengthwise so that you have 4 pieces.');
INSERT INTO step (recipe_id, step_order, step_text) VALUES (2, 4, 'Spread peanut butter on the bottom of each mouth, inside the cutout.');
INSERT INTO step (recipe_id, step_order, step_text) VALUES (2, 5, 'Place a strawberry inside the cutout to resemble a tongue sticking out.');
INSERT INTO step (recipe_id, step_order, step_text) VALUES (2, 6, 'Poke 6 or 7 sunflower seeds or almond slivers into the top edge of the cut out to resemble teeth.');
INSERT INTO step (recipe_id, step_order, step_text) VALUES (2, 7, 'Serve right away!');

INSERT INTO recipe_category (recipe_id, category_id) VALUES (2, 8);
INSERT INTO recipe_category (recipe_id, category_id) VALUES (2, 18);
INSERT INTO recipe_category (recipe_id, category_id) VALUES (2, 22);
INSERT INTO recipe_category (recipe_id, category_id) VALUES (2, 24);

-- Ice cubes
INSERT INTO recipe (recipe_name, notes, num_servings, prep_time, cook_time) VALUES ('Ice Cubes', 'I''m publishing this recipe because I''m sure that there are other families who have members, who don''t know how or have forgotten how to make ice when the ice tray is empty.', 4, '00:05', '06:00');

INSERT INTO ingredient (recipe_id, unit_id, ingredient_name, instruction, ingredient_order, amount) VALUES (3, 3, 'water', 'approximately', 1, 2);
INSERT INTO ingredient (recipe_id, unit_id, ingredient_name, instruction, ingredient_order, amount) VALUES (3, 2, 'water', 'if needed', 2, 2);

INSERT INTO step (recipe_id, step_order, step_text) VALUES (3, 1, 'Empty the ice cubes that are left in the trays (if there are any left) into the bin.');
INSERT INTO step (recipe_id, step_order, step_text) VALUES (3, 2, 'Take the trays over to the sink and fill them with cold water. (Hot water will freeze faster and more clear).');
INSERT INTO step (recipe_id, step_order, step_text) VALUES (3, 3, 'Place the water filled ice trays back in the freezer.');
INSERT INTO step (recipe_id, step_order, step_text) VALUES (3, 4, 'Replace the ice bin if you had to remove it.');
INSERT INTO step (recipe_id, step_order, step_text) VALUES (3, 5, 'Shut the door to the freezer.');
INSERT INTO step (recipe_id, step_order, step_text) VALUES (3, 6, 'Be sure to leave for around 4-6 hours at least to make sure it is frozen.');
INSERT INTO step (recipe_id, step_order, step_text) VALUES (3, 7, 'If you want to experiment, you can freeze things like fruit infused waters or juices.');

INSERT INTO recipe_category (recipe_id, category_id) VALUES (3, 22);

-- Chocolate Moose
INSERT INTO recipe (recipe_name, notes, num_servings, prep_time, cook_time) VALUES ('Chocolate Moose', 'This nontraditional dessert will require a slightly larger refrigerator. This is for VERY special occasions only — it takes a lot of effort, but the presentation is spectacular!', 4, '23:59', '23:59');

INSERT INTO ingredient (recipe_id, unit_id, ingredient_name, instruction, ingredient_order, amount) VALUES (4, null, 'moose', null, 1, 1);
INSERT INTO ingredient (recipe_id, unit_id, ingredient_name, instruction, ingredient_order, amount) VALUES (4, 5, 'Hershey''s chocolate', null, 2, 40);
INSERT INTO ingredient (recipe_id, unit_id, ingredient_name, instruction, ingredient_order, amount) VALUES (4, 9, 'Cool Whip', null, 3, 17);
INSERT INTO ingredient (recipe_id, unit_id, ingredient_name, instruction, ingredient_order, amount) VALUES (4, null, 'cherry', null, 4, 1);

INSERT INTO step (recipe_id, step_order, step_text) VALUES (4, 1, 'Send spouse to Alaska to capture moose, or have one delivered by UPS.');
INSERT INTO step (recipe_id, step_order, step_text) VALUES (4, 2, 'Meanwhile, melt chocolate in very large double boiler.');
INSERT INTO step (recipe_id, step_order, step_text) VALUES (4, 3, 'Keep warm.');
INSERT INTO step (recipe_id, step_order, step_text) VALUES (4, 4, 'Tie up moose with rope.');
INSERT INTO step (recipe_id, step_order, step_text) VALUES (4, 5, 'Holding the moose by the tail, carefully dip in melted chocolate, covering it completely with a thin coating.');
INSERT INTO step (recipe_id, step_order, step_text) VALUES (4, 6, 'Arrange moose attractively on large platter and refrigerate for 2 days to set chocolate.');
INSERT INTO step (recipe_id, step_order, step_text) VALUES (4, 7, 'Remove rope, wash to remove chocolate, if necessary, and return rope to clothesline.');
INSERT INTO step (recipe_id, step_order, step_text) VALUES (4, 8, 'Garnish chocolate moose with Cool Whip and top with a cherry.');
INSERT INTO step (recipe_id, step_order, step_text) VALUES (4, 9, 'Serve immediately.');
INSERT INTO step (recipe_id, step_order, step_text) VALUES (4, 10, 'Or you could just chew on the rope, which may be tastier.');
INSERT INTO step (recipe_id, step_order, step_text) VALUES (4, 11, 'May be doubled for serving a crowd.');

INSERT INTO recipe_category (recipe_id, category_id) VALUES (4, 10);
