package com.haoyue.notedemos.nestscrollview;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：chen1 on 2018/2/8 17
 * E—Mail：chen126jie@163.com
 * TODO：
 */

public class InitData {
    public static InitData instance;

    public static InitData getInstance(){
        if (instance == null) {
            synchronized (InitData.class){
                if (instance == null) {
                    instance = new InitData();
                }
            }
        }
        return instance;
    }

    public List<String> integerList(){
        List<String> integerList = new ArrayList<>();
        integerList.add("剑一破");
        integerList.add("剑二空");
        integerList.add("剑三飞");
        integerList.add("剑四灭");
        integerList.add("剑五虚");
        integerList.add("剑六绝");
        integerList.add("剑七真");
        integerList.add("剑八玄");
        integerList.add("剑九轮回");
        integerList.add("剑十天葬");
        integerList.add("剑十一涅槃");
        integerList.add("剑十二心");
        integerList.add("剑一破");
        integerList.add("剑二空");
        integerList.add("剑三飞");
        integerList.add("剑四灭");
        integerList.add("剑五虚");
        integerList.add("剑六绝");
        integerList.add("剑七真");
        integerList.add("剑八玄");
        integerList.add("剑九轮回");
        integerList.add("剑十天葬");
        integerList.add("剑十一涅槃");
        integerList.add("剑十二心");
        integerList.add("剑一破");
        integerList.add("剑二空");
        integerList.add("剑三飞");
        integerList.add("剑四灭");
        integerList.add("剑五虚");
        integerList.add("剑六绝");
        integerList.add("剑七真");
        integerList.add("剑八玄");
        integerList.add("剑九轮回");
        integerList.add("剑十天葬");
        integerList.add("剑十一涅槃");
        integerList.add("剑十二心");
        return integerList;
    }
}
