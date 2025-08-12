package com.example.bth132;

public class Phone {
    private String namephone;
    private int imagephone;

    public Phone(int imagephone, String namephone) {
        this.imagephone = imagephone;
        this.namephone = namephone;
    }

    public int getImagephone() {
        return imagephone;
    }

    public String getNamephone() {
        return namephone;
    }

    public void setNamephone(String namephone) {
        this.namephone = namephone;
    }

    public void setImagephone(int imagephone) {
        this.imagephone = imagephone;
    }
}
