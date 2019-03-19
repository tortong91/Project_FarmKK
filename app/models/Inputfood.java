package models;

import com.avaje.ebean.Expr;
import play.data.format.Formats;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="tbInputfood")
public class Inputfood extends Model {
    @Id
    private String id_ifoods;
    private String unit,picture;
    private int price_ifoods;
    private int amount_ifoods,totalamount;
    private int amountKg,total,totalPrice;

    @Formats.DateTime(pattern = "dd/MM/yyyy")
    private Date date;

    @OneToMany(mappedBy = "inputfood", cascade = CascadeType.ALL)//สั่งให้เปลี่ยนข้อมูลให้สัมพันธ์กัน
    private List<Recipe> recipeList = new ArrayList<Recipe>();

    @OneToMany(mappedBy = "ifoode", cascade = CascadeType.ALL)//สั่งให้เปลี่ยนข้อมูลให้สัมพันธ์กัน
    private List<SaveFoods> savefoodsList = new ArrayList<SaveFoods>();

    @OneToMany(mappedBy = "inputfoods", cascade = CascadeType.ALL)//สั่งให้เปลี่ยนข้อมูลให้สัมพันธ์กัน
    private List<DetailRecip> drecipList = new ArrayList<DetailRecip>();


    @ManyToOne
    private Foods food;
    @ManyToOne
    private DetailRecip inputfood;
    @ManyToOne
    private Recipe recip;

    public Inputfood() {
    }

    public Inputfood(String id_ifoods,Foods food, String unit, String picture, int price_ifoods, int amount_ifoods, int totalamount, int amountKg, int total, int totalPrice, Date date) {
        this.id_ifoods = id_ifoods;

        this.food = food;
        this.unit = unit;
        this.picture = picture;
        this.price_ifoods = price_ifoods;
        this.amount_ifoods = amount_ifoods;
        this.totalamount = totalamount;
        this.amountKg = amountKg;
        this.total = total;
        this.totalPrice = totalPrice;
        this.date = date;

        this.picture = picture;


    }

    //    public int getPlus() {
//        return plus=amount_ifoods-amount_recipe;
//    }
//
//    public void setPlus(int plus) {
//        this.plus = plus;
//    }


    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getAmountKg() {
        return amountKg;
    }

    public void setAmountKg(int amountKg) {
        this.amountKg = amountKg;
    }

    public String getId_ifoods() {
        return id_ifoods;
    }

    public void setId_ifoods(String id_ifoods) {
        this.id_ifoods = id_ifoods;
    }




    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getPrice_ifoods() {
        return price_ifoods;
    }

    public void setPrice_ifoods(int price_ifoods) {
        this.price_ifoods = price_ifoods;
    }

    public int getAmount_ifoods() {
        return amount_ifoods;


    }


    public void setAmount_ifoods(int amount_ifoods) {
        this.amount_ifoods = amount_ifoods;
    }

    public Foods getFood() {
        return food;
    }

    public void setFood(Foods food) {
        this.food = food;
    }

    public DetailRecip getInputfood() {
        return inputfood;
    }

    public void setInputfood(DetailRecip inputfood) {
        this.inputfood = inputfood;
    }

    public Recipe getRecip() {
        if(recip==null)
            return recip;
        else
        return recip;
    }

    public void setRecip(Recipe recip) {
        this.recip = recip;
    }

    public int getTotalamount() {
                    return totalamount=getAmount_ifoods()-20;
    }

    public void setTotalamount(int totalamount) {
        this.totalamount = totalamount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setRecipeList(List<Recipe> recipeList) {
        this.recipeList = recipeList;
    }

    public void setSavefoodsList(List<SaveFoods> savefoodsList) {
        this.savefoodsList = savefoodsList;
    }





    //เป็นการหาเอาข้อมูลมาเก็บไว้ใน ชื่อ (finder)
    public static Finder<String,Inputfood> finder=new Finder<String, Inputfood>(String.class,Inputfood.class);
    //ใน<คือ modei > เอาข้อมูลในฐานข้อมูลมาเก็บใน showlist
    public static List<Inputfood> showlist(){
        return finder.all();
    }
    //เป็นเอาข้อมูลบันทึกลงฐานข้อมูล
    public static void add(Inputfood data){
        data.save();
    }
    //เป็นแก้ไขข้อมูลฐานข้อมูล
    public static void edit( Inputfood data)
    {
        data.update();
    }
    //เป็นลบข้อมูลฐานข้อมูล
    public static void delete(Inputfood data){
        data.delete();
    }


    public static Inputfood findFood (String id )
    {
        return finder.where().eq("inputfoods.id",id).findUnique();
    }
}
