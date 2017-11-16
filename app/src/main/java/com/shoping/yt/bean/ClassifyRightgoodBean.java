package com.shoping.yt.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fmy on 2017/11/13.
 */

public class ClassifyRightgoodBean {

  private   String categoryName;
  private   List<String> imgUri;
  private   List<String> infomation;
  private   boolean showRanking;

    public ClassifyRightgoodBean(String categoryName, List<String> imgUri, List<String> infomation, boolean showRanking) {
        this.categoryName = categoryName;
        this.imgUri = imgUri;
        this.infomation = infomation;
        this.showRanking = showRanking;
    }

    public boolean isShowRanking() {

        return showRanking;
    }

    public void setShowRanking(boolean showRanking) {
        this.showRanking = showRanking;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<String> getImgUri() {
        return imgUri;
    }

    public void setImgUri(List<String> imgUri) {
        this.imgUri = imgUri;
    }

    public List<String> getInfomation() {
        return infomation;
    }

    public void setInfomation(List<String> infomation) {
        this.infomation = infomation;
    }

    public ClassifyRightgoodBean() {
    }

    public ClassifyRightgoodBean(String categoryName, List<String> imgUri, List<String> infomation) {
        this.categoryName = categoryName;
        this.imgUri = imgUri;
        this.infomation = infomation;
    }
}
