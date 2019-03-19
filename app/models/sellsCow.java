package models;

import play.db.ebean.Model;

import java.util.List;

public class sellsCow {
    private String SellCow_id;
    private String ListSellCow_Price,ListSellCow_Weight,ListSellCow_Meat;

    public sellsCow() {
    }

    public sellsCow(String sellCow_id, String listSellCow_Price, String listSellCow_Weight, String listSellCow_Meat) {
        SellCow_id = sellCow_id;
        ListSellCow_Price = listSellCow_Price;
        ListSellCow_Weight = listSellCow_Weight;
        ListSellCow_Meat = listSellCow_Meat;
    }

    public String getSellCow_id() {
        return SellCow_id;
    }

    public void setSellCow_id(String sellCow_id) {
        SellCow_id = sellCow_id;
    }

    public String getListSellCow_Price() {
        return ListSellCow_Price;
    }

    public void setListSellCow_Price(String listSellCow_Price) {
        ListSellCow_Price = listSellCow_Price;
    }

    public String getListSellCow_Weight() {
        return ListSellCow_Weight;
    }

    public void setListSellCow_Weight(String listSellCow_Weight) {
        ListSellCow_Weight = listSellCow_Weight;
    }

    public String getListSellCow_Meat() {
        return ListSellCow_Meat;
    }

    public void setListSellCow_Meat(String listSellCow_Meat) {
        ListSellCow_Meat = listSellCow_Meat;
    }
    //เป็นการหาเอาข้อมูลมาเก็บไว้ใน ชื่อ (finder)
    public static Model.Finder<String,sellsCow> finder=new Model.Finder<String, sellsCow>(String.class,sellsCow.class);
    //ใน<คือ modei > เอาข้อมูลในฐานข้อมูลมาเก็บใน showlist
    public static List<sellsCow> showlist(){
        return finder.all();

    }

/*
    //เป็นเอาข้อมูลบันทึกลงฐานข้อมูล
    public static void add(sellsCow data){
        data.save();
    }
    //เป็นแก้ไขข้อมูลฐานข้อมูล
    public static void edit(sellsCow data)
    {
        data.update();
    }
    //เป็นลบข้อมูลฐานข้อมูล
    public static void delete(sellsCow data){
        data.delete();
    }
*/
}
