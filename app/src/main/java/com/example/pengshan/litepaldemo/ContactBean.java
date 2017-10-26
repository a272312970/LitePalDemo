package com.example.pengshan.litepaldemo;

import android.graphics.Bitmap;

/**
 * 联系人Bean
 */
public class ContactBean extends GroupMemberBean{
    private String phoneNumber;//得到手机号码
    private String contactName;//得到联系人名称 
    private String contactid; //得到联系人ID  
    private String photoid; ////得到联系人头像ID 
    private Bitmap contactPhoto;//得到联系人头像Bitamp 

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setContactid(String contactid) {
        this.contactid = contactid;
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

    public void setPhotoid(String photoid) {
        this.photoid = photoid;
    }

    public void setContactPhoto(Bitmap contactPhoto) {
        this.contactPhoto = contactPhoto;
    }

    public String getContactid() {
        return contactid;
    }

    public String getPhotoid() {
        return photoid;
    }

    public Bitmap getContactPhoto() {
        return contactPhoto;
    }

    @Override
    public String toString() {
        return "ContactBean{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", contactName='" + contactName + '\'' +
                ", contactid='" + contactid + '\'' +
                ", photoid='" + photoid + '\'' +
                ", contactPhoto=" + contactPhoto +
                '}';
    }
}
