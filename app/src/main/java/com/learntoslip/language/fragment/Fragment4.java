package com.learntoslip.language.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.learntoslip.language.R;
import com.learntoslip.language.adapter.WordAdapter;
import com.learntoslip.language.service.WordService;

/**
 * Created by Administrator on 2017/5/25.
 */
public class Fragment4 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.word_list , container, false);

        WordAdapter wordAdapter = new WordAdapter(getActivity(), R.layout.word_list_item, WordService.listWord(4));

        ListView listView = (ListView)view.findViewById(R.id.wordListView);

        listView.setAdapter(wordAdapter);
        return view;
    }
}
