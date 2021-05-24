    package be.bxl.formation.exo_03_recyclerview;

    import androidx.appcompat.app.AppCompatActivity;
    import androidx.recyclerview.widget.GridLayoutManager;
    import androidx.recyclerview.widget.LinearLayoutManager;
    import androidx.recyclerview.widget.RecyclerView;

    import android.os.Bundle;
    import android.util.Log;
    import android.widget.ArrayAdapter;
    import android.widget.Button;
    import android.widget.EditText;
    import android.widget.Spinner;
    import android.widget.Toast;

    import java.util.ArrayList;
    import java.util.List;

    import be.bxl.formation.exo_03_recyclerview.adapter.AdapterFood;
    import be.bxl.formation.exo_03_recyclerview.models.Food;

    public class MainActivity extends AppCompatActivity {

        private List<Food> foods;

        private EditText etName, etCalory;
        private Spinner spCategory;
        private Button btnAdd;
        private RecyclerView rvFoods;
        private AdapterFood adapterFood;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            // Initialisation de la liste
            foods = new ArrayList<>();

            // Liaison avec le layoute
            etName = findViewById(R.id.et_main_food_name);
            etCalory = findViewById(R.id.et_main_food_calory);
            spCategory = findViewById(R.id.sp_main_food_type);
            btnAdd = findViewById(R.id.btn_main_add_food);
            rvFoods = findViewById(R.id.rv_main_foods);

            // Définition du Spinner
            List<String> categoryChoices = new ArrayList<>();
            categoryChoices.add(getString(R.string.choice_category_vegetable));
            categoryChoices.add(getString(R.string.choice_category_meat));
            categoryChoices.add(getString(R.string.choice_category_fruit));
            categoryChoices.add("Autre");

            ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(
                    getApplicationContext(),
                    android.R.layout.simple_spinner_item,
                    android.R.id.text1,
                    categoryChoices
            );

            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spCategory.setAdapter(spinnerAdapter);

            // TODO Ajouter le comportement du bouton (-> Ajouter un aliment dans la liste)
            btnAdd.setOnClickListener( v-> {
               ajouterAliment();
            });

            rvFoods.setHasFixedSize(false);

            // Définir du type de layout que le RecyclerView utilise (Linear/Grid/StaggeredGrid)
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),2);
            rvFoods.setLayoutManager(layoutManager);
            adapterFood=new AdapterFood(foods);
            rvFoods.setAdapter(adapterFood);


        }
        public void ajouterAliment() {
            Food oneFood = new Food(etName.getText().toString(), Integer.parseInt(etCalory.getText().toString()),
                    findCategory(spCategory.getSelectedItem().toString()));
            foods.add(oneFood);
            adapterFood.notifyDataSetChanged();
            Toast.makeText(getApplicationContext(), "Aliment ajouté", Toast.LENGTH_LONG).show();
        }
        public Food.Category findCategory(String category) {
            Food.Category resultat;
            switch (category) {
                case "Légume":
                    resultat = Food.Category.VEGETABLE;
                    break;
                case "Viande":
                    resultat = Food.Category.MEAT;
                    break;
                case "Fruit":
                    resultat = Food.Category.FRUIT;
                    break;
                default:
                        resultat=Food.Category.OTHER;
            }
            return resultat;
        }



            // TODO Configurer le RecyclerView
            // TODO Créer l'adapter customiser (Aliment avec la CardView)
        }
