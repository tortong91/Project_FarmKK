package controllers;


import models.Vacs;
import models.breeds;
import play.Play;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import views.html.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static controllers.allController.main1;

public class breedController extends Controller {
    public static String picPath = Play.application().configuration().getString("path_form");


    //ข้อมูลวัคซีน
    public static Form<breeds> myForms = Form.form(breeds.class);
    public static breeds data1;
    public static List<breeds> breedList = new ArrayList<breeds>();

    public static Result showBreeds() {
        breedList = breeds.showlist();
        myForms = Form.form(breeds.class);
        return main1(showBreed.render(breedList,myForms));
    }

//    public static Result add() {
//        myForms = Form.form(breeds.class);
//        return main1(formBreed.render(myForms));
//    }

    public static play.mvc.Result input() {

        Form<breeds> myForm1 = myForms.bindFromRequest();
        Http.MultipartFormData body = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart picture = body.getFile("picture");
        String fileName, contentType;
//        if (myForm1.hasErrors()) {
//            flash("Errorbreeds", "ข้อมูลซ้ำกันในระบบ");
//            return main1(formBreed.render(myForm1));
//
//        } else {
//            data1 = myForm1.get();
//            breeds chk1;
//            chk1 = breeds.finder.byId(data1.getId());
//            if (chk1 != null) {
//                flash("Errorbreeds", "ข้อมูล Primarykey ซ้ำกันในระบบ");
//                return showBreeds();
//            } else {
//                data1 = myForm1.get();
//                breeds.add(data1);
//            }
//            return showBreeds();
//        }
        if (myForm1.hasErrors()) {
            flash("Error","Please correct the from below.");
            return main1(formBreed.render(myForm1));
        }
        else {


            String typeid = "WT000001";
            int idwood =0;
            int id_wood =0;


            breedList = breeds.showlist();



            for (int i = 0; i < breedList.size(); i++) {



                typeid = breedList.get(i).getId();



                typeid = typeid.substring(2, 8);


                idwood = Integer.parseInt(typeid);

                idwood = idwood+100000;

                if(idwood > id_wood){

                    id_wood = idwood;

                    typeid = Integer.toString(id_wood+1);

                    typeid = "WT" + typeid;

                    typeid = typeid.substring(3, 8);

                    typeid = "WT" +"0"+typeid;

                }





            }



            String tyname = myForm1.get().getName();

         data1=new breeds(typeid,tyname);

            data1.add(data1);
            breedList = data1.showlist();

            return main1(showBreed.render(breedList,myForms));

        }
    }

    public static Result edit(String id) {
        play.data.Form<breeds> newformpro = myForms.bindFromRequest();
        data1 = breeds.finder.byId(id);
        if (data1 == null) {
            return showBreeds();
        } else {
            myForms = play.data.Form.form(breeds.class).fill(data1);
            return main1(editBreed.render(myForms));
        }


    }

    public static Result update() {
        play.data.Form<breeds> formupdate = myForms.bindFromRequest();
        if (formupdate.hasErrors()) {
            return main1(editBreed.render(formupdate));
        } else {
            data1=formupdate.get();
            breeds.edit(data1);
            return showBreeds();
        }
    }

    //ลบ
    public static Result delete(String id) {
        data1 = breeds.finder.byId(id);
        if (data1 != null) {
            data1.delete();
        }
        return showBreeds();

    }


}