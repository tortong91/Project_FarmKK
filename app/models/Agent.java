package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;
import java.util.Random;

@Entity
@Table(name="tbagent")
public class Agent extends Model {
    @Id

    private String agent_id;
    private String agent_name,agent_address,agent_tel,picture;

    public Agent() {
        setAgent_id();
    }

    public Agent( String agent_name, String agent_address, String agent_tel, String picture) {
 if(agent_id==null){
setAgent_id();
 }else{
     setnewid(agent_id);
 }
        this.agent_name = agent_name;
        this.agent_address = agent_address;
        this.agent_tel = agent_tel;
        this.picture = picture;
    }

    public String getAgent_id() {
        return agent_id;
    }

    public void setAgent_id() {
        int i ;
        Random random = new Random();
        i = random.nextInt(100000)+1;
        agent_id = "Agent-" + Integer.toString(i);
    }
 public void setnewid(String id) {
      this.agent_id=id;
    }

    public String getAgent_name() {
        return agent_name;
    }

    public void setAgent_name(String agent_name) {
        this.agent_name = agent_name;
    }

    public String getAgent_address() {
        return agent_address;
    }

    public void setAgent_address(String agent_address) {
        this.agent_address = agent_address;
    }

    public String getAgent_tel() {
        return agent_tel;
    }

    public void setAgent_tel(String agent_tel) {
        this.agent_tel = agent_tel;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    //เป็นการหาเอาข้อมูลมาเก็บไว้ใน ชื่อ (finder)
    public static Model.Finder<String,Agent> finder=new Model.Finder<String, Agent>(String.class,Agent.class);
    //ใน<คือ modei > เอาข้อมูลในฐานข้อมูลมาเก็บใน showlist
    public static List<Agent> showlist(){
        return finder.all();

    }
    //เป็นเอาข้อมูลบันทึกลงฐานข้อมูล
    public static void add(Agent dataagent){
     dataagent.save();
    }
    //เป็นแก้ไขข้อมูลฐานข้อมูล
    public static void edit(Agent dataagent)
    {
        dataagent.update();
    }



    //เป็นลบข้อมูลฐานข้อมูล
    public static void delet(Agent dataagent){
        dataagent.delete();
    }

}
