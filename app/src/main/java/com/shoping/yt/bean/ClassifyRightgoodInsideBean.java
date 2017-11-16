package com.shoping.yt.bean;

import java.util.List;

/**
 * Created by fmy on 2017/11/13.
 */

public class ClassifyRightgoodInsideBean {


  private   String imgUri;
  private   String infomation;

    public String getImgUri() {
        return imgUri;
    }

    public void setImgUri(String imgUri) {
        this.imgUri = imgUri;
    }

    public String getInfomation() {
        return infomation;
    }

    public void setInfomation(String infomation) {
        this.infomation = infomation;
    }

    public ClassifyRightgoodInsideBean() {
    }

    public ClassifyRightgoodInsideBean(String imgUri, String infomation) {
        this.imgUri = imgUri;
        this.infomation = infomation;
    }
}
