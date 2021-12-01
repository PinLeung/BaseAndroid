package com.leung.baseactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.jeremyliao.liveeventbus.LiveEventBus;
import com.leung.baseactivity.databinding.ActivityMainBinding;
import com.youyu.common.base.BaseActivity;
import com.youyu.common.router.PathConfig;

import org.jetbrains.annotations.NotNull;

//路由路径
@Route(path = PathConfig.TO_MAIN)
public class MainActivity extends BaseActivity<VM, ActivityMainBinding> {

    @Autowired(name = "data1")
    String data1;


    @Override
    public void initView() {
        super.initView();
        addListent();
    }

    private void addListent() {
        //跨界面通信接收
        LiveEventBus.get("key",BaseData.class).observe(this,data->{

        });
        //跨界面通信发送
        LiveEventBus.get("key").post(data1);
    }

    public void getData() {
        //网络请求
        getVm().loadInfo("1","2");
    }

    public void jump(){

        //路由
        ARouter.getInstance().build(PathConfig.TO_MAIN)
                .withString("data1", data1)
                .withInt("form", 0x1)
                .navigation();
    }




    @Override
    public void observe() {
        super.observe();
        //处理网络请求返回数据
        getVm().getBaseData().observe(this,data->{
            BaseData baseData=data;
        });



    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @NotNull
    @Override
    protected Class<VM> getVmClass() {
        return VM.class;
    }
}