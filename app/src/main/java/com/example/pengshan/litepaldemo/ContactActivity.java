package com.example.pengshan.litepaldemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author ChenZhe.
 * @date 2017/10/26
 */

public class ContactActivity extends AppCompatActivity {
    @BindView(R.id.rv_contact)
    RecyclerView mRvContact;
    @BindView(R.id.rv_image)
    RecyclerView mRvImage;
    @BindView(R.id.bt_get_contact)
    Button mBtGetContact;
    @BindView(R.id.bt_get_image)
    Button mBtGetImage;
    ContactAdapter mContactAdapter;
    private List<ContactLitePalBean> contactList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        ButterKnife.bind(this);

        mRvContact.setLayoutManager(new LinearLayoutManager(this));
        mContactAdapter = new ContactAdapter(this,contactList);
        mRvContact.setAdapter(mContactAdapter);
    }

    @OnClick({R.id.bt_get_contact, R.id.bt_get_image})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_get_contact:
                findAllData();
                break;
            case R.id.bt_get_image:
                break;
        }
    }


    private void findAllData() {
        contactList.clear();
        List<ContactLitePalBean> allBean = DataSupport.findAll(ContactLitePalBean.class);
        if(allBean != null){
            contactList.addAll(allBean);
        }
        mContactAdapter.setData(contactList);
    }
}
