package models;

import play.data.format.Formats;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Entity
@Table(name="tbRecipe")
public class Recipe extends Model {
    @Id
    private String nameRecip;


    private int amountCow;


    private String dateTimeRecipe;
    @Formats.DateTime(pattern="dd/MM/yyyy")
    private  Date dateRecipe=new Date();
    @OneToMany(mappedBy = "recip", cascade = CascadeType.ALL)//สั่งให้เปลี่ยนข้อมูลให้สัมพันธ์กัน
    private List<SaveFoods> recipList = new ArrayList<SaveFoods>();

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)//สั่งให้เปลี่ยนข้อมูลให้สัมพันธ์กัน
    private List<DetailRecip> drecipList = new ArrayList<DetailRecip>();


    @ManyToOne()
    private Inputfood inputfood;
    @ManyToOne()
    private DetailRecip recip;


    public Recipe() {
//setRid();
    }


    public Recipe(String nameRecip, Date dateRecipe, String dateTimeRecipe,int amountCow ) {
    this.nameRecip=nameRecip;
        this.dateRecipe = dateRecipe;
        this.dateTimeRecipe = dateTimeRecipe;
        this.amountCow = amountCow;

    }

    public String getNameRecip() {
        return nameRecip;
    }

    public void setNameRecip(String nameRecip) {
//        int i ;
//        Random random = new Random();
//        i = random.nextInt(100000)+1;
//        rid = "Ctive-" + Integer.toString(i);
        this.nameRecip = nameRecip;
    }
//public void setnewrid(String rid) {
//        this.rid = rid;
//    }


    public List<SaveFoods> getRecipList() {
        return recipList;
    }

    public List<DetailRecip> getDrecipList() {
        return drecipList;
    }

    public DetailRecip getRecip() {
        return recip;
    }

    public void setRecip(DetailRecip recip) {
        this.recip = recip;
    }

    public Date getDateRecipe() {
        return dateRecipe;
    }

    public void setDateRecipe(Date dateRecipe) {
        this.dateRecipe = dateRecipe;
    }

    public String getDateTimeRecipe() {
        return dateTimeRecipe;
    }

    public void setDateTimeRecipe(String dateTimeRecipe) {
        this.dateTimeRecipe = dateTimeRecipe;
    }

    public int getAmountCow() {
        return amountCow;
    }

    public void setAmountCow(int amountCow) {
        this.amountCow = amountCow;
    }

    public void setRecipList(List<SaveFoods> recipList) {
        this.recipList = recipList;
    }

    public void setDrecipList(List<DetailRecip> drecipList) {
        this.drecipList = drecipList;
    }

    public Inputfood getInputfood() {
        return inputfood;
    }

    public void setInputfood(Inputfood inputfood) {
        this.inputfood = inputfood;
    }



    //เป็นการหาเอาข้อมูลมาเก็บไว้ใน ชื่อ (finder)
    public static Model.Finder<String,Recipe> finder=new Model.Finder<String, Recipe>(String.class,Recipe.class);
    //ใน<คือ modei > เอาข้อมูลในฐานข้อมูลมาเก็บใน showlist
    public static List<Recipe> list(){
        return finder.all();

    }
    //เป็นเอาข้อมูลบันทึกลงฐานข้อมูล
    public static void add(Recipe data){
        data.save();
    }
    //เป็นแก้ไขข้อมูลฐานข้อมูล
    public static void edit(Recipe data)
    {
        data.update();
    }
    //เป็นลบข้อมูลฐานข้อมูล
    public static void delete(Recipe data){
        data.delete();
    }


}
