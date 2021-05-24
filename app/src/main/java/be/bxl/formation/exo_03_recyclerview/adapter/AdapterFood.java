    package be.bxl.formation.exo_03_recyclerview.adapter;

    import android.graphics.Color;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.TextView;

    import androidx.annotation.ColorInt;
    import androidx.annotation.NonNull;
    import androidx.cardview.widget.CardView;
    import androidx.recyclerview.widget.RecyclerView;

    import java.util.ArrayList;
    import java.util.List;

    import be.bxl.formation.exo_03_recyclerview.R;
    import be.bxl.formation.exo_03_recyclerview.models.Food;

    public class AdapterFood extends RecyclerView.Adapter<AdapterFood.ViewHolder> {
    private List<Food> foodData;

    //region Création du type ViewHolder
    // -> Permet de définir les interactions possible
    //    sur les vues contenue par le RecyclerView

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name, calorie;
        private CardView barreColor;

        public ViewHolder(View v) {
            super(v);

            // Liaison avec le layout
            name = v.findViewById(R.id.item_food_name);
            calorie = v.findViewById(R.id.item_food_calory);
            barreColor=v.findViewById(R.id.item_cv_color);
        }

        public TextView getName() {
            return name;
        }

        public TextView getCalorie() {
            return calorie;
        }

        public CardView getBarreColor() {
            return barreColor;
        }
    }

        public AdapterFood(List<Food> foodData) {
            this.foodData = foodData;
        }

        // Méthode qui permet de créer les vues contenue dans la liste
        // -> Utiliser par le LayoutManager
        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            // Création de la vue sur base du layout qu'on a créer « item_person.xml »
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food, parent, false);

            // Renvoie la vue créée encapsuler dans un ViewHolder
            return new ViewHolder(v);
        }

        // Méthode qui permet de mettre à jours la vue avec les données
        // -> Utiliser par le LayoutManager
        @Override
        public void onBindViewHolder(@NonNull AdapterFood.ViewHolder holder, int position) {
            // Récuperation des données a utiliser
            Food target = foodData.get(position);


            // Mise a jours de la vue via le ViewHolder
            holder.getName().setText(target.getName());
            holder.getCalorie().setText(Double.toString(target.getCalory()));
            Food.Category category=foodData.get(position).getCategory();

            switch(category){
                case MEAT:holder.getBarreColor().setCardBackgroundColor(Color.RED);break;
                case FRUIT: holder.getBarreColor().setCardBackgroundColor(Color.MAGENTA);break;
                case VEGETABLE:holder.getBarreColor().setCardBackgroundColor(Color.GREEN);break;
                case OTHER:holder.getBarreColor().setCardBackgroundColor(Color.YELLOW);break;
            }

        }

        // Méthode qui permet d'obtenir le nombre d'element dans la liste
        // -> Utiliser par le LayoutManager
        @Override
        public int getItemCount() {
            return foodData.size();
        }
    }

