package com.learntoslip.language.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.learntoslip.language.R;
import com.learntoslip.language.adapter.MindAdapter;
import com.learntoslip.language.config.MindConfig;

/**
 * Created by Administrator on 2017/5/25.
 */
public class MindFragment extends Fragment {
    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view= inflater.inflate(R.layout.mind_list , container, false);

        // UI界面的更新等相关操作
        MindAdapter mindAdapter = new MindAdapter(getActivity(), R.layout.mind_list_item, MindConfig.listMind());

        ListView listView = (ListView)view.findViewById(R.id.mindListView);

        listView.setAdapter(mindAdapter);
        return view;
    }
}
