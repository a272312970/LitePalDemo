package com.example.pengshan.litepaldemo;

import android.graphics.Bitmap;

import org.litepal.crud.DataSupport;

/**
 * @author ChenZhe.
 * @date 2017/10/26
 */

public class ContactLitePalBean extends DataSupport{
    private int id;
    private String phoneNumber;//得到手机号码
    private String contactName;//得到联系人名称 
    private String contactid; //得到联系人ID  
    private String photoid; ////得到联系人头像ID 
    private byte[] bitmapByte;

    public byte[] getBitmapByte() {
        return bitmapByte;
    }

    public void setBitmapByte(byte[] bitmapByte) {
        this.bitmapByte = bitmapByte;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactid() {
        return contactid;
    }

    public void setContactid(String contactid) {
        this.contactid = contactid;
    }

    public String getPhotoid() {
        return photoid;
    }

    public void setPhotoid(String photoid) {
        this.photoid = photoid;
    }
}
