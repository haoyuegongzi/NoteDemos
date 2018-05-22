package com.haoyue.notedemos.retrofit_learn;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 作者：chen1 on 2018/1/16 16
 * E—Mail：chen126jie@163.com
 * TODO：
 * @author chen1
 */

public class Translation {

    public content content;

    public static class content implements Parcelable {
        public String from;
        public String to;
        public String vendor;
        public String out;
        public int errNo;

        @Override
        public String toString() {
            return "content{" +
                    "from='" + from + '\'' +
                    ", to='" + to + '\'' +
                    ", vendor='" + vendor + '\'' +
                    ", out='" + out + '\'' +
                    ", errNo=" + errNo +
                    '}';
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.errNo);
            dest.writeString(this.to);
            dest.writeString(this.vendor);
            dest.writeString(this.out);
            dest.writeString(this.from);
        }

        public content() {
        }

        protected content(Parcel in) {
            this.errNo = in.readInt();
            this.to = in.readString();
            this.vendor = in.readString();
            this.out = in.readString();
            this.from = in.readString();
        }

        public static final Parcelable.Creator<content> CREATOR = new Parcelable.Creator<content>() {
            @Override
            public content createFromParcel(Parcel source) {
                return new content(source);
            }

            @Override
            public content[] newArray(int size) {
                return new content[size];
            }
        };
    }
}
