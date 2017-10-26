package com.example.pengshan.litepaldemo;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;
import android.text.TextUtils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ChenZhe.
 * @date 2017/10/26
 */

public class DataUtils {

    /**
     * 联系人显示名称
     **/
    private static final int PHONES_DISPLAY_NAME_INDEX = 0;
    /**
     * 电话号码
     **/
    private static final int PHONES_NUMBER_INDEX = 1;
    /**
     * 头像ID
     **/
    private static final int PHONES_PHOTO_ID_INDEX = 2;
    /**
     * 联系人的ID
     **/
    private static final int PHONES_CONTACT_ID_INDEX = 3;
    /**
     *  库 phone表字段 
     **/
    private static final String[] PHONES_PROJECTION = new String[]{
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
            , ContactsContract.CommonDataKinds.Phone.NUMBER
            , ContactsContract.CommonDataKinds.Photo.PHOTO_ID
            , ContactsContract.CommonDataKinds.Phone.CONTACT_ID};


    /**
     * 得到手机通讯录联系人信息
     **/
    public static  List<ContactBean> getPhoneContacts() {

        List<ContactBean> address=new ArrayList<>();
        ContentResolver resolver = BaseApplication.getAppContext().getContentResolver();
        // 获取手机联系人  
        Cursor phoneCursor = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,PHONES_PROJECTION,null,null,null);
        if (phoneCursor != null) {
            while (phoneCursor.moveToNext()) {
                ContactBean bean=new ContactBean();
                //得到手机号码  
                String phoneNumber = phoneCursor.getString(PHONES_NUMBER_INDEX).replace(" ","");
                phoneNumber=phoneNumber.replace("+86","");
                bean.setPhoneNumber(phoneNumber);
                //当手机号码为空的或者为空字段 跳过当前循环  
                if (TextUtils.isEmpty(phoneNumber))
                    continue;
                //得到联系人名称  
                String contactName = phoneCursor.getString(PHONES_DISPLAY_NAME_INDEX);
                bean.setContactName(contactName);
                bean.setName(contactName);
                //得到联系人ID  
                Long contactid = phoneCursor.getLong(PHONES_CONTACT_ID_INDEX);
                bean.setContactid(contactid+"");
                //得到联系人头像ID  
                Long photoid = phoneCursor.getLong(PHONES_PHOTO_ID_INDEX);
                bean.setPhotoid(photoid+"");
                //得到联系人头像Bitamp  
                Bitmap contactPhoto = null;
                //photoid 大于0 表示联系人有头像 如果没有给此人设置头像则给他一个默认的  
                if (photoid > 0) {
                    Uri uri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, contactid);
                    InputStream input = ContactsContract.Contacts.openContactPhotoInputStream(resolver, uri);
                    contactPhoto = BitmapFactory.decodeStream(input);
                } else {
                    contactPhoto = BitmapFactory.decodeResource(BaseApplication.getAppContext().getResources(), R.mipmap.ic_launcher);
                }
                bean.setContactPhoto(contactPhoto);
//                if (bean.getPhoneNumber().trim().length()==11&&"1".equals(bean.getPhoneNumber().trim().substring(0,1))){
                    address.add(bean);
//                }
            }
            phoneCursor.close();
        }


        return address;
    }
}
