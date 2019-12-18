package br.com.kmg.bakingapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.kmg.bakingapp.R;
import br.com.kmg.bakingapp.model.Ingredient;

public class IngredientAdapter  extends RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder>{

    private List<Ingredient> ingredients;

    public IngredientAdapter(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @NonNull
    @Override
    public IngredientAdapter.IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.ingredient_item, viewGroup, false);
        return new IngredientAdapter.IngredientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientAdapter.IngredientViewHolder ingredientViewHolder, int i) {
        Ingredient ing = ingredients.get(i);
        String ingredientStr = ingredientViewHolder.ingredientTitle.getResources().getString(R.string.ingredient_portion, ing.getIngredient(), ing.getQuantity(), ing.getMeasure());
        ingredientViewHolder.ingredientTitle.setText(ingredientStr);
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    class IngredientViewHolder extends RecyclerView.ViewHolder{

        final TextView ingredientTitle;

        IngredientViewHolder(@NonNull View itemView) {
            super(itemView);
            ingredientTitle = itemView.findViewById(R.id.tv_ingredient_item);
        }
    }
}

