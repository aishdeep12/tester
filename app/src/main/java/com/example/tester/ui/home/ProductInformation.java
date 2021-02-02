package com.example.tester.ui.home;

public class ProductInformation {

    public ProductInformation(){

    }
    public String name;
    public String salt;
    public String image;

    public String getname() {
        return name;
    }

    public String getsalt() {
        return salt;
    }

    public String getimage() {
        return image;
    }

    public ProductInformation(String image, String name, String salt){
        this.name = name;
        this.salt = salt;
        this.image = image;
    }
}
