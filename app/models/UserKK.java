package models;

import com.avaje.ebean.Expr;
import org.hibernate.validator.constraints.Email;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;
import java.util.Random;


@Entity
@Table(name="tbUser")
public class UserKK extends Model {
    @Id
    private String id;
    private String User_Name,User_Password,position,name,lastname,address,tel,email,age;

    public UserKK() {
setId();
    }

    public UserKK(String user_Name, String user_Password, String position, String name, String lastname, String address, String tel, String email, String age) {
        setId();
        User_Name = user_Name;
        User_Password = user_Password;
        this.position = position;
        this.name = name;
        this.lastname = lastname;
        this.address = address;
        this.tel = tel;
        this.email = email;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId() {
        int i ;
        Random random = new Random();
        i = random.nextInt(100000)+1;
        id = "member" + Integer.toString(i);
    }

    public String getUser_Name() {
        return User_Name;
    }

    public void setUser_Name(String user_Name) {
        User_Name = user_Name;
    }

    public String getUser_Password() {
        return User_Password;
    }

    public void setUser_Password(String user_Password) {
        User_Password = user_Password;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
    // public static Finder<String,foodsAnimal> finder=new Finder<String, foodsAnimal>(String.class,foodsAnimal.class);
    //ใน<คือ modei > เอาข้อมูลในฐานข้อมูลมาเก็บใน showlist
   // public static List<foodsAnimal> showlist(){
    //    return finder.all();

  //  }

    public static Finder<String, UserKK> finder = new Finder(String.class,UserKK.class);

    public static List<UserKK> userKKList (){
        return finder.all();
    }
    //เป็นเอาข้อมูลบันทึกลงฐานข้อมูล
    public static void add(UserKK data){
        data.save();
    }
    public static void edit(UserKK data)
    {
        data.update();
    }


    public static UserKK userKK (String user ,String pass){
        return finder.where().and(Expr.eq("User_Name",user),Expr.eq("User_Password",pass)).findUnique();
    }
}
