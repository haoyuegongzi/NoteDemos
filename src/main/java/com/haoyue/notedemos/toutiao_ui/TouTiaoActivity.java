package com.haoyue.notedemos.toutiao_ui;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.haoyue.notedemos.R;
import com.haoyue.notedemos.toutiao_ui.ImageBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TouTiaoActivity extends AppCompatActivity{

    @BindView(R.id.rvList)
    RecyclerView rvList;
    @BindView(R.id.llBar)
    LinearLayout llBar;

    List<ImageBean> beanList;
    ToutiaoAdapter toutiaoAdapter;
    RequestOptions options;
    float x = 0;
    float y = 0;

    Animation viewUpSlide;
    Animation viewDownSlide;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tou_tiao);
        ButterKnife.bind(this);
        addParams();
        toutiaoAdapter = new ToutiaoAdapter(getApplicationContext(), options);
        LinearLayoutManager layout = new LinearLayoutManager(getApplicationContext());
        layout.setOrientation(LinearLayout.VERTICAL);
        rvList.setLayoutManager(layout);
        rvList.setAdapter(toutiaoAdapter);
        toutiaoAdapter.refreshData(beanList);
        addListener();

        findViewById(R.id.llBar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //smoothScrollToPosition()指定position的item显示在界面上并置顶或者置底。
                rvList.smoothScrollToPosition(beanList.size()-1);
            }
        });
    }

    public void addListener(){
        /**
         * dx，dy分别表示 在x方向和y方向滑动的值，这个值有正负。
         * dx>0 则表示右滑，dx<0 表示左滑
         * dy<0 表示上滑， dy>0 表示下滑;通过这几个参数就可以监听 滑动方向的状态。
         * 但是还有两种情况，不能通过 dx，dy直接判断出来。那就是顶部top状态和底部bottom状态。需要借助一些其他参数，结合来判断。
         */
        rvList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int downSlide = 0xff;
                int upSlide = 0xee;
                Log.i("LOGI", "dy === " + dy);
                /**
                 * 利用View的一个方法:public boolean canScrollVertically (int direction)
                 * 这个方法是判断View在竖直方向是否还能向上，向下滑动。-1表示向上， 1表示向下.
                 */
                if(!rvList.canScrollVertically(-1)){//不能再向上滑动：已经到顶了
                    Log.i("TAG", "onScrolled: 不能再向上滑动：已经到顶了");
                }else if(!rvList.canScrollVertically(1)){//不能再向下滑动：已经到底了
                    Log.i("TAG", "onScrolled: 不能再向下滑动：已经到底了");
                }else if(dy > 0){
                    if(downSlide == 0xff){
                        downSlide++;
                        Log.i("TAG", "onScrolled: downSlide===" + downSlide);
                        llBar.startAnimation(viewUpSlide);
                    }
                }else if(dy < 0){
                    if(upSlide == 0xee){
                        upSlide++;
                        Log.i("TAG", "onScrolled: upSlide===" + upSlide);
                        llBar.startAnimation(viewDownSlide);
                    }
                }else if(dy == 0){

                }
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void addParams(){
        if (beanList == null) {
            beanList = new ArrayList<>();
            options = new RequestOptions()
                    .placeholder(getDrawable(R.mipmap.toutiao))
                    .error(R.mipmap.ic_launcher_round)
                    .diskCacheStrategy(DiskCacheStrategy.ALL);
        }else {
            beanList.clear();
        }
        beanList.add(new ImageBean("http://image.hnol.net/c/2018-01/02/07/201801020711444121-5058976.jpg"));
        beanList.add(new ImageBean("http://image.hnol.net/c/2018-01/02/07/20180102071158521-5058976.jpg"));
        beanList.add(new ImageBean("http://image.hnol.net/c/2018-01/02/07/201801020712114881-5058976.jpg"));
        beanList.add(new ImageBean("http://image.hnol.net/c/2018-01/02/07/201801020712262331-5058976.jpg"));
        beanList.add(new ImageBean("http://image.hnol.net/c/2018-01/02/07/201801020712411461-5058976.jpg"));
        beanList.add(new ImageBean("http://image.hnol.net/c/2018-01/02/07/201801020713007781-5058976.jpg"));
        beanList.add(new ImageBean("http://image.hnol.net/c/2018-01/02/07/201801020713149351-5058976.jpg"));
        beanList.add(new ImageBean("http://image.hnol.net/c/2018-01/02/07/201801020713294911-5058976.jpg"));
        viewUpSlide = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.toutiao_navigation2);
        viewDownSlide = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.toutiao_navigation);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        switch (event.getAction()){
//            case MotionEvent.ACTION_DOWN:
//                x = event.getX();
//                y = event.getY();
//                break;
//            case MotionEvent.ACTION_MOVE:
//                float move = event.getY();
//                if((move - y) > 0){
//                    llBar.startAnimation(viewUpSlide);
//                }else {
//                    llBar.startAnimation(viewDownSlide);
//                }
//                Log.i("TAG", "滑动距离: " + (move - y));
//                break;
//            case MotionEvent.ACTION_UP:
//
//                break;
//            default:
//                break;
//        }
        return super.onTouchEvent(event);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
