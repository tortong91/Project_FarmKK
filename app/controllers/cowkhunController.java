package controllers;

import models.*;
import org.h2.table.Plan;
import play.Play;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import views.html.*;
import views.html.details.detailsCow;
import views.html.details.detailsCowPrint;
import views.html.sells.sellCow;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static controllers.allController.main1;

public class cowkhunController extends Controller {

    public static String picPath = Play.application().configuration().getString("path_formCow");


    //ข้อมูลวัคซีน
    public static Form<Cows> myForms = Form.form(Cows.class);
    public static Cows data1;
    public static breeds data2;
    public static List<Cows> cowList = new ArrayList<Cows>();
    public static List<breeds> breedList = new ArrayList<breeds>();

    public static Result showcow() {
        cowList = Cows.showlist();
        myForms = Form.form(Cows.class);
        breedList = breeds.showlist();
        return main1(showCow.render(cowList,myForms,breedList));
    }
  public static Result showcowdata() {
        cowList = Cows.showlist();
      breedList = breeds.showlist();
        return main1(showCowdata.render(cowList,breedList));
    }

//    public static Result add() {
//        breedList = breeds.showlist();
//        myForms = Form.form(Cows.class);
//        return main1(form_Cow.render(myForms, breedList));
//    }

    public static Result input() {
        Form<Cows> myForm = myForms.bindFromRequest();
        Http.MultipartFormData body = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart picture = body.getFile("picture");
        String fileName, contentType;
        if (myForm.hasErrors()) {
            flash("Errorcow", "ข้อมูลซ้ำกันในระบบ");
            return main1(form_Cow.render(myForm, breedList));
        } else {

//            Cows chk1;
//            chk1 = Cows.finder.byId(data1.getCow_id());
//            if (chk1 != null) {
//                flash("Errorcow", "ข้อมูล Primarykey ซ้ำกันในระบบ");
//                return showcow();
//            }else {

        data1 = myForm.get();
        if (picture != null) {
            contentType = picture.getContentType();
            File file = picture.getFile();
            fileName = picture.getFilename();
            if (contentType.startsWith("images")) {
                return main1(form_Cow.render(myForm, breedList));
            } else {
                data1 = myForm.get();
                fileName = data1.getCow_id() + fileName.substring(fileName.lastIndexOf("."));
                file.renameTo(new File(picPath, fileName));
                data1.setPicture(fileName);
                data1.setDate(new Date());
                Cows.add(data1);

            }
            return showcow();
        }

    }


            String typeid = "WT000001";
            int idwood = 0;
            int id_wood = 0;


            cowList = Cows.showlist();


            for (int i = 0; i < cowList.size(); i++) {


                typeid = cowList.get(i).getCow_id();


                typeid = typeid.substring(2, 8);


                idwood = Integer.parseInt(typeid);

                idwood = idwood + 100000;

                if (idwood > id_wood) {

                    id_wood = idwood;

                    typeid = Integer.toString(id_wood + 1);

                    typeid = "WT" + typeid;

                    typeid = typeid.substring(3, 8);

                    typeid = "WT" + "0" + typeid;

                }


            }


            String k = myForm.get().getStatus();
            String h = myForm.get().getPrice();
            String j = myForm.get().getSex();
            String a = myForm.get().getColor();
            Date z = myForm.get().getDate();
            String s = myForm.get().getAge();
            String g = myForm.get().getPicture();
            String l = myForm.get().getWeight();
            String f = myForm.get().getHeight();

            data1 = new Cows(typeid, data2, k, h, j, a, z, s, g, l, f);

            data1.add(data1);
            cowList = data1.showlist();

            return main1(showCow.render(cowList, myForms, breedList));



    }


    public static Result edit(String id) {
        Form<Cows> newformpro = myForms.bindFromRequest();
        data1 = Cows.finder.byId(id);
        session("cowId", data1.getCow_id());
        if (data1 == null) {
            return showcow();
        } else {
            breedList = breeds.showlist();
            myForms = Form.form(Cows.class).fill(data1);
            return main1(editCow.render(myForms, breedList));
        }
    }

    public static Result update() {
        Form<Cows> formupdate = myForms.bindFromRequest();
        Http.MultipartFormData body = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart picture = body.getFile("picture");
        data1 = Cows.finder.byId(session("cowId"));
        if (formupdate.hasErrors()) {
            return main1(editCow.render(formupdate, breedList));
        } else {
            data1=formupdate.get();
            if (picture != null) {
                String fileName = picture.getFilename();
                String extension = fileName.substring(fileName.indexOf("."));
                String realName = data1.getCow_id() + extension;
                File file = picture.getFile();
                File temp = new File("public/images/Piccow/" + realName);
                if (temp.exists()) {
                    temp.delete();
                }
                file.renameTo(new File("public/images/Piccow/" + realName));
                data1.setPicture(realName);
            }
//            Cows cowdata = new Cows(data1.getBreed(),"sale",formupdate.get().getPrice(), session("cowId"), formupdate.get().getSex(), formupdate.get().getColor(), formupdate.get().getDate(), formupdate.get().getAge(), formupdate.get().getPicture(), formupdate.get().getWeight(), formupdate.get().getHeight());
            Cows.edit(data1);
            return showcow();
        }
    }

    //ลบ
    public static Result delete(String id) {
        data1 = Cows.finder.byId(id);
        if (data1 != null) {
            File temp = new File("public/images/Piccow/" + data1.getPicture());
            temp.delete();
            data1.delete();
        }
        return showcow();
    }

    public static Result detailsCow(String id) {
        int i;
        for (i = 0; i < cowList.size(); i++) {
            if (cowList.get(i).getCow_id().equals(id)) {
                break;
            }
        }
        return ok(detailsCow.render(cowList.get(i)));
    }
    public static Result detailsCowPrint(String id) {
        int i;
        for (i = 0; i < cowList.size(); i++) {
            if (cowList.get(i).getCow_id().equals(id)) {
                break;
            }
        }
        return ok(detailsCowPrint.render(cowList.get(i)));
    }
    public static Result two(){
        DynamicForm myForms = Form.form().bindFromRequest();
        String se = myForms.get("search"); //"sh" สีเขียวคือชื่อที่ตั้งตรง inputtext
        breedList = breeds.showlist();
        cowList = Cows.finder.where().like("cow_id",'%'+se+'%').findList();//title คือชื่อฟิลด์ที่ต้องการค้นหา
        return main1(showCowdata.render(cowList,breedList));
    }

    public static Result showcow1() {
        cowList = Cows.showlist();
        return main1(showCow1.render(cowList));
    }

    public static Result edit1(String id) {
        Form<Cows> newformpro = myForms.bindFromRequest();
        data1 = Cows.finder.byId(id);
        session("cowId", data1.getCow_id());
        if (data1 == null) {
            return showcow1();
        } else {
            breedList = breeds.showlist();
            myForms = Form.form(Cows.class).fill(data1);
            return main1(formCow1.render(myForms, breedList));
        }
    }

    public static Result update1() {
        Form<Cows> formupdate = myForms.bindFromRequest();
        data1 = Cows.finder.byId(session("cowId"));
        if (formupdate.hasErrors()) {
            return main1(formCow1.render(formupdate, breedList));
        } else {
//           Cows cowdata = new Cows(data1.getBreed(), "sale",formupdate.get().getPrice(), session("cowId"), formupdate.get().getSex(), formupdate.get().getColor(), formupdate.get().getDate(), formupdate.get().getAge(), formupdate.get().getPicture(), formupdate.get().getWeight(), formupdate.get().getHeight());
//            Cows.edit(cowdata);
            Cows.edit(data1);
            return showcow1();
        }
    }

//    public static play.mvc.Result check (String id ){
//        cowList = Cows.brandList(id);
//        if(cowList != null){
//            return main1(showCow.render(cowList));
//        }else {
//            cowList = Cows.showlist();
//            return main1(showCow.render(cowList));
//        }
//    }
}
