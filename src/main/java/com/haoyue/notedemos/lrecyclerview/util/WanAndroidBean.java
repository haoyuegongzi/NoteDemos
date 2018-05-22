package com.haoyue.notedemos.lrecyclerview.util;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：chen1 on 2018/3/19 09
 * E—Mail：chen126jie@163.com
 * TODO：
 */

public class WanAndroidBean implements Serializable {

    public DataBean data;
    public String errorCode;
    public String errorMsg;


    public static class DataBean {
        public String curPage;
        public String offset;
        public boolean over;
        public String pageCount;
        public String size;
        public String total;
        public List<DatasBean> datas;

        public static class DatasBean {

            public String apkLink;
            public String author;
            public String chapterId;
            public String chapterName;
            public boolean collect;
            public String courseId;
            public String desc;
            public String envelopePic;
            public boolean fresh;
            public String id;
            public String link;
            public String niceDate;
            public String origin;
            public String projectLink;
            public long publishTime;
            public String superChapterId;
            public String superChapterName;
            public String title;
            public String type;
            public String visible;
            public String zan;
            public List<?> tags;
        }
    }
}
