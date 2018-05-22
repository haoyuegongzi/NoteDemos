package com.haoyue.notedemos.retrofit.mvp.beaninfo;

import java.util.List;

/**
 * 作者：chen1 on 2018/3/8 13
 * E—Mail：chen126jie@163.com
 * TODO：
 * @author chen1
 */

public class DouBanBean {
    /**https://api.douban.com/v2/book/search?q=%E5%B0%8F%E7%8E%8B%E5%AD%90&tag=&start=0&count=3**/
    
    public String count;
    public String start;
    public String total;
    public List<BooksBean> books;

    @Override
    public String toString() {
        return "DouBanBean{" +
                "count='" + count + '\'' +
                ", start='" + start + '\'' +
                ", total='" + total + '\'' +
                ", books=" + books +
                '}';
    }

    public static class BooksBean {
        public RatingBean rating;
        public String subtitle;
        public String pubdate;
        public String origin_title;
        public String image;
        public String binding;
        public String catalog;
        public String pages;
        public ImagesBean images;
        public String alt;
        public String id;
        public String publisher;
        public String isbn10;
        public String isbn13;
        public String title;
        public String url;
        public String alt_title;
        public String author_intro;
        public String summary;
        public String price;
        public String ebook_url;
        public String ebook_price;
        public SeriesBean series;
        public List<String> author;
        public List<TagsBean> tags;
        public List<String> translator;

        @Override
        public String toString() {
            return "BooksBean{" +
                    "rating=" + rating +
                    ", subtitle='" + subtitle + '\'' +
                    ", pubdate='" + pubdate + '\'' +
                    ", origin_title='" + origin_title + '\'' +
                    ", image='" + image + '\'' +
                    ", binding='" + binding + '\'' +
                    ", catalog='" + catalog + '\'' +
                    ", pages='" + pages + '\'' +
                    ", images=" + images +
                    ", alt='" + alt + '\'' +
                    ", id='" + id + '\'' +
                    ", publisher='" + publisher + '\'' +
                    ", isbn10='" + isbn10 + '\'' +
                    ", isbn13='" + isbn13 + '\'' +
                    ", title='" + title + '\'' +
                    ", url='" + url + '\'' +
                    ", alt_title='" + alt_title + '\'' +
                    ", author_intro='" + author_intro + '\'' +
                    ", summary='" + summary + '\'' +
                    ", price='" + price + '\'' +
                    ", ebook_url='" + ebook_url + '\'' +
                    ", ebook_price='" + ebook_price + '\'' +
                    ", series=" + series +
                    ", author=" + author +
                    ", tags=" + tags +
                    ", translator=" + translator +
                    '}';
        }

        public static class RatingBean {
            public String max;
            public String numRaters;
            public String average;
            public String min;

            @Override
            public String toString() {
                return "RatingBean{" +
                        "max='" + max + '\'' +
                        ", numRaters='" + numRaters + '\'' +
                        ", average='" + average + '\'' +
                        ", min='" + min + '\'' +
                        '}';
            }
        }

        public static class ImagesBean {
            public String small;
            public String large;
            public String medium;

            @Override
            public String toString() {
                return "ImagesBean{" +
                        "small='" + small + '\'' +
                        ", large='" + large + '\'' +
                        ", medium='" + medium + '\'' +
                        '}';
            }
        }

        public static class SeriesBean {
            public String id;
            public String title;

            @Override
            public String toString() {
                return "SeriesBean{" +
                        "id='" + id + '\'' +
                        ", title='" + title + '\'' +
                        '}';
            }
        }

        public static class TagsBean {
            public String count;
            public String name;
            public String title;

            @Override
            public String toString() {
                return "TagsBean{" +
                        "count='" + count + '\'' +
                        ", name='" + name + '\'' +
                        ", title='" + title + '\'' +
                        '}';
            }
        }
    }
}
