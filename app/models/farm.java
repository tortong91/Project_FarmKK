package models;

import play.data.format.Formats;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Entity
@Table(name="tbFarm")
public class farm extends Model {
    @Id
    private String farm_id;
    @Lob
    private  String farm_details;
@Lob
    private String fram_name,farm_address,farm_tel,picture;
    @Formats.DateTime(pattern="dd/MM/yyyy")
    private Date date_farm=new Date();


    public farm() {

        setFarm_id();
    }

    public farm(String farm_id, String fram_name, String farm_address, String farm_details, String farm_tel, String picture,Date date_farm) {
      if(farm_id==null){
          setFarm_id();
      }else{
          setnewid(farm_id);
      }
        this.fram_name = fram_name;
        this.farm_address = farm_address;
        this.farm_details = farm_details;
        this.farm_tel = farm_tel;
        this.picture = picture;
        this.date_farm=date_farm;
    }

    public String getFarm_id() {
        return farm_id;
    }

    public void setFarm_id() {
        int i ;
        Random random = new Random();
        i = random.nextInt(100000)+1;
        farm_id = "Farm-" + Integer.toString(i);
    }
public void setnewid(String id){
        this.farm_id=id;
}
    public String getFram_name() {
        return fram_name;
    }

    public void setFram_name(String fram_name) {
        this.fram_name = fram_name;
    }

    public String getFarm_address() {
        return farm_address;
    }

    public void setFarm_address(String farm_address) {
        this.farm_address = farm_address;
    }

    public String getFarm_details() {
        return farm_details;
    }

    public void setFarm_details(String farm_details) {
        this.farm_details = farm_details;
    }

    public String getFarm_tel() {
        return farm_tel;
    }

    public void setFarm_tel(String farm_tel) {
        this.farm_tel = farm_tel;
    }

    public String getPicture() {
        return picture;
    }

    public Date getDate_farm() {
        return date_farm;
    }

    public void setDate_farm(Date date_farm) {
        this.date_farm = date_farm;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
    //เป็นการหาเอาข้อมูลมาเก็บไว้ใน ชื่อ (finder)
    public static Finder<String,farm> finder=new Finder<String, farm>(String.class,farm.class);
    //ใน<คือ modei > เอาข้อมูลในฐานข้อมูลมาเก็บใน showlist
    public static List<farm> showlist(){
        return finder.all();

    }
    //เป็นเอาข้อมูลบันทึกลงฐานข้อมูล
    public static void add(farm data){
        data.save();
    }
    //เป็นแก้ไขข้อมูลฐานข้อมูล
    public static void edit( farm data)
    {
        data.update();
    }
    //เป็นลบข้อมูลฐานข้อมูล
    public static void delete(farm data){
        data.delete();
    }

}
