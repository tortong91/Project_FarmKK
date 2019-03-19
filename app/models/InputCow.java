package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name="tbinputcow")
public class InputCow extends Model {
    @Id
    private String id;
    private String date,confirm;
    @ManyToOne
    private Cows cow;
    public InputCow() {
    }

    public InputCow(String id, String date, String confirm,Cows cow) {
        this.cow=cow;
        this.id = id;
        this.date = date;
        this.confirm = confirm;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getConfirm() {
        if (confirm==null)
            return "ยังไม่ได้รับโค";
        else
            return "รับโคขุนเข้าฟาร์มเรียบร้อย";

    }

    public Cows getCow() {
        return cow;
    }

    public void setCow(Cows cow) {
        this.cow = cow;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }
    //เป็นการหาเอาข้อมูลมาเก็บไว้ใน ชื่อ (finder)
    public static Finder<String,InputCow> finder=new Finder<String, InputCow>(String.class,InputCow.class);
    //ใน<คือ modei > เอาข้อมูลในฐานข้อมูลมาเก็บใน showlist
    public static List<InputCow> showlist(){
        return finder.all();

    }
    //เป็นเอาข้อมูลบันทึกลงฐานข้อมูล
    public static void add(InputCow Cow){
        Cow.save();
    }
    //เป็นแก้ไขข้อมูลฐานข้อมูล
    public static void edit(InputCow Cow)
    {
        Cow.update();
    }
    //เป็นลบข้อมูลฐานข้อมูล
    public static void delete(InputCow Cow){
        Cow.delete();
    }
}
