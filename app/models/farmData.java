package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.soap.Text;
import java.util.List;

@Entity
@Table(name="farmData")
public class farmData extends Model {
    @Id
    @GeneratedValue
    private String farm_id;
    private String fram_name,farm_address,farm_tel,picture;

    public farmData() {
    }

    public farmData(String farm_id, String fram_name, String farm_address, String farm_tel, String picture) {
        this.farm_id = farm_id;
        this.fram_name = fram_name;
        this.farm_address = farm_address;
        this.farm_tel = farm_tel;
        this.picture = picture;
    }

    public String getFarm_id() {
        return farm_id;
    }

    public void setFarm_id(String farm_id) {
        this.farm_id = farm_id;
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

    public String getFarm_tel() {
        return farm_tel;
    }

    public void setFarm_tel(String farm_tel) {
        this.farm_tel = farm_tel;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }//เป็นการหาเอาข้อมูลมาเก็บไว้ใน ชื่อ (finder)
    public static Model.Finder<String,farmData> finder=new Model.Finder<String, farmData>(String.class,farmData.class);
    //ใน<คือ modei > เอาข้อมูลในฐานข้อมูลมาเก็บใน showlist
    public static List<farmData> showlist(){
        return finder.all();

    }
    //เป็นเอาข้อมูลบันทึกลงฐานข้อมูล
    public static void add(farmData dataFarm){
        dataFarm.save();
    }
    //เป็นแก้ไขข้อมูลฐานข้อมูล
    public static void edit(farmData dataFarm)
    {
        dataFarm.update();
    }



    //เป็นลบข้อมูลฐานข้อมูล
    public static void delet(farmData dataFarm){
        dataFarm.delete();
    }

}
