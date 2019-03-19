package controllers;

import models.*;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import views.html.Procress6.editsavefood;
import views.html.Procress6.formsavefood;
import views.html.Procress6.saveFoods;
import views.html.editBreed;
import views.html.formBreed;
import views.html.form_RecipeFoods;
import views.html.plansVac.planVacs;
import views.html.showBreed;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static controllers.allController.data;
import static controllers.allController.main1;

public class saveKKFarmController extends Controller {




    //ข้อมูลบันทึกการให้อาหาร
    public static Form<SaveFoods> myForms = Form.form(SaveFoods.class);
    public static SaveFoods data1;
    public static Recipe data2;

    public static List<SaveFoods> SaveFoodList = new ArrayList<SaveFoods>();
    public static List<Cows> cowsList = new ArrayList<Cows>();
    public static List<Recipe> RecipeList = new ArrayList<Recipe>();
    public static List<Inputfood> ifoodList = new ArrayList<Inputfood>();

    public static Result showsaveFood(){
        SaveFoodList = SaveFoods.list();
        myForms = Form.form(SaveFoods.class);
        cowsList = Cows.showlist();
        RecipeList = Recipe.list();
        ifoodList = Inputfood.showlist();
        return savefoodseach();
    }

    public static Result input() {
        Form<SaveFoods> myForm1 = myForms.bindFromRequest();
        if (myForm1.hasErrors()) {
            return main1(formsavefood.render(myForm1, cowsList));

        } else {
            data1 = myForm1.get();
          int round = myForm1.get().getRound();
//            data2 = Recipe.finder.byId(myForm1.get().getRecip().getNameRecip());
            SaveFoods savefood = SaveFoods.findFood(data1.getCowdata().getCow_id());

            if (savefood != null) {
                flash("datacow", "ข้อมูล โคขุน ที่ท่านเลือกมีการจัดสูตรไว้ก่อนหน้านี้แล้ว");
                return savefoodseach();
            } else {

//                        data2.setAmountCow(update);
//                        Recipe.edit(data2);
//                        Recipe.add(data2);
//                        int total = myForm1.get().getRound()*6;
//                        int total1 =total*6;
//                        data1.setRound(total1);

                        data1 = myForm1.get();
                        data1.setDatesavefood(new Date());
                        SaveFoods.add(data1);


                    }

                }


//            SaveFoods savefood = SaveFoods.findFood(data1.getCowdata().getCow_id());
//            if (savefood != null) {
//                flash("datacow", "ข้อมูล โคขุน ที่ท่านเลือกมีการจัดแผนการฉีดวัคซีนแล้ว");
//                return savefoodseach();
//            } else {
//
//
//                return showsaveFood();
//            }
                return savefoodseach();

        }


    public static Result edit(String id) {
        play.data.Form<SaveFoods> newformpro = myForms.bindFromRequest();
        data1 = SaveFoods.finder.byId(id);
        if (data1 == null) {
            return showsaveFood();
        } else {
            myForms = play.data.Form.form(SaveFoods.class).fill(data1);
            return main1(editsavefood.render(myForms,cowsList));
        }
    }

    public static Result update() {
        play.data.Form<SaveFoods> formupdate = myForms.bindFromRequest();
        if (formupdate.hasErrors()) {
            return main1(editsavefood.render(formupdate,cowsList));
        } else {
            data1=formupdate.get();
            SaveFoods.edit(data1);
            return showsaveFood();
        }
    }

    //ลบ
    public static Result delete(String id) {
        data1 = SaveFoods.finder.byId(id);
        if (data1 != null) {
            data1.delete();
        }
        return showsaveFood();

    }
    public static Result savefoodseach(){
        SaveFoodList = SaveFoods.finder.where().orderBy().desc("datesavefood").findList();//title คือชื่อฟิลด์ที่ต้องการค้นหา
        myForms = Form.form(SaveFoods.class);
        cowsList = Cows.showlist();
        RecipeList = Recipe.list();
        ifoodList = Inputfood.showlist();

        return main1(saveFoods.render(SaveFoodList,myForms,cowsList,RecipeList,ifoodList));
    }
    //แผนการฉีดวัคซีน

}

