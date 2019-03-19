package models;

import com.avaje.ebean.Expr;
import play.data.format.Formats;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Entity
@Table(name="tbaddcow")
public class Cows extends Model {
    @Id
    private String cow_id;
    private String sex,color,age,status,picture;
    @Formats.DateTime(pattern = "dd/MM/yyyy")
    private Date date;
    private String weight,height;
    private String price;
    @ManyToOne
    private breeds breed;

    @OneToMany(mappedBy = "cow", cascade = CascadeType.ALL)//สั่งให้เปลี่ยนข้อมูลให้สัมพันธ์กัน
    private List<InputCow> inputList = new ArrayList<InputCow>();
    private List<Cows> cowList = new ArrayList<Cows>();
    private List<SaveFoods> saveFoods = new ArrayList<SaveFoods>();
    private List<PlanVacs> vacList = new ArrayList<PlanVacs>();
    public Cows() {

    }

    public Cows(String cow_id,breeds breed,String status,String price, String sex, String color, Date date, String age, String picture, String weight, String height) {
        this.breed = breed;
        this.status=status;
        this.cow_id = cow_id;
        this.sex = sex;
        this.color = color;
        this.date = date;
        this.age = age;
        this.picture = picture;
        this.weight = weight;
        this.height = height;
        this.price=price;
    }

    public String getCow_id() {
        return cow_id;
    }

    public void setCow_id(String cow_id) {

        this.cow_id=cow_id  ;
    }


    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Date getDate() {

//        if (date==null)
//            return "ในเร็วๆนี้";
//        else
//
//        return date;
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public breeds getBreed() {
        return breed;
    }

    public void setBreed(breeds breed) {
        this.breed = breed;
    }

    public String getStatus() {
        if (status==null)
            return "ยังไม่รับโคขุน ⌛";
    else
        return "ได้รับโคขุนแล้ว ✓";


    }

    public void setStatus(String status) {
        this.status= status;
    }

    public String getPrice() {
        if (price==null)
            return "0.00";
        else
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


    //เป็นการหาเอาข้อมูลมาเก็บไว้ใน ชื่อ (finder)
    public static Finder<String,Cows> finder=new Finder<String, Cows>(String.class,Cows.class);
    //ใน<คือ modei > เอาข้อมูลในฐานข้อมูลมาเก็บใน showlist
    public static List<Cows> showlist(){
        return finder.all();
    }
    //เป็นเอาข้อมูลบันทึกลงฐานข้อมูล
    public static void add(Cows data){
        data.save();
    }
    //เป็นแก้ไขข้อมูลฐานข้อมูล
    public static void edit( Cows data)
    {
        data.update();
    }
    //เป็นลบข้อมูลฐานข้อมูล
    public static void delete(Cows data){
        data.delete();
    }

    public static List<Cows> cowlList (String id){
        return finder.where().eq("status_sale",id).findList();
    }
    public static List<Cows> brandList (String id){
        return finder.where().eq("breed.id",id).findList();
    }
}
