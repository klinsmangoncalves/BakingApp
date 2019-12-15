package br.com.kmg.bakingapp.model;

import java.io.Serializable;
import java.util.List;

public class Receipt implements Serializable {
    private Integer id;
    private String name;
    private List<Ingredient> ingredients;
    private List<ReceiptStep> steps;
    private Integer servings;
    private String image;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<ReceiptStep> getSteps() {
        return steps;
    }

    public void setSteps(List<ReceiptStep> steps) {
        this.steps = steps;
    }

    public Integer getServings() {
        return servings;
    }

    public void setServings(Integer servings) {
        this.servings = servings;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
