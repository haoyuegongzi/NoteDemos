package com.haoyue.notedemos.drawerlayout;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 作者：chen1 on 2018/2/1 09
 * E—Mail：chen126jie@163.com
 * TODO：
 */

public class HandlerBean implements Parcelable {
    String address;
    String phoneNum;
    int color;

    public HandlerBean(String address, String phoneNum, int color) {
        this.address = address;
        this.phoneNum = phoneNum;
        this.color = color;
    }

    @Override
    public String toString() {
        return "HandlerBean{" +
                "address='" + address + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", color=" + color +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.address);
        dest.writeString(this.phoneNum);
        dest.writeInt(this.color);
    }

    public HandlerBean() {
    }

    protected HandlerBean(Parcel in) {
        this.address = in.readString();
        this.phoneNum = in.readString();
        this.color = in.readInt();
    }

    public static final Parcelable.Creator<HandlerBean> CREATOR = new Parcelable.Creator<HandlerBean>() {
        @Override
        public HandlerBean createFromParcel(Parcel source) {
            return new HandlerBean(source);
        }

        @Override
        public HandlerBean[] newArray(int size) {
            return new HandlerBean[size];
        }
    };
}
