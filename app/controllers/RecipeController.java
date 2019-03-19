package controllers;

import models.DetailRecip;
import models.Foods;
import models.Inputfood;
import models.Recipe;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http;
import views.html.form_RecipeFoods;
import views.html.show_Recipe;
import views.html.testValue;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static controllers.allController.main1;

public class RecipeController extends Controller {

    //Join Table
    public static List<Inputfood> foodsAnimalList = new ArrayList<Inputfood>();
    public static List<Recipe> RecipeList = new ArrayList<Recipe>();
    public static List<Foods> foodList = new ArrayList<Foods>();
    public static List<DetailRecip> dList = new ArrayList<DetailRecip>();
    public static Form<Recipe> picture = Form.form(Recipe.class);
    public static Form<Recipe> recipeForm = Form.form(Recipe.class);
    public static Recipe data;
    public static DetailRecip data1;
    public static List<DetailRecip> detailRecipList = new ArrayList<DetailRecip>();
    public static Inputfood inputfood;
    public static HashMap newmap = new HashMap<String,String>();


    public static play.mvc.Result showRecipe() {
        RecipeList = Recipe.list();
        recipeForm = Form.form(Recipe.class);
        foodsAnimalList = Inputfood.showlist();


        return main1(show_Recipe.render(RecipeList,recipeForm, foodsAnimalList));
    }

//    public static play.mvc.Result add() {
//        foodsAnimalList = Inputfood.showlist();
//        recipeForm = Form.form(Recipe.class);
//        return main1(form_RecipeFoods.render(recipeForm, foodsAnimalList));
//    }
    public static String amountFood;
    public static play.mvc.Result input() {
        foodsAnimalList = Inputfood.showlist();
        DynamicForm newMapping = Form.form().bindFromRequest();
        int i=0;
        while (newMapping.field("id["+i+"]")!= null){
            String id = newMapping.field("id["+i+"]").value() ;
            String value = newMapping.field("chk["+i+"]").value();
            newmap.put(id,value);
            i++;
            if  (i == foodsAnimalList.size() ){
             break;
            }

        }

        String nameRe = newMapping.get("rid");
        String rdio = newMapping.get("rdio");
        int  amount  =  Integer.parseInt(newMapping.get("amount")) ;
        Recipe recipe = new Recipe();
        recipe.setNameRecip(nameRe);
        recipe.setAmountCow(amount);
        recipe.setDateRecipe(new Date());
        recipe.setDateTimeRecipe(rdio);
        Recipe.add(recipe);
        int y;
        for (y = 0 ; y<foodsAnimalList.size();y++){
            foodsAnimalList = Inputfood.showlist();
            String id = foodsAnimalList.get(y).getId_ifoods();
            amountFood = (String) newmap.get(id);
            if (amountFood == "") {
                amountFood = "0";
            }
            inputfood = Inputfood.finder.byId(id);
            if (amountFood != null){
                DetailRecip dataDetail = new DetailRecip();
                dataDetail.setAmount(Integer.parseInt(amountFood));

                inputfood = Inputfood.finder.byId(id);
                dataDetail.setInputfoods(inputfood);
                data = Recipe.finder.byId(nameRe);
                dataDetail.setRecipe(data);
                int total = inputfood.getTotal();
                int blanc  = total - Integer.parseInt(amountFood);
                inputfood.setTotal(blanc);
                inputfood.setId_ifoods(id);
                Inputfood.edit(inputfood);
                DetailRecip.add(dataDetail);
            }
        }
        return showRecipe();
    }

    //ลบ
    public static play.mvc.Result delete(String id) {
        data = Recipe.finder.byId(id);
        if (data != null) {
            Recipe.delete(data);

        }
        RecipeList = Recipe.list();
        return showRecipe();
    }


}
