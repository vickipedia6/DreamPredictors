package com.company.batman.firebasetest;

public class ImageTask {
    String title;
     String url;
     String desc;

    public  String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public ImageTask(String title, String url,String desc) {
        this.title = title;
        this.url = url;
        this.desc=desc;
    }
   public ImageTask(){}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
