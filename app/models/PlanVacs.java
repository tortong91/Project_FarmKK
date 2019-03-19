package models;

import play.data.format.Formats;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Entity
@Table(name="tbPlanVacs")
public class PlanVacs extends Model {
    @Id

    private String id;
    @Formats.DateTime(pattern = "dd/MM/yyyy")
    private  Date pvacdate;

    private String pdate,pdate1,status;

@ManyToOne
private Cows cowLst;
@ManyToOne
private Vacs vac;
@ManyToOne
private Vacs vacfinaly;
    public PlanVacs() {
setId();
    }


    public PlanVacs(String id, Date pvacdate, String pdate, String pdate1, Cows cowLst, Vacs vac,Vacs vacfinaly,String status) {
                if (id == null){
            setId();
        }else {
            setnewid(id);
        }
        this.pvacdate = pvacdate;
        this.pdate = pdate;
        this.pdate1 = pdate1;
        this.cowLst = cowLst;
        this.vac = vac;
        this.vacfinaly=vacfinaly;
        this.status=status;
    }

    public String getId() {
        return id;
    }

    public void setId()
    {
                int i ;
        Random random = new Random();
        i = random.nextInt(100000)+1;
        id = "PlanVac" + Integer.toString(i);
        this.id = id;
    }
    public void setnewid(String id) {
        this.id = id;
    }

    public Date getPvacdate() {
        return pvacdate;
    }

    public void setPvacdate(Date pvacdate) {
        this.pvacdate = pvacdate;
    }

    public Cows getCowLst() {
        return cowLst;
    }

    public void setCowLst(Cows cowLst) {
        this.cowLst = cowLst;
    }

    public Vacs getVac() {
        return vac;
    }

    public void setVac(Vacs vac) {
        this.vac = vac;
    }

    public String getPdate() {
        return pdate;
    }

    public void setPdate(String pdate) {
        this.pdate = pdate;
    }

    public String getPdate1() {
        return pdate1;
    }

    public void setPdate1(String pdate1) {
        this.pdate1 = pdate1;
    }

    public String getStatus() {
        if(status==null)
            return "รอการฉีดวัคซีน";
            else

        return "ฉีดวัคซีนแล้ว";
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Vacs getVacfinaly() {
        return vacfinaly;
    }

    public void setVacfinaly(Vacs vacfinaly) {
        this.vacfinaly = vacfinaly;
    }

    //เป็นการหาเอาข้อมูลมาเก็บไว้ใน ชื่อ (finder)
    public static Finder<String,PlanVacs> finder=new Finder<String, PlanVacs>(String.class,PlanVacs.class);
    //ใน<คือ modei > เอาข้อมูลในฐานข้อมูลมาเก็บใน showlist
    public static List<PlanVacs> showlist(){
        return finder.all();

    }
    //เป็นเอาข้อมูลบันทึกลงฐานข้อมูล
    public static void add(PlanVacs data){
        data.save();
    }
    //เป็นแก้ไขข้อมูลฐานข้อมูล
    public static void edit(PlanVacs data)
    {
        data.update();
    }
    //เป็นลบข้อมูลฐานข้อมูล
    public static void delete(PlanVacs data){
        data.delete();
    }

    public static PlanVacs findCow (String id ){
        return finder.where().eq("cowLst.cow_id",id).findUnique();
    }

}
