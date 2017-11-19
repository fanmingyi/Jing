package com.shoping.yt.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.UUID;

/**
 * Created by fmy on 2017/11/19.
 */

public class CartGoodsBean implements Parcelable {
    //是否被选中
    private boolean isCheck;
    private String ID;

    {
        ID = UUID.randomUUID().toString();
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    protected CartGoodsBean(Parcel in) {
        isCheck = in.readByte() != 0;
        prise = in.readFloat();
    }

    @Override
    public String toString() {
        return "CartGoodsBean{" +
                "isCheck=" + isCheck +
                ", ID='" + ID + '\'' +
                ", prise=" + prise +
                '}';
    }

    public static final Creator<CartGoodsBean> CREATOR = new Creator<CartGoodsBean>() {
        @Override
        public CartGoodsBean createFromParcel(Parcel in) {
            return new CartGoodsBean(in);
        }

        @Override
        public CartGoodsBean[] newArray(int size) {
            return new CartGoodsBean[size];
        }
    };

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public float getPrise() {
        return prise;
    }

    public void setPrise(float prise) {
        this.prise = prise;
    }

    public CartGoodsBean(boolean isCheck, float prise) {
        this.isCheck = isCheck;
        this.prise = prise;
    }

    //价格
    private float prise;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (isCheck ? 1 : 0));
        dest.writeFloat(prise);
        dest.writeString(getID());
    }
}
