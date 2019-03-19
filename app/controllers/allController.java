package controllers;

import models.Recipe;
import models.UserKK;
import models.breeds;
import models.foodsAnimal;
import org.h2.engine.User;
import play.api.templates.Html;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;
import views.html.contact.contact;
import views.html.contact.contentView;
import views.html.details.detailsCow;

import java.util.ArrayList;
import java.util.List;

import static controllers.farmController.showfarm;

public class allController extends Controller {
    public static List<UserKK>Listlogin=new ArrayList<UserKK>();

    public static Result main1(Html main1)
    {
        Listlogin=UserKK.userKKList();
        return ok(index.render(main1,Listlogin));
    }

    public static Result index()
    {
        return main1(contentView.render());
    }
    public static Result Login()
    {
        return main1(formLogin.render(listRegister));
    }
    public static Result telAdmin(){
        return main1(views.html.contact.contact_Systemadministrator.render());
    }
    public static Result tel(){
        return main1(contact.render());
    }




    public static Result loginkk() {
        String user = Form.form().bindFromRequest().get("user");
        String pass = Form.form().bindFromRequest().get("pass");
        UserKK datauser = UserKK.userKK(user, pass);
        String position = datauser.getPosition();
        if (datauser != null) {
            session("name", datauser.getName());
            session("position", datauser.getPosition());
        }
        return inputmember();
    }

    public static Result Logout (){
        session().clear();
        return main1(formLogin.render(listRegister));
    }
//Register
    public static Form<UserKK> formRegister = Form.form(UserKK.class);
    public static List<UserKK> listRegister = new ArrayList<UserKK>();
    public static UserKK data;

    public static play.mvc.Result inputmember(){
        return main1(contentView.render());
    }

    public static play.mvc.Result showmember() {
       listRegister = UserKK.userKKList();
        return main1(success.render(listRegister));
    }
    public static play.mvc.Result showmemberOK() {
       listRegister = UserKK.userKKList();
        return main1(show_okRegister.render(listRegister));
    }


    public static play.mvc.Result add() {
        formRegister = Form.form(UserKK.class);
        return ok(register_member.render(formRegister));
    }

    public static play.mvc.Result input() {
        Form<UserKK> newadd = formRegister.bindFromRequest();
        if (newadd.hasErrors()) {

            //flash("msgError", "ใช้ข้อมูลไม่ถูกต้อง");
            return ok(register_member.render(newadd));
        } else {
            data = newadd.get();
            UserKK.add(data);
            return showmember();
        }
    }



}
