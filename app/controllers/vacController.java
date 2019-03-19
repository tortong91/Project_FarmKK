package controllers;

import models.*;
import play.Play;
import play.data.DynamicForm;
import play.data.Form;
import play.db.ebean.Model;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import views.html.*;
import views.html.details.detailVac;
import views.html.plansVac.editPlanVac;
import views.html.plansVac.formplanVacs;
import views.html.plansVac.planVacs;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static controllers.allController.main1;


public class vacController extends Controller {
    public static String picPath = Play.application().configuration().getString("path_Vac");


    //ข้อมูลวัคซีน
    public static Form<Vacs> myForms = Form.form(Vacs.class);
    public static Vacs data1;
    public static List<Vacs> vacList = new ArrayList<Vacs>();
//    public static Result numseach(){
//
//        vacList = Vacs.finder.where().orderBy().desc("id").findList();//title คือชื่อฟิลด์ที่ต้องการค้นหา
//        myForms = Form.form(Vacs.class);
//        return main1(showVac.render(vacList,myForms));
//    }
    public static Result showvac() {
        vacList = Vacs.showlist();
        myForms = Form.form(Vacs.class);
        return main1(showVac.render(vacList,myForms));
    }

//    public static Result add() {
//        myForms = Form.form(Vacs.class);
//        return main1(formVac.render(myForms));
//    }

    public static Result input() {
        Form<Vacs> myForm = myForms.bindFromRequest();
        Http.MultipartFormData body = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart picture = body.getFile("picture");
        String fileName, contentType;
        if (myForm.hasErrors()) {
            flash("Error", "Please correct the from below.");
            return main1(formVac.render(myForm));
        } else {


            String typeid = "VC000001";
            int idwood = 0;
            int id_wood = 0;


            vacList = Vacs.showlist();


            for (int i = 0; i < vacList.size(); i++) {


                typeid = vacList.get(i).getId();


                typeid = typeid.substring(2, 8);


                idwood = Integer.parseInt(typeid);

                idwood = idwood + 100000;

                if (idwood > id_wood) {

                    id_wood = idwood;

                    typeid = Integer.toString(id_wood + 1);

                    typeid = "VC" + typeid;

                    typeid = typeid.substring(3, 8);

                    typeid = "Vc" + "0" + typeid;

                }


            }


            String a = myForm.get().getName();
            String b = myForm.get().getPr();
            String c = myForm.get().getPicture();

            data1 = new Vacs(typeid, a, b, c);



            if (picture != null) {
                contentType = picture.getContentType();
                File file = picture.getFile();
                fileName = picture.getFilename();
                if (contentType.startsWith("images")) {
                    return main1(showVac.render(vacList, myForms));
                }


                fileName = data1.getId() + fileName.substring(fileName.lastIndexOf("."));
                file.renameTo(new File(picPath, fileName));
                data1.setPicture(fileName);

                data1.add(data1);
                vacList = Vacs.showlist();


            }
            return showvac();
        }
    }



    public static Result edit(String id) {
        play.data.Form<Vacs> newformpro = myForms.bindFromRequest();
        data1 = Vacs.finder.byId(id);
        session("vacId",data1.getId());
        if (data1 == null) {
            return showvac();
        } else {
            myForms = play.data.Form.form(Vacs.class).fill(data1);
            return main1(editVac.render(myForms));
        }
    }

    public static Result update() {
        play.data.Form<Vacs> formupdate = myForms.bindFromRequest();
        Http.MultipartFormData body = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart picture = body.getFile("picture");

        if (formupdate.hasErrors()) {
            return main1(editVac.render(formupdate));
        } else {
            data1=formupdate.get();
            if (picture != null) {
                String fileName = picture.getFilename();
                String extension = fileName.substring(fileName.indexOf("."));
                String realName = data1.getId() + extension;
                File file = picture.getFile();
                File temp = new File("public/images/PicVac/" + realName);
                if (temp.exists()) {
                    temp.delete();
                }
                file.renameTo(new File("public/images/PicVac/" + realName));
                data1.setPicture(realName);
            }

            Vacs dataEdit = new Vacs(session("vacId"),formupdate.get().getPr(),formupdate.get().getName(),formupdate.get().getPicture());
            Vacs.edit(dataEdit);
            return showvac();
        }
    }

    //ลบ
    public static Result delete(String id) {
        data1 = Vacs.finder.byId(id);
        if (data1 != null) {
            File temp = new File("public/images/PicVac/" + data1.getPicture());
            temp.delete();

            data1.delete();
        }
        return showvac();
    }




    //แผนการฉีดวัคซีน

    //ข้อมูลวัคซีน
    public static Form<PlanVacs> myFormsplan = Form.form(PlanVacs.class);
    public static PlanVacs data2;
    public static Cows data3;
    public static List<PlanVacs> PlanvacList = new ArrayList<PlanVacs>();
    public static List<Vacs> vacList1 = new ArrayList<Vacs>();
    public static List<Cows> cowList1 = new ArrayList<Cows>();

    public static Result showPlanvac() {
        PlanvacList = PlanVacs.showlist();
        myFormsplan = Form.form(PlanVacs.class);
        vacList1 = Vacs.showlist();
        cowList1 = Cows.showlist();
        return main1(planVacs.render(PlanvacList,myFormsplan,vacList1,cowList1));
    }

//    public static Result add() {
//        myForms = Form.form(Vacs.class);
//        return main1(formVac.render(myForms));
//    }

    public static Result inputPlan() {
        Form<PlanVacs> myForm1 = myFormsplan.bindFromRequest();

        if (myForm1.hasErrors()) {

            flash("error", "ข้อมูล โคขุน ที่ท่านเลือกซ้ำกัน");
            return main1(formplanVacs.render(myForm1, vacList1, cowList1));
        } else {
            /*
            PlanVacs chk2;
            data2 = myForm1.get();
            Cows testcow = data2.getCowLst();
            chk2 = PlanVacs.findCow(testcow);
            PlanVacs chk1;
            chk1 = PlanVacs.finder.byId(data2.getCowLst().getCow_id());
            if (chk2 != null) {
                flash("error", "ข้อมูล โคขุน ที่ท่านเลือกซ้ำกัน");
                return showPlanvac();
            }else {
                data2 = myForm1.get();
                PlanvacList.add(data2);
                PlanVacs.add(data2);
            }
            return showPlanvac();
*/
            data2 = myForm1.get();
            PlanVacs datacow = PlanVacs.findCow(data2.getCowLst().getCow_id());
            if  (datacow != null){
                flash("datacow","ข้อมูล โคขุน ที่ท่านเลือกมีการจัดแผนการฉีดวัคซีนแล้ว");
                return numseach();
            }else{
                data2.setPvacdate(new Date());
                PlanVacs.add(data2);
                return numseach();
            }


        }

    }
    public static Result editPlan(String id) {
        play.data.Form<PlanVacs> newformpro = myFormsplan.bindFromRequest();
        data2 = PlanVacs.finder.byId(id);
        session("vacplanId",data2.getId());
//
        if (data2 == null) {
            return showPlanvac();
        } else {
            myFormsplan = play.data.Form.form(PlanVacs.class).fill(data2);
            return main1(editPlanVac.render(myFormsplan,vacList1,cowList1));
        }
    }

    public static Result updatePlan() {
        play.data.Form<PlanVacs> formupdate1 = myFormsplan.bindFromRequest();
        if (formupdate1.hasErrors()) {
            return main1(editPlanVac.render(formupdate1,vacList1,cowList1));
        } else {
          PlanVacs Edit = new PlanVacs(session("vacplanId"),formupdate1.get().getPvacdate(),formupdate1.get().getPdate(),formupdate1.get().getPdate1(),data2.getCowLst(),data2.getVac(),data2.getVacfinaly(),"0");
//          data2 = formupdate1.get();
            PlanVacs.edit(Edit);
            return showPlanvac();
        }
    }

    //ลบ
    public static Result deletePlan(String id) {
        data2 =PlanVacs.finder.byId(id);
        if (data2 != null) {
            data2.delete();
        }
        return showPlanvac();
    }
    public static Result numseach(){
        PlanvacList = PlanVacs.finder.where().orderBy().desc("pvacdate").findList();//title คือชื่อฟิลด์ที่ต้องการค้นหา
        myFormsplan = Form.form(PlanVacs.class);
        vacList1 = Vacs.showlist();
        cowList1 = Cows.showlist();
        return main1(planVacs.render(PlanvacList,myFormsplan,vacList1,cowList1));
    }
}
