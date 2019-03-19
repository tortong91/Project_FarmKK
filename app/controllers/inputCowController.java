package controllers;

import models.*;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

import java.util.ArrayList;
import java.util.List;

import static controllers.allController.main1;

public class inputCowController extends Controller {
    //Join Table
    public static List<InputCow> listCow = new ArrayList<InputCow>();
    public static List<Cows> listinputCow = new ArrayList<Cows>();
    public static Form<InputCow> formCow = Form.form(InputCow.class);
    public static InputCow data;



    public static play.mvc.Result show() {
        listCow = InputCow.showlist();
        return main1(showInputCow.render(listCow));
    }

    public static play.mvc.Result add() {
        listinputCow=Cows.showlist();
        formCow = Form.form(InputCow.class);
        return main1(formInputCow.render(formCow,listinputCow));
    }

    public static play.mvc.Result input() {
        Form<InputCow> newadd = formCow.bindFromRequest();
        if (newadd.hasErrors()) {
            return main1(formInputCow.render(newadd,listinputCow));
        } else {
            data = newadd.get();
            InputCow.add(data);
            return add();
        }
    }
    public static Result edit(String id) {
        play.data.Form<InputCow> newformpro = formCow.bindFromRequest();
        data = InputCow.finder.byId(id);
        if (data == null) {
            return show();
        } else {
            listinputCow=Cows.showlist();
            formCow = play.data.Form.form(InputCow.class).fill(data);
            return main1(editInputCow.render(formCow,listinputCow));
        }
    }

    public static Result update() {
        play.data.Form<InputCow> formupdate = formCow.bindFromRequest();
        if (formupdate.hasErrors()) {
            return main1(editInputCow.render(formupdate,listinputCow));
        } else {
            data=formupdate.get();
            InputCow.edit(data);
            return show();
        }
    }


    //ลบ
    public static play.mvc.Result delete(String id) {
        data = InputCow.finder.byId(id);
        if (data != null) {
            InputCow.delete(data);

        }
        listCow = InputCow.showlist();
        return show();
    }



}
