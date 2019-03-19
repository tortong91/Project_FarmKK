package controllers;

import models.*;
import play.*;
import play.api.templates.Html;
import play.data.Form;
import play.mvc.*;

import sun.text.resources.cldr.ar.FormatData_ar_MA;
import views.html.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


import play.Play;

import static controllers.allController.main1;

public class Application extends Controller {


    public static String picPath = Play.application().configuration().getString("path_form");


    //database sql ข้อมูลสหกรณ์
    public static Form<cooperatives> myForms = Form.form(cooperatives.class);
    public static cooperatives data1;
    public static List<cooperatives> product3List = new ArrayList<cooperatives>();
    public static Result showdata() {
        product3List = cooperatives.showlist();
        myForms = play.data.Form.form(cooperatives.class);
        return main1(information_cooparetive.render(product3List,myForms));
    }

//    public static Result adddata() {
//        myForms = play.data.Form.form(cooperatives.class);
//        return main1(form_information_cooparetive.render(myForms));
//    }

    public static Result inputdata() {
        Form<cooperatives> myForm = myForms.bindFromRequest();
        Http.MultipartFormData body = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart picture = body.getFile("picture");
        String fileName, contentType;
        if (myForm.hasErrors()) {
            flash("Errorsahakon", "ข้อมูลซ้ำกันในระบบ");
            return main1(form_information_cooparetive.render(myForm));
        } else{
            if (picture != null) {
                contentType = picture.getContentType();
                File file = picture.getFile();
                fileName = picture.getFilename();
                if (contentType.startsWith("images")) {
                    return main1(form_information_cooparetive.render(myForm));
                }
                data1 = myForm.get();
                fileName = data1.getId() + fileName.substring(fileName.lastIndexOf("."));
                file.renameTo(new File(picPath, fileName));
                data1.setPicture(fileName);
                product3List.add(data1);
                cooperatives.adddata(data1);

            }
            return showdata();
        }
        }







    public static Result edit(String id) {
        play.data.Form<cooperatives> newformpro = myForms.bindFromRequest();
        data1 = cooperatives.finder.byId(id);

        if (data1 == null) {
            return showdata();
        } else {
            myForms = play.data.Form.form(cooperatives.class).fill(data1);
            return main1(edit_information_cooparetive.render(myForms));
        }
    }

    public static Result update() {
        play.data.Form<cooperatives> formupdate = myForms.bindFromRequest();
        Http.MultipartFormData body = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart picture = body.getFile("picture");
        if (formupdate.hasErrors()) {
            data1=formupdate.get();

            return main1(edit_information_cooparetive.render(formupdate));
        } else {
            data1=formupdate.get();
            if (picture != null) {
                String fileName = picture.getFilename();
                String extension = fileName.substring(fileName.indexOf("."));
                String realName = data1.getId() + extension;
                File file = picture.getFile();
                File temp = new File("public/images/Pic/" + realName);
                if (temp.exists()) {
                    temp.delete();
                }
                file.renameTo(new File("public/images/Pic/" + realName));
                data1.setPicture(realName);
            }
            cooperatives.edit(data1);
            return showdata();
        }
    }

    //ลบ
    public static Result delet(String id) {
        data1 = cooperatives.finder.byId(id);
        if (data1 != null) {
            File temp = new File("public/images/Pic/" + data1.getPicture());
            temp.delete();
            data1.delete();
        }
        return showdata();
    }


//รายละเอียด

/*
    public static Result details(String id) {
        int i;
        for (i = 0; i < product3List.size(); i++){
            if (product3List.get(i).getId().equals(id)) {
                break;
            }
        }
        return main1(information_cooparetive.render(product3List.get(i));
    }
    /*public static Result ishow2() {

        return ok(Detailss.render();
    }*/


    /*ข้อมูลอาหารสัตว์------*/

    //database sql ข้อมูลอาหารสัตว์
    public static List<Foods> myFormlistfoods = new ArrayList<Foods>();
    public static Form<Foods> myFormsfoods = Form.form(Foods.class);
    public static Foods datafoods;

public static Result showfoods() {
    myFormsfoods = play.data.Form.form(Foods.class);
    myFormlistfoods= Foods.showlist();
    return main1(show_foodsAnimal.render(myFormlistfoods,myFormsfoods));
}

//    public static Result addfoods() {
//        myFormsfoods = play.data.Form.form(Foods.class);
//        return main1(form_animalFoods.render(myFormsfoods));
//    }




    public static Result inputfoods() {
        play.data.Form<Foods> myForm = myFormsfoods.bindFromRequest();
        if (myForm.hasErrors()) {
            flash("Error","Please correct the from below.");
            return main1(form_animalFoods.render(myForm));
        }
        else {


            String typeid = "F0000001";
            int idwood =0;
            int id_wood =0;


            myFormlistfoods = Foods.showlist();



            for (int i = 0; i < myFormlistfoods.size(); i++) {



                typeid = myFormlistfoods.get(i).getId();



                typeid = typeid.substring(2, 8);


                idwood = Integer.parseInt(typeid);

                idwood = idwood+100000;

                if(idwood > id_wood){

                    id_wood = idwood;

                    typeid = Integer.toString(id_wood+1);

                    typeid = "F0" + typeid;

                    typeid = typeid.substring(3, 8);

                    typeid = "F0" +"0"+typeid;

                }





            }



            String tyname = myForm.get().getAnimalfoods_name();
            String tytype = myForm.get().getAnimalfoods_type();

            datafoods = new Foods(typeid,tyname,tytype);

            datafoods.add(datafoods);
           myFormlistfoods = Foods.showlist();

            return main1(show_foodsAnimal.render(myFormlistfoods,myFormsfoods));

        }


//        if (myForm.hasErrors()) {
//            flash("Error", "ข้อมูล Primarykey ซ้ำกันในระบบ");
//            return main1(form_animalFoods.render(myForm));
//
//        } else {
//            datafoods = myForm.get();
//            Foods chk1;
//            chk1 = Foods.finder.byId(datafoods.getId());
//            if (chk1 != null) {
//                flash("Error", "ข้อมูล Primarykey ซ้ำกันในระบบ");
//                return showfoods();
//            } else {
//                datafoods = myForm.get();
//                Foods.add(datafoods);
//                return showfoods();
//            }
//
//        }


        }

    public static Result editfoods(String id) {
        play.data.Form<Foods> newformpro = myFormsfoods.bindFromRequest();
        datafoods = Foods.finder.byId(id);
        if (datafoods == null) {
            return showfoods();
        } else {
            myFormsfoods = Form.form(Foods.class).fill(datafoods);
            return main1(edit_animalFoods.render(myFormsfoods));
        }
    }

    public static Result updatefoods1() {
        play.data.Form<Foods> formupdate = myFormsfoods.bindFromRequest();
        if (formupdate.hasErrors()) {
            return main1(edit_animalFoods.render(formupdate));
        } else {
            datafoods = formupdate.get();
            Foods.edit1(datafoods);
            return showfoods();
        }
    }

    //ลบ
    public static Result deletefoods(String id) {
        datafoods = Foods.finder.byId(id);
        if (datafoods != null) {
            datafoods.delete();
        }
        return showfoods();

    }


    //database sql ข้อมูลตัวแทนจำหน่าย
    public static Form<Agent> myFormsAgent = Form.form(Agent.class);
    public static Agent dataAgen;
    public static List<Agent> agentList = new ArrayList<Agent>();

    public static Result showagent() {
        agentList = Agent.showlist();
        return main1(show_Agent.render(agentList));
    }

    public static Result addagent() {
        myFormsAgent = play.data.Form.form(Agent.class);
        return main1(form_Agent.render(myFormsAgent));
    }

    public static Result inputagent() {
        Form<Agent> myForm = myFormsAgent.bindFromRequest();
        Http.MultipartFormData body = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart picture = body.getFile("picture");
        String fileName, contentType;
        if (myForm.hasErrors()) {
            return main1(form_Agent.render(myForm));
        } else {
            if (picture != null) {
                contentType = picture.getContentType();
                File file = picture.getFile();
                fileName = picture.getFilename();
                if (contentType.startsWith("images")) {
                    return main1(form_Agent.render(myForm));
                }
                dataAgen = myForm.get();
                fileName = dataAgen.getAgent_id() + fileName.substring(fileName.lastIndexOf("."));
                file.renameTo(new File(picPath, fileName));
                dataAgen.setPicture(fileName);
                agentList.add(dataAgen);
                Agent.add(dataAgen);

            }
            return showagent();

        }
    }

    public static Result editagent(String id) {
        play.data.Form<Agent> newformpro = myFormsAgent.bindFromRequest();
        dataAgen = Agent.finder.byId(id);
        if (dataAgen == null) {
            return showagent();
        } else {
            myFormsAgent = play.data.Form.form(Agent.class).fill(dataAgen);
            return main1(edit_Agent.render(myFormsAgent));
        }
    }

    public static Result updateagent() {
        play.data.Form<Agent> formupdate = myFormsAgent.bindFromRequest();
        if (formupdate.hasErrors()) {
            return main1(edit_Agent.render(formupdate));
        } else {
            dataAgen = formupdate.get();
            Agent.edit(dataAgen);
            return showagent();
        }
    }

    //ลบ
    public static Result deletagent(String id) {
        dataAgen = Agent.finder.byId(id);
        if (dataAgen != null) {
            dataAgen.delete();
        }
        return showagent();
    }


//รายละเอียด

/*
    public static Result details(String id) {
        int i;
        for (i = 0; i < product3List.size(); i++){
            if (product3List.get(i).getId().equals(id)) {
                break;
            }
        }
        return ok(information_cooparetive.render(product3List.get(i));
    }
    /*public static Result ishow2() {

        return ok(Detailss.render();
    }*/




//รายละเอียด

/*
    public static Result details(String id) {
        int i;
        for (i = 0; i < product3List.size(); i++){
            if (product3List.get(i).getId().equals(id)) {
                break;
            }
        }
        return ok(information_cooparetive.render(product3List.get(i));
    }
    /*public static Result ishow2() {

        return ok(Detailss.render();
    }*/


}
