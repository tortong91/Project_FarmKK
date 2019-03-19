package models;

import play.data.format.Formats;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Entity
@Table(name = "saveFood")
public class SaveFoods extends Model {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Formats.DateTime(pattern = "dd/MM/yyyy")
    private Date datesavefood;
    private int round;
    private int mount;

@ManyToOne
private Cows cowdata;
@ManyToOne
private Recipe recip;
@ManyToOne
private Inputfood ifoode;
@ManyToOne
private DetailRecip d;

    public SaveFoods() {

    }

    public SaveFoods(long id, Date datesavefood, Cows cowdata, Recipe recip,int round ,Inputfood ifoode,int mount) {
this.id=id;

        this.datesavefood = datesavefood;
        this.cowdata = cowdata;
        this.recip = recip;

        this.round=round;
        this.ifoode=ifoode;

        this.mount=mount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
      this.id=id;

    }



    public Date getDatesavefood() {
        return datesavefood;
    }

    public void setDatesavefood(Date datesavefood) {
        this.datesavefood = datesavefood;
    }

    public Cows getCowdata() {
        return cowdata;
    }

    public void setCowdata(Cows cowdata) {
        this.cowdata = cowdata;
    }

    public Recipe getRecip() {
        return recip;
    }

    public void setRecip(Recipe recip) {
        this.recip = recip;
    }



    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public Inputfood getIfoode() {
        return ifoode;
    }

    public void setIfoode(Inputfood ifoode) {
        this.ifoode = ifoode;
    }


    public int getMount() {
        return mount;
    }

    public void setMount(int mount) {
        this.mount = mount;
    }

    //เป็นการหาเอาข้อมูลมาเก็บไว้ใน ชื่อ (finder)
    public static Model.Finder<String,SaveFoods> finder=new Model.Finder<String, SaveFoods>(String.class,SaveFoods.class);
    //ใน<คือ modei > เอาข้อมูลในฐานข้อมูลมาเก็บใน showlist
    public static List<SaveFoods> list(){
        return finder.all();

    }
    //เป็นเอาข้อมูลบันทึกลงฐานข้อมูล
    public static void add(SaveFoods data){
        data.save();
    }
    //เป็นแก้ไขข้อมูลฐานข้อมูล
    public static void edit(SaveFoods data)
    {
        data.update();
    }
    //เป็นลบข้อมูลฐานข้อมูล
    public static void delete(SaveFoods data){
        data.delete();
    }

    public static SaveFoods findFood (String id )
    {
        return finder.where().eq("cowdata.cow_id",id).findUnique();
    }

}
