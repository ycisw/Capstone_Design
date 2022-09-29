package com.example.capstonedesign.student;


public class ListViewItem {
    private String name;
    private String pname;
    private String pphone;
    public void setName(String name){
        this.name =name;
    }

    public String getName(){ return name; }

    public void setPname(String pname){this.pname =pname;}

    public String getPname(){
        return pname;
    }

    public void setPphone(String pphone){this.pphone =pphone;}

    public String getPphone(){
        return pphone;
    }
}