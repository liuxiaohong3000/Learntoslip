package com.learntoslip.language.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.learntoslip.language.R;
import com.learntoslip.language.model.Mind;

import java.util.List;

/**
 * Created by Administrator on 2017/5/23.
 */
public class MindAdapter extends ArrayAdapter<Mind> {
    public MindAdapter(Context context, @LayoutRes int resource, @NonNull List<Mind> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 获取数据
        final Mind mind = getItem(position);

        // 创建布局
        View oneMindView = LayoutInflater.from(getContext()).inflate(R.layout.mind_list_item, parent, false);

        // 获取ImageView和TextView
        TextView titleView = (TextView) oneMindView.findViewById(R.id.mind_list_item_titleView);
        TextView textView = (TextView) oneMindView.findViewById(R.id.mind_list_item_textView);

        titleView.setText(mind.getTitle());
        textView.setText(mind.getText());

        return oneMindView;
    }
}
