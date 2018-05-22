package com.haoyue.notedemos.sql;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.haoyue.notedemos.R;
import com.haoyue.notedemos.app_base.BaseActivity;
import com.haoyue.notedemos.utils.FullyLinearLayoutManager;
import com.haoyue.auxiliary.RecycleViewDividerSegmentate;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GreenDaoActivity extends BaseActivity {

    @BindView(R.id.rvGreenDao)
    RecyclerView mRvGreenDao;
    @BindView(R.id.btinsertOne)
    Button mBtinsertOne;
    @BindView(R.id.btinsertOrReplaceOne)
    Button mBtinsertOrReplaceOne;
    @BindView(R.id.btinsertManyData)
    Button mBtinsertManyData;
    @BindView(R.id.btinsertInTxManyData)
    Button mBtinsertInTxManyData;
    @BindView(R.id.btquerryAll)
    Button mBtquerryAll;
    @BindView(R.id.btqueryBuilder)
    Button mBtqueryBuilder;
    @BindView(R.id.btquerryCount)
    Button mBtquerryCount;
    @BindView(R.id.btupdateOneData)
    Button mBtupdateOneData;
    @BindView(R.id.btupdateList)
    Button mBtupdateList;
    @BindView(R.id.btdeleteDataList)
    Button mBtdeleteDataList;
    @BindView(R.id.btdeleteAll)
    Button mBtdeleteAll;
    @BindView(R.id.btdeleteOneData)
    Button mBtdeleteOneData;
    @BindView(R.id.btdeleteByKey)
    Button mBtdeleteByKey;
    @BindView(R.id.btdeleteByKeyList)
    Button mBtdeleteByKeyList;
    @BindView(R.id.btqueryUserList)
    Button mBtqueryUserList;


    private List<ShopBean> mShopBeanList;
    private GreenDaoUtils mDaoUtils;
    private GreenDaoAdapter mDaoAdapter;
    private String[] name;
    private String[] address;
    private String[] imageUrl;

    private static final String KEY_ = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_dao);
        ButterKnife.bind(this);

        mDaoUtils = GreenDaoUtils.getInstance();//获取Utils对象
        mDaoUtils.init(this);//实例化注册
        mDaoUtils.setUpdataBase();//数据库创建
        mDaoUtils.initLogInfo();
        name = new String[]{"剑七真", "剑八玄", "剑九·轮回：意为“八式往复入轮回”", "剑十·天葬：意为“自生而灭为天葬”",
                "剑十一·涅槃：意为“极而复始，不生不灭是为涅槃”", "剑十二：招式名至今未知，是乃纯粹的剑意，超越人力之招"};
        address = new String[]{"特卖商城", "天猫国际", "京东商城"};
        imageUrl = new String[]{"http://image.hnol.net/c/2017-11/21/11/201711211146417241-5058976.jpg",
                "http://image.hnol.net/c/2017-11/21/11/20171121114712971-5058976.jpg",
                "http://image.hnol.net/c/2017-11/21/11/201711211147394581-5058976.jpg",
                "http://image.hnol.net/c/2017-11/21/11/201711211147575471-5058976.jpg",
                "http://image.hnol.net/c/2017-11/21/11/201711211148412521-5058976.jpg",
                "http://image.hnol.net/c/2017-11/21/11/201711211149022811-5058976.jpg",
                "http://image.hnol.net/c/2017-12/01/08/201712010845568271-239867.jpg"};

        initData();
    }

    private void initData() {
        mShopBeanList = mDaoUtils.querryAll();
        if (mShopBeanList == null || mShopBeanList.size() <= 0) {
            mShopBeanList = mDaoUtils.queryBuilder();
            if (mShopBeanList == null || mShopBeanList.size() <= 0) {
                for (int i = 0; i < 6; i++) {
                    ShopBean shop = new ShopBean();
                    shop.setId(null);
                    shop.setName(name[i]);
                    shop.setPrice((i + 15) * 133 / 28 + "");
                    shop.setSellNumber((i + 10) * 359);
                    shop.setImageUrl(imageUrl[i]);
                    int mod = i % 2;
                    shop.setMerchantAddress(address[mod]);
                    if (mod == 1) {
                        shop.setType(ShopBean.COLLECTION);
                    } else if (mod == 0) {
                        shop.setType(ShopBean.SHOPCART);
                    }
                    mShopBeanList.add(shop);
                }
            }
        }
        mDaoAdapter = new GreenDaoAdapter(GreenDaoActivity.this, mShopBeanList);
        FullyLinearLayoutManager layoutManager = new FullyLinearLayoutManager(GreenDaoActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvGreenDao.setLayoutManager(layoutManager);
        mRvGreenDao.addItemDecoration(new RecycleViewDividerSegmentate(this, LinearLayoutManager.VERTICAL));
        mRvGreenDao.setAdapter(mDaoAdapter);
        boolean initInsertFlag = mDaoUtils.insertInTxManyData(mShopBeanList);
        Log.i("TAG", "initData: 初始化数据插入结果：initInsertFlag ===" + initInsertFlag +
                "初始化插入的数据长度" + mShopBeanList.size());
    }

    @OnClick({R.id.btinsertOne, R.id.btinsertOrReplaceOne, R.id.btinsertManyData, R.id.btinsertInTxManyData,
            R.id.btquerryAll, R.id.btqueryBuilder, R.id.btquerryCount, R.id.btupdateOneData, R.id.btupdateList,
            R.id.btdeleteDataList, R.id.btdeleteAll, R.id.btdeleteOneData, R.id.btdeleteByKey, R.id.btdeleteByKeyList,
            R.id.btqueryUserList})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btinsertOne:
                insertOne();
                break;
            case R.id.btinsertOrReplaceOne:
                insertOrReplaceOne();
                break;
            //该插入方法插入失败
            case R.id.btinsertManyData:
                insertManyData();
                break;
            case R.id.btinsertInTxManyData:
                insertInTxManyData();
                break;
            //querryAll、queryBuilder查询到的结果是一样的。querryCount查询到的数据长度跟前面两个是一样的。
            case R.id.btquerryAll:
                List<ShopBean> querrySomeData = new ArrayList<>();
                //另外，我要查询某几条指定信息的数据，比如我要查询商品列表分类 type下的所有数据，
                //则先获取数据库里的所有数据DBTotalData，然后再在DBTotalData里面按照type属性查询符合要求的数据：
                List<ShopBean> DBTotalData = mDaoUtils.querryAll();
                for (int i = 0; i < DBTotalData.size(); i++) {
                    if (DBTotalData.get(i).getType() == ShopBean.SHOPCART) {
                        querrySomeData.add(DBTotalData.get(i));
                    }
                }
                mDaoAdapter.refreshData(querrySomeData);
                Log.i("TAG", "btquerryAll: querryAll().size ===" + DBTotalData.size());
                break;
            case R.id.btqueryBuilder:
                List<ShopBean> querryBuilderSomeData = new ArrayList<>();
                List<ShopBean> totalData = mDaoUtils.querryAll();
                for (int i = 0; i < totalData.size(); i++) {
                    if (totalData.get(i).getType() == ShopBean.COLLECTION) {
                        querryBuilderSomeData.add(totalData.get(i));
                    }
                }
                mDaoAdapter.refreshData(querryBuilderSomeData);
                Log.i("TAG", "btqueryBuilder: queryBuilder().size ===" + totalData.size());
                break;
            case R.id.btquerryCount:
                Log.i("TAG", "btquerryCount: qquerryCount ===" + mDaoUtils.querryCount());
                break;
            case R.id.btupdateOneData:
                updateOneData();
                break;
            case R.id.btupdateList:
                updateList();
                break;
            case R.id.btdeleteDataList:
                deleteDataList();
                break;
            case R.id.btdeleteAll:
                boolean deleteAllFlag = mDaoUtils.deleteAll();
                Log.i("TAG", "btdeleteAll： 删除全部数据deleteAllFlag: deleteAllFlag ===" + deleteAllFlag);
                mShopBeanList = mDaoUtils.querryAll();
                mDaoAdapter.refreshData(mShopBeanList);
                break;
            case R.id.btdeleteOneData:
                deleteOneData();
                break;
            case R.id.btdeleteByKey:
                Toast.makeText(this, "暂未开放,敬请期待", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btdeleteByKeyList:
                Toast.makeText(this, "暂未开放,敬请期待", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btqueryUserList:
                int mod = (int) (System.nanoTime() % 2);
                if (mod == 0) {
                    //按Id查询
                    mShopBeanList = mDaoUtils.queryById(1, 100, 5);
                }else {
                    //按merchantAddress查询
                    mShopBeanList = mDaoUtils.querryByCharacter("天猫国际");
                }
                mDaoAdapter.refreshData(mShopBeanList);
                break;
            default:
                break;
        }
    }

    private void insertOne() {
        Log.i("TAG", "btinsertOne: 单个插入btinsertOne前的数据长度" + mDaoUtils.querryCount());
        ShopBean insertOne = new ShopBean();
        insertOne.setId(null);
        insertOne.setName("btinsertOne");
        insertOne.setPrice("￥208");
        insertOne.setSellNumber(37958);
        insertOne.setImageUrl(imageUrl[6]);
        insertOne.setMerchantAddress(address[1]);
        insertOne.setType(ShopBean.SHOPCART);
        boolean insertOneFlag = mDaoUtils.insertOne(insertOne);

        mShopBeanList = mDaoUtils.querryAll();
        Log.i("TAG", "onViewClicked: 单个插入insertOneFlag ===" + insertOneFlag + "\n" +
                "插入后数据长度mShopBeanList.size()" + mShopBeanList.size());
        mDaoAdapter.refreshData(mShopBeanList);
    }

    private void insertOrReplaceOne() {
        Log.i("TAG", "btinsertOrReplaceOne: 单个插入btinsertOrReplaceOne前的数据长度" + mDaoUtils.querryCount());
        ShopBean insertOrReplace = new ShopBean();
        insertOrReplace.setId(null);
        insertOrReplace.setName("btinsertOrReplaceOne");
        insertOrReplace.setPrice("￥208");
        insertOrReplace.setSellNumber(37958);
        insertOrReplace.setImageUrl(imageUrl[6]);
        insertOrReplace.setMerchantAddress(address[1]);
        insertOrReplace.setType(ShopBean.SHOPCART);
        boolean insertOrReplaceFlag = mDaoUtils.insertOrReplaceOne(insertOrReplace);
        mShopBeanList = mDaoUtils.querryAll();
        Log.i("TAG", "btinsertOrReplaceOne: 单个插入insertOrReplaceFlag ===" + insertOrReplaceFlag + "\n" +
                "OrReplaceOne插入后数据长度mShopBeanList.size()" + mShopBeanList.size());
        mDaoAdapter.refreshData(mShopBeanList);
    }

    private void insertManyData() {
        Log.i("TAG", "onViewClicked: 执行insertManyData方法插入失败");
        Log.i("TAG", "btinsertManyData: 单个插入btinsertManyData前的数据长度" + mDaoUtils.querryCount());
        for (int i = 0; i < 6; i++) {
            ShopBean shop = new ShopBean();
            shop.setId((long) (i * i * 11293));
            shop.setName(address[2] + "**" + name[i]);
            shop.setPrice((i + 15) * 1330 + "");
            shop.setSellNumber((i + 10) * 3590);
            shop.setImageUrl(imageUrl[i]);
            int mod = i % 2;
            shop.setMerchantAddress(address[2]);
            if (mod == 1) {
                shop.setType(ShopBean.COLLECTION);
            } else if (mod == 0) {
                shop.setType(ShopBean.SHOPCART);
            }
            mShopBeanList.add(shop);
        }
        boolean btinsertManyDataFlag = mDaoUtils.insertManyData(mShopBeanList);
        mShopBeanList = mDaoUtils.querryAll();
        Log.i("TAG", "insertManyData: 单个插入btinsertManyDataFlag ===" + btinsertManyDataFlag + "\n" +
                "insertManyData插入后数据长度mShopBeanList.size()" + mShopBeanList.size());
        mDaoAdapter.refreshData(mShopBeanList);
    }

    private void insertInTxManyData() {
        Log.i("TAG", "btinsertManyData: 批量插入btinsertManyData前的数据长度" + mDaoUtils.querryCount() +
                "querryAll方法查询到的数据长度" + mDaoUtils.querryAll().size());
        for (int i = 0; i < 6; i++) {
            ShopBean shop = new ShopBean();
            shop.setId(null);
            shop.setName(address[2] + "*||||||*" + name[i]);
            shop.setPrice((i + 15) * 1330 + "");
            shop.setSellNumber((i + 10) * 3590);
            shop.setImageUrl(imageUrl[i]);
            int mod = i % 2;
            shop.setMerchantAddress(address[2]);
            if (mod == 1) {
                shop.setType(ShopBean.COLLECTION);
            } else if (mod == 0) {
                shop.setType(ShopBean.SHOPCART);
            }
            mShopBeanList.add(shop);
        }
        boolean insertInTxFlag = mDaoUtils.insertInTxManyData(mShopBeanList);
        mShopBeanList = mDaoUtils.querryAll();
        Log.i("TAG", "btinsertInTxManyData: 批量插入insertInTxFlag ===" + insertInTxFlag + "\n" +
                "insertManyData插入后数据长度mShopBeanList.size()" + mShopBeanList.size());
        mDaoAdapter.refreshData(mShopBeanList);
    }

    private void updateOneData() {
        //获取要更新的数据对象
        ShopBean shopBean = mShopBeanList.get(0);
        //设置更新的内容
        shopBean.setName("秋水浮萍任缥缈");
        //更新数据集合
        boolean updateOneFlag = mDaoUtils.updateOneData(shopBean);
        Log.i("TAG", "btupdateOneData: 更新单一条数据的结果updateOneFlag ===" + updateOneFlag);
        //获取更新后的所有数据并加载出来
        mShopBeanList = mDaoUtils.querryAll();
        mDaoAdapter.refreshData(mShopBeanList);
    }

    private void updateList() {
        //获取要更新的数据对象
        ShopBean shopBean1 = mShopBeanList.get(1);
        ShopBean shopBean2 = mShopBeanList.get(2);
        ShopBean shopBean3 = mShopBeanList.get(3);
        //设置更新的内容
        shopBean1.setName("俏如来");
        shopBean2.setName("宫本总司");
        shopBean3.setName("剑无极");
        List<ShopBean> updateList = new ArrayList<>();
        updateList.add(shopBean1);
        updateList.add(shopBean2);
        updateList.add(shopBean3);
        //更新数据集合
        boolean updateListFlag = mDaoUtils.updateList(updateList);
        Log.i("TAG", "btupdateList: 批量更新数据的结果updateListFlag ===" + updateListFlag);
        //获取更新后的所有数据并加载出来
        mShopBeanList = mDaoUtils.querryAll();
        mDaoAdapter.refreshData(mShopBeanList);
    }

    private void deleteDataList() {
        //获取要删除的数据对象
        ShopBean deleteList01 = mShopBeanList.get(6);
        ShopBean deleteList02 = mShopBeanList.get(7);
        List<ShopBean> deleteList = new ArrayList<>();
        deleteList.add(deleteList01);
        deleteList.add(deleteList02);
        //删除数据集合
        boolean deleteLstFlag = mDaoUtils.deleteDataList(deleteList);
        Log.i("TAG", "btdeleteDataList: 批量删除数据的结果deleteLstFlag ===" + deleteLstFlag);
        //查询删除后剩余的所有数据并加载出来
        mShopBeanList = mDaoUtils.querryAll();
        mDaoAdapter.refreshData(mShopBeanList);
    }

    private void deleteOneData() {
        //获取最后一条数据
        ShopBean deleteOne = mShopBeanList.get((int) mDaoUtils.querryCount() - 1);
        //删除最后一条数据
        boolean deleteOneFlag = mDaoUtils.deleteOneData(deleteOne);
        //查询删除后剩余的所有数据并加载出来
        mShopBeanList = mDaoUtils.querryAll();
        mDaoAdapter.refreshData(mShopBeanList);
        Log.i("TAG", "btdeleteOneData： 删除某一条数据deleteOneFlag ===" + deleteOneFlag);
    }
}
