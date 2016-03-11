package com.example.administrator.huhu.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.huhu.MyPagerAdapter;
import com.example.administrator.huhu.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FirstPageFragment extends Fragment implements ViewPager.OnPageChangeListener {

    private ViewPager mViewPager;
    private List<View> mData;

    /**
     * 请求更新显示的View
     */
    public static final int MSG_UPDATE_IMG = 1;
    /**
     * 请求暂停轮播
     */
    public static final int MSG_KEEP_SILENT = 2;
    /**
     * 恢复暂停轮播
     */
    public static final int MSG_BREAK_SILENT = 3;
    /**
     * 记录最新的页号，当用户手动滑动时需要记录新页号，否则会使轮播的页面出错。
     * 例如当前如果在第一页，本来准备播放的是第二页，而这时候用户滑动到了末页，
     * 则应该播放的是第一页，如果继续按照原来的第二页播放，则逻辑上有问题。
     */
    public static final int MSG_PAGE_CHANGE = 4;
    /**
     * 轮播间隔时间
     */
    public static final long MSG_HAND_DELAY = 3000;
    private int count=0;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case MSG_UPDATE_IMG:
                    count++;
                    mViewPager.setCurrentItem(count);
                    handler.sendEmptyMessageDelayed(MSG_UPDATE_IMG, MSG_HAND_DELAY);
                    break;
                case MSG_KEEP_SILENT:
                    break;
                case MSG_BREAK_SILENT:
                    handler.sendEmptyMessageDelayed(MSG_UPDATE_IMG, MSG_HAND_DELAY);
                    break;
                case MSG_PAGE_CHANGE:
                    count=msg.arg1;
                    handler.sendEmptyMessageDelayed(MSG_UPDATE_IMG, MSG_HAND_DELAY);
                    break;
            }

        }
    };


    public FirstPageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDate(container);

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.layout_fp_frg, container, false);
        mViewPager = (ViewPager) view.findViewById(R.id.first_pager);
        mViewPager.setAdapter(new MyPagerAdapter(mData));
        mViewPager.setOnPageChangeListener(this);

        mViewPager.setCurrentItem(Integer.MAX_VALUE / 2);
        handler.sendEmptyMessageDelayed(MSG_UPDATE_IMG, MSG_HAND_DELAY);

        return view;
    }

    /**
     * 获取ViewPager中的数据
     */
    private void getDate(ViewGroup container) {
        mData = new ArrayList<View>();
        LayoutInflater infla = LayoutInflater.from(getActivity());
        mData.add(infla.inflate(R.layout.layout_picture, container, false));
        mData.add(infla.inflate(R.layout.layout_picture4, container, false));
        mData.add(infla.inflate(R.layout.layout_picture2, container, false));
        mData.add(infla.inflate(R.layout.layout_picture3, container, false));
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        handler.sendMessage(Message.obtain(handler, MSG_PAGE_CHANGE, position,0));
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        switch (state) {
            case ViewPager.SCROLL_STATE_DRAGGING:
                handler.sendEmptyMessage(MSG_KEEP_SILENT);
                break;
            case ViewPager.SCROLL_STATE_IDLE:
                handler.sendEmptyMessageDelayed(MSG_UPDATE_IMG, MSG_HAND_DELAY);
                break;
        }
    }
}
