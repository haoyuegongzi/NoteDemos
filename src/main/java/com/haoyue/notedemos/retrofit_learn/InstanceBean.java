package com.haoyue.notedemos.retrofit_learn;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 作者：chen1 on 2018/3/7 15
 * E—Mail：chen126jie@163.com
 * TODO：
 * @author chen1
 */

public class InstanceBean implements Parcelable {
    /**
     * status : 1
     * content : {"from":"en-EU","to":"zh-CN","out":"示例","vendor":"ciba","err_no":0}
     */
    public String status;
    public ContentBean content;

    @Override
    public String toString() {
        return "InstanceBean{" +
                "status='" + status + '\'' +
                ", content=" + content +
                '}';
    }

    public static class ContentBean {
        /**
         * from : en-EU
         * to : zh-CN
         * out : 示例
         * vendor : ciba
         * err_no : 0
         */
        public String from;
        public String to;
        public String out;
        public String vendor;
        public String err_no;

        @Override
        public String toString() {
            return "ContentBean{" +
                    "from='" + from + '\'' +
                    ", to='" + to + '\'' +
                    ", out='" + out + '\'' +
                    ", vendor='" + vendor + '\'' +
                    ", err_no='" + err_no + '\'' +
                    '}';
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.status);
        dest.writeParcelable((Parcelable) this.content, flags);
    }

    public InstanceBean() {

    }

    protected InstanceBean(Parcel in) {
        this.status = in.readString();
        this.content = in.readParcelable(ContentBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<InstanceBean> CREATOR = new Parcelable.Creator<InstanceBean>() {
        @Override
        public InstanceBean createFromParcel(Parcel source) {
            return new InstanceBean(source);
        }

        @Override
        public InstanceBean[] newArray(int size) {
            return new InstanceBean[size];
        }
    };
}
