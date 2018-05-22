package com.haoyue.notedemos.sql;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 *
 * @author chen1
 * @date 2017/11/29
 * TO DO:
 */

@Entity
public class ShopBean {
    public static final int SHOPCART = 0x001;
    public static final int COLLECTION = 0x002;

    /**
     * @Entity 表明这个实体类会在数据库中生成一个与之相对应的表。
     * @Id 对应数据表中的 Id 字段，有了解数据库的话，是一条数据的唯一标识。
     * @Property(nameInDb = “STUDENTNUM”)表名这个属性对应数据表中的 STUDENTNUM 字段。
     * @Property 可以自定义字段名，注意外键不能使用该属性
     * @NotNull 该属性值不能为空
     * @Transient 该属性不会被存入数据库中
     * @Unique 表名该属性在数据库中只能有唯一值
     */

    //不能用int(Id表示主键且主键不能用int;autoincrement=true表示主键自增)
    @org.greenrobot.greendao.annotation.Id(autoincrement = true)
    private Long Id;

    //商品名称 (unique 表示该属性必须在数据库中是唯一的值)
//    @Unique
    private String name;
    //商品价格
    private String price;
    //已售数量
    private int sellNumber;
    //图标地址
    private String imageUrl;
    //商家地址
    private String merchantAddress;
    //商品列表分类
    private int type;

    @Generated(hash = 1537464301)
    public ShopBean(Long Id, String name, String price, int sellNumber,
                    String imageUrl, String merchantAddress, int type) {
        this.Id = Id;
        this.name = name;
        this.price = price;
        this.sellNumber = sellNumber;
        this.imageUrl = imageUrl;
        this.merchantAddress = merchantAddress;
        this.type = type;
    }

    @Generated(hash = 748345971)
    public ShopBean() {
    }

    public Long getId() {
        return this.Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getSellNumber() {
        return this.sellNumber;
    }

    public void setSellNumber(int sellNumber) {
        this.sellNumber = sellNumber;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getMerchantAddress() {
        return this.merchantAddress;
    }

    public void setMerchantAddress(String merchantAddress) {
        this.merchantAddress = merchantAddress;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
