package cn.nj.www.my_module.view.banner.demo;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;

import cn.nj.www.my_module.tools.ImageLoaderUtil;
import cn.nj.www.my_module.view.banner.holder.Holder;


/**
 * Created by Sai on 15/8/4.
 * 网络图片加载例子
 */
public class NetworkImageHolderView implements Holder<String>
{
    private ImageView imageView;

    @Override
    public View createView(Context context)
    {
        //你可以通过layout文件来创建，也可以像我一样用代码创建，不一定是Image，任何控件都可以进行翻页
        imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }

    @Override
    public void UpdateUI(Context context, int position, String data)
    {
        imageView.setBackgroundColor(Color.GRAY);
//        GeneralUtils.setImageViewWithUrl(context, data, imageView, R.color.gray);
        ImageLoaderUtil.getInstance().initImage(context,data,imageView,"");
    }
}
