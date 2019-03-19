package controllers;

import models.breeds;
import models.farm;
import play.Play;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import views.html.edit_Farm;
import views.html.form_Farm;
import views.html.show_Farm;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static controllers.allController.data;
import static controllers.allController.main1;

public class farmController extends Controller {
    public static String picPath = Play.application().configuration().getString("path_farm");

    //database sql ข้อมูลฟาร์ม
    public static Form<farm> myFormsFarm = Form.form(farm.class);
    public static farm dataFarm;
    public static List<farm> farmList = new ArrayList<farm>();
    public static Result showfarm() {
        farmList = farm.showlist();
        return main1(show_Farm.render(farmList));
    }

    public static Result addfarm() {
        myFormsFarm = play.data.Form.form(farm.class);
        return main1(form_Farm.render(myFormsFarm));
    }

    public static Result inputfarm() {
        Form<farm> myForm = myFormsFarm.bindFromRequest();
        Http.MultipartFormData body = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart picture = body.getFile("picture");
        String fileName, contentType;
        if (myForm.hasErrors()) {
            return main1(form_Farm.render(myForm));
        } else {
            if (picture != null) {
                contentType = picture.getContentType();
                File file = picture.getFile();
                fileName = picture.getFilename();
                if (contentType.startsWith("images")) {
                    return main1(form_Farm.render(myForm));
                }
                dataFarm = myForm.get();
                fileName = dataFarm.getFarm_id() + fileName.substring(fileName.lastIndexOf("."));
                file.renameTo(new File(picPath, fileName));
                dataFarm.setPicture(fileName);
               dataFarm.setFarm_tel("093-119-9915");
                dataFarm.setFarm_address("บ้านเลขที่ 46 หมู่ที่11 บ้านหนองแวง ตำบลหนองแก้ว อำเภอเมือง จังหวัดร้อยเอ็ด 45000");
                dataFarm.setFarm_details("โคขุนหนองแวง\n" +
                        "เนื้อโคขุนคุณภาพสูง ของเมืองไทย\n" +
                        " เป็นโคลูกผสมที่เลี้ยงในฟาร์มโคขุนหนองแวง จ.ร้อยเอ็ด ในสภาพแวดล้อมแบบเปิดเพื่อให้โคไม่ต้องออกกำลัง พร้อมขุนด้วยอาหารขุนสูตรเฉพาะของฟาร์ม ซึ่งมีกากน้ำตาลเป็นหลัก เพื่อให้ได้เนื้อที่นุ่มด้วยไขมันละเอียดแทรกอยู่ โดยไม่ใช้ฮอโมนและสารเร่งโตใดๆทำให้ได้อร่อยจากรสชาติเนื้อวัวแท้โดยพ่อพันธุ์ยุโรปแท้ 100% เพื่อใช้ผสมเทียม ได้แก่ วัวสายพันธ์โรเล่ห์  ถิ่นกำเนิดประเทศฝรั่งเศสแม่พันธุ์ เป็นโคลูกผสมสายพันธุ์พื้นเมืองที่ทนทานต่อสภาพอากาศ และพันธุ์บรามันที่มีโครงสร้างใหญ่ โคลูกผสมที่จะได้เหมาะสำหรับเลี้ยงในสภาพภูมิอากาศของเมืองไทย ลักษณะพันธุ์ของโคขุน มีดังนี้ โคพื้นเมือง 25% โคบรามันห์ 25% โคสายพันธ์ยุโรป 50%  โคที่จะขุนต้องผ่านการถ่ายพายาธิและมีอายุ 2 ปี ขึ้นไป การขุนอย่างน้อยจะใช้เวลา 8 เดือน  ขึ้นไปจึงจะสามารถชำแหละได้ และบ่มเนื้อไว้ในห้องอุณหภูมิ 3 องศาเซลเซียล ที่กรุงเทพฯ และนำมาตัดแต่งชิ้นส่วนออกจำหน่ายสู่ตลาดต่อไป");
                dataFarm.setFram_name("ฟาร์มโคขุนหนองแวง");
               dataFarm.setDate_farm(new Date());
                farmList.add(dataFarm);
                farm.add(dataFarm);

            }
            return showfarm();

        }
    }


    public static Result editfarm(String id) {
        play.data.Form<farm> newformpro = myFormsFarm.bindFromRequest();
        dataFarm = farm.finder.byId(id);
        session("FarmId",dataFarm.getFarm_id());
        if (dataFarm == null) {
            return showfarm();
        } else {
            myFormsFarm = play.data.Form.form(farm.class).fill(dataFarm);
            return main1(edit_Farm.render(myFormsFarm));
        }
    }

    public static Result updatefarm() {
        play.data.Form<farm> formupdate = myFormsFarm.bindFromRequest();
        if (formupdate.hasErrors()) {
            return main1(edit_Farm.render(formupdate));
        } else {
            farm ffarm = new farm(session("FarmId"),formupdate.get().getFram_name(),formupdate.get().getFarm_address(),formupdate.get().getFarm_details(),formupdate.get().getFarm_tel(),formupdate.get().getPicture(),formupdate.get().getDate_farm());
            farm.edit(ffarm);
            return showfarm();
        }
    }

    //ลบ
    public static Result deletfarm(String id) {
        dataFarm = farm.finder.byId(id);
        if (dataFarm != null) {
            dataFarm .delete();
        }
        return showfarm();
    }


}
