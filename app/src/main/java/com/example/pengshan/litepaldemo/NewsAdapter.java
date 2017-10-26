package com.example.pengshan.litepaldemo;

import android.content.Context;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by pengshan on 2017/9/13.
 */

public class NewsAdapter extends CommonAdapter<NewsBean> {

    public NewsAdapter(Context context, List<NewsBean> datas) {
        super(context, R.layout.item_news, datas);
    }

    @Override
    protected void convert(ViewHolder holder, NewsBean bean, int position) {
            holder.setText(R.id.tv_title,bean.getTitle());
            holder.setText(R.id.tv_id,bean.getId() + "");
            holder.setText(R.id.tv_commentCount,bean.getCommentCount() + "");
            holder.setText(R.id.tv_content,bean.getContent());
            holder.setText(R.id.tv_publishDate,longToDate2(bean.getPublishDate()));
            holder.setText(R.id.tv_link,bean.getLink());
    }

    public void setData(List<NewsBean> data){
        if(data != null) {
            this.mDatas = data;
        } else {
            this.mDatas.clear();
        }
        this.notifyDataSetChanged();

    }



    public static String longToDate2(Date date) {
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd ");
        return sd.format(date);
    }
}
