package models;

import play.db.ebean.Model;
import scalax.io.Input;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Entity
@Table(name="detailRe")
public class DetailRecip extends Model {
@Id
private String did;
private int amount;
    private int totalkg;
@ManyToOne
private Inputfood inputfoods;
@ManyToOne
    private Recipe recipe;

    public DetailRecip() {
        setDid();
    }

    public DetailRecip( int amount, Inputfood inputfoods, Recipe recipe,int totalkg) {
        setDid();
        this.amount = amount;
        this.inputfoods = inputfoods;
        this.recipe = recipe;
        this.totalkg = totalkg;
    }

    public String getDid() {
        return did;
    }

    public void setDid() {
        int i ;
        Random random = new Random();
        i = random.nextInt(1000000)+1;
        did = "CS-" + Integer.toString(i);
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Inputfood getInputfoods() {
        return inputfoods;
    }

    public void setInputfoods(Inputfood inputfoods) {
        this.inputfoods = inputfoods;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public int getTotalkg() {
        return totalkg=getAmount()+getAmount();
    }

    public void setTotalkg(int totalkg) {
        this.totalkg = totalkg;
    }

    public static Finder<String, DetailRecip> getFinder() {
        return finder;
    }

    public static void setFinder(Finder<String, DetailRecip> finder) {
        DetailRecip.finder = finder;
    }

    //เป็นการหาเอาข้อมูลมาเก็บไว้ใน ชื่อ (finder)
    public static Finder<String,DetailRecip> finder=new Finder<String, DetailRecip>(String.class,DetailRecip.class);
    //ใน<คือ modei > เอาข้อมูลในฐานข้อมูลมาเก็บใน showlist
    public static List<DetailRecip> showlist(){
        return finder.all();
    }
    //เป็นเอาข้อมูลบันทึกลงฐานข้อมูล
    public static void add(DetailRecip data){
        data.save();
    }
    //เป็นแก้ไขข้อมูลฐานข้อมูล
    public static void edit( DetailRecip data)
    {
        data.update();
    }
    //เป็นลบข้อมูลฐานข้อมูล
    public static void delete(DetailRecip data){
        data.delete();
    }
}
