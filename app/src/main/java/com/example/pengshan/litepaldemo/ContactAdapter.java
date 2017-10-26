package com.example.pengshan.litepaldemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by pengshan on 2017/9/13.
 */

public class ContactAdapter extends CommonAdapter<ContactLitePalBean> {

    public ContactAdapter(Context context, List<ContactLitePalBean> datas) {
        super(context, R.layout.item_contact, datas);
    }

    @Override
    protected void convert(ViewHolder holder, ContactLitePalBean bean, int position) {
            holder.setText(R.id.tv_name,bean.getContactName());
            holder.setText(R.id.tv_phone,bean.getPhoneNumber()+ "");
            holder.setText(R.id.tv_photo_id,bean.getPhotoid() + "");
        byte[] bitmapByte = bean.getBitmapByte();
        if(bitmapByte != null && bitmapByte.length > 0){
            Bitmap bitmap = BitmapFactory.decodeByteArray(bitmapByte, 0, bitmapByte.length);
            holder.setImageBitmap(R.id.iv_photo,bitmap);
        }else{
            holder.setImageResource(R.id.iv_photo,R.mipmap.ic_launcher);
        }
    }

    public void setData(List<ContactLitePalBean> data){
        if(data != null) {
            this.mDatas = data;
        } else {
            this.mDatas.clear();
        }
        this.notifyDataSetChanged();

    }


}
