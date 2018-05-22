package com.haoyue.notedemos.retrofit_learn;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：chen1 on 2018/3/7 11
 * E—Mail：chen126jie@163.com
 * TODO：
 * @author chen1
 */

public class CarBean implements Parcelable {
    /**
     * translation : ["车"]
     * basic : {"us-phonetic":"kɑr","phonetic":"kɑː","uk-phonetic":"kɑː","explains":["n. 汽车；车厢","n. (Car)人名；(土)贾尔；(法、西)卡尔；(塞)察尔"]}
     * query : car
     * errorCode : 0
     * web : [{"value":["汽车","小汽车","轿车"],"key":"Car"},{"value":["碰碰车","碰撞用汽车","碰碰汽车"],"key":"bumper car"},{"value":["安全车","安全车","安全汽车"],"key":"Safety car"}]
     */
    public BasicBean basic;
    public String query;
    public String errorCode;
    public List<String> translation;
    public List<WebBean> web;

    @Override
    public String toString() {
        return "CarBean{" +
                "basic=" + basic +
                ", query='" + query + '\'' +
                ", errorCode='" + errorCode + '\'' +
                ", translation=" + translation +
                ", web=" + web +
                '}';
    }

    public static class BasicBean {
        /**
         * us-phonetic : kɑr
         * phonetic : kɑː
         * uk-phonetic : kɑː
         * explains : ["n. 汽车；车厢","n. (Car)人名；(土)贾尔；(法、西)卡尔；(塞)察尔"]
         */
        @SerializedName("us-phonetic")
        public String usphonetic;
        public String phonetic;
        @SerializedName("uk-phonetic")
        public String ukphonetic;
        public List<String> explains;

        @Override
        public String toString() {
            return "BasicBean{" +
                    "usphonetic='" + usphonetic + '\'' +
                    ", phonetic='" + phonetic + '\'' +
                    ", ukphonetic='" + ukphonetic + '\'' +
                    ", explains=" + explains +
                    '}';
        }
    }

    public static class WebBean {
        /**
         * value : ["汽车","小汽车","轿车"]
         * key : Car
         */
        public String key;
        public List<String> value;

        @Override
        public String toString() {
            return "WebBean{" +
                    "key='" + key + '\'' +
                    ", value=" + value +
                    '}';
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable((Parcelable) this.basic, flags);
        dest.writeString(this.query);
        dest.writeString(this.errorCode);
        dest.writeStringList(this.translation);
        dest.writeList(this.web);
    }

    public CarBean() {
    }

    protected CarBean(Parcel in) {
        this.basic = in.readParcelable(BasicBean.class.getClassLoader());
        this.query = in.readString();
        this.errorCode = in.readString();
        this.translation = in.createStringArrayList();
        this.web = new ArrayList<WebBean>();
        in.readList(this.web, WebBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<CarBean> CREATOR = new Parcelable.Creator<CarBean>() {
        @Override
        public CarBean createFromParcel(Parcel source) {
            return new CarBean(source);
        }

        @Override
        public CarBean[] newArray(int size) {
            return new CarBean[size];
        }
    };
}
