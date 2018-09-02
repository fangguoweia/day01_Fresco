package com.bwei.day01_Fresco;

import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    //动态：鱼 http://n.sinaimg.cn/tech/transform/511/w288h223/20180828/Vs2U-hifuvpi2224632.gif
    //动态：鸡 http://img.zcool.cn/community/0139505792e5fc0000018c1bbb7271.gif
    //静态：http://pic39.nipic.com/20140226/18071023_164300608000_2.jpg
    public static final String mGifUrl = "http://n.sinaimg.cn/tech/transform/511/w288h223/20180828/Vs2U-hifuvpi2224632.gif";

    @MyBandView(R.id.my_image_view)
    SimpleDraweeView draweeView;
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyButterKnife.bind(this);
        MyButterKnife.process(this);

        uri = Uri.parse(mGifUrl);
        //控制图片加载的一些特性
        AbstractDraweeController controller = Fresco.newDraweeControllerBuilder()
                //第二种设置图片地址
                .setUri(uri)
                //设置可以重试 （重试4次）
                .setTapToRetryEnabled(true)
                //设置自动播放
                .setAutoPlayAnimations(true)
                .setOldController(draweeView.getController())
                .build();
        draweeView.setController(controller);
    }
    @MyOnClick(R.id.my_image_view)
    public void onViewClicked(){
        Toast.makeText(this,"hi",Toast.LENGTH_SHORT).show();
    }
}