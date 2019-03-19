package models;

import play.db.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Entity
@Table(name="tbVac")
public class Vacs extends Model {
    @Id

    private String id;
    private String name,pr,picture;
    @OneToMany(mappedBy = "vac", cascade = CascadeType.ALL)//สั่งให้เปลี่ยนข้อมูลให้สัมพันธ์กัน
    private List<PlanVacs> vacList = new ArrayList<PlanVacs>();
    public Vacs() {
//setId();
    }

    public Vacs(String id, String name,  String pr, String picture) {
//        if (id == null){
//            setId();
//        }else {
//            setnewid(id);
//        }
        this.id=id;
        this.name = name;
        this.pr = pr;
        this.picture = picture;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {

//        int i ;
//        Random random = new Random();
//        i = random.nextInt(100000)+1;
//        id = "Vac" + Integer.toString(i);
        this.id=id;

    }
//public void setnewid(String id) {
//this.id=id;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPr() {
        return pr;
    }

    public void setPr(String pr) {
        this.pr = pr;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setVacList(List<PlanVacs> vacList) {
        this.vacList = vacList;
    }

    //เป็นการหาเอาข้อมูลมาเก็บไว้ใน ชื่อ (finder)
    public static Finder<String,Vacs> finder=new Finder<String, Vacs>(String.class,Vacs.class);
    //ใน<คือ modei > เอาข้อมูลในฐานข้อมูลมาเก็บใน showlist
    public static List<Vacs> showlist(){
        return finder.all();

    }
    //เป็นเอาข้อมูลบันทึกลงฐานข้อมูล
    public static void add(Vacs data){
        data.save();
    }
    //เป็นแก้ไขข้อมูลฐานข้อมูล
    public static void edit(Vacs data)
    {
        data.update();
    }
    //เป็นลบข้อมูลฐานข้อมูล
    public static void delete(Vacs data){
        data.delete();
    }


}
