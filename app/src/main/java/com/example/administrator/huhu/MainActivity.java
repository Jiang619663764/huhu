package com.example.administrator.huhu;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioGroup;

import com.example.administrator.huhu.fragment.FirstPageFragment;
import com.example.administrator.huhu.fragment.KnowledgeFragment;
import com.example.administrator.huhu.fragment.MapFragment;
import com.example.administrator.huhu.fragment.MyselfFragment;

public class MainActivity extends AppCompatActivity {

    DrawerLayout mDrawer;
    ActionBarDrawerToggle mDrawerToggle;
    Toolbar toolbar;

    RadioGroup mRadioGroup;

    FragmentManager mFragmentManager;
    FragmentTransaction mTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        initView();


    }

    /**
     * 初始化控件
     */
    private void initView() {
        //设置toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //设置侧滑菜单
        mDrawer = (DrawerLayout) findViewById(R.id.drawer);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.open_drawer, R.string.close_drawer);
        mDrawerToggle.syncState();
        mDrawer.setDrawerListener(mDrawerToggle);

        //设置第一张fragment
        mFragmentManager=getSupportFragmentManager();
        mTransaction=mFragmentManager.beginTransaction();
        mTransaction.replace(R.id.content_frgment,new FirstPageFragment());
        mTransaction.commit();

        //设置底部菜单
        mRadioGroup=(RadioGroup)findViewById(R.id.radiogroup);

        //设置RadioButton的点击事件
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                FragmentManager fm=getSupportFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                switch (checkedId){
                    case R.id.rb_shouye:
                        ft.replace(R.id.content_frgment,new FirstPageFragment());
                        break;
                    case R.id.rb_zhishi:
                        ft.replace(R.id.content_frgment,new KnowledgeFragment());
                        break;
                    case R.id.rb_tidu:
                        ft.replace(R.id.content_frgment,new MapFragment());
                        break;
                    case R.id.rb_wode:
                        ft.replace(R.id.content_frgment,new MyselfFragment());
                        break;
                }
                ft.commit();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
