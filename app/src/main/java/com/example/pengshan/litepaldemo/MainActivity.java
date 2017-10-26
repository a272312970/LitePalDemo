package com.example.pengshan.litepaldemo;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_READ_CONTACTS = 200;
    @BindView(R.id.t_create)
    Button mTCreate;
    @BindView(R.id.bt_upgrade)
    Button mBtUpgrade;
    @BindView(R.id.bt_add)
    Button mBtAdd;
    @BindView(R.id.bt_delete)
    Button mBtDelete;
    @BindView(R.id.bt_update)
    Button mBtUpdate;
    @BindView(R.id.bt_deleteall)
    Button mBtDeleteall;
    @BindView(R.id.bt_find)
    Button mBtFind;
    @BindView(R.id.bt_jump)
    Button mBtJump;
    @BindView(R.id.activity_main)
    LinearLayout mActivityMain;
    @BindView(R.id.rv_news)
    RecyclerView mRvNews;
    @BindView(R.id.et_delete)
    EditText mEtDelete;
    @BindView(R.id.et_update)
    EditText mEtUpdate;
    private NewsAdapter mNewsAdapter;
    private List<NewsBean> newsDatas = new ArrayList<>();
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= 23) {
            int checkCallPhonePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);
            if (checkCallPhonePermission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, REQUEST_CODE_READ_CONTACTS);
                return;
            } else {
                initContact();
            }
        } else {
            //上面已经写好的拨号方法
            initContact();
        }


        mRvNews.setLayoutManager(new LinearLayoutManager(this));
        mNewsAdapter = new NewsAdapter(this,newsDatas);
        mRvNews.setAdapter(mNewsAdapter);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_READ_CONTACTS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted
                    initContact();
                } else {
                    // Permission Denied
                    Toast.makeText(this,"权限被拒绝",Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    public byte[] imgByte(Bitmap bitmap){
        if(bitmap != null){
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
            return baos.toByteArray();
        }else{
            return new byte[0];
        }
    }

    /**
     * 初始化联系人数据
     */
    private void initContact() {
        int i = DataSupport.deleteAll(ContactLitePalBean.class);
        Toast.makeText(this,"清除" + i+ "条数据",Toast.LENGTH_SHORT).show();

        List<ContactBean> phoneContacts = DataUtils.getPhoneContacts();
        if(phoneContacts != null && phoneContacts.size() > 0){
            for (ContactBean phoneContact : phoneContacts) {
                ContactLitePalBean contactLitePalBean = new ContactLitePalBean();
                contactLitePalBean.setContactid(phoneContact.getContactid());
                contactLitePalBean.setContactName(phoneContact.getContactName());
                contactLitePalBean.setPhoneNumber(phoneContact.getPhoneNumber());
                contactLitePalBean.setPhotoid(phoneContact.getPhotoid());
                contactLitePalBean.setBitmapByte(imgByte(phoneContact.getContactPhoto()));
                contactLitePalBean.save();
            }
        }
    }

    @OnClick({R.id.t_create, R.id.bt_upgrade, R.id.bt_jump, R.id.bt_add, R.id.bt_delete, R.id.bt_update, R.id.bt_find, R.id.activity_main,R.id.bt_deleteall})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.t_create:
                SQLiteDatabase db = Connector.getDatabase();//任何操作都会触发创建表
                break;
            case R.id.bt_upgrade:

                break;
            case R.id.bt_add:

                NewsBean newsBean = new NewsBean();
                newsBean.setTitle(i + "级新闻");
                newsBean.setCommentCount(new Random().nextInt(100));
                newsBean.setPublishDate(new Date());
                newsBean.setLink("www.baidu.com" + i);
                if(newsBean.save()){
                    Toast.makeText(this,"存储成功",Toast.LENGTH_SHORT).show();
                }
                i ++;
                findAllData();
                break;
            case R.id.bt_delete:
                String delete = mEtDelete.getText().toString().trim();
                int count = DataSupport.deleteAll(NewsBean.class,"title = ?",delete + "级新闻");
                Toast.makeText(this,"成功删除" + count + "条数据",Toast.LENGTH_SHORT ).show();
                findAllData();
                break;
            case R.id.bt_update:
                String update = mEtUpdate.getText().toString().trim();
                ContentValues valus = new ContentValues();
                valus.put("title","修改后的标题");
                DataSupport.updateAll(NewsBean.class,valus,"id = ?",update);
                findAllData();
                break;
            case R.id.bt_deleteall:
                int i = DataSupport.deleteAll(NewsBean.class);
                Toast.makeText(this,"成功删除" + i + "条数据",Toast.LENGTH_SHORT ).show();
                findAllData();
                break;
            case R.id.bt_find:
                findAllData();
                break;
            case R.id.bt_jump:
                startActivity(new Intent(this,ContactActivity.class));
                break;

        }
    }

    private void findAllData() {
        newsDatas.clear();
        List<NewsBean> allBean = DataSupport.findAll(NewsBean.class);
        newsDatas.addAll(allBean);
        mNewsAdapter.setData(newsDatas);
    }
}
