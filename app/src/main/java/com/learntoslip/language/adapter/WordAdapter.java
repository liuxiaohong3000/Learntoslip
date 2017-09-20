package com.learntoslip.language.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.learntoslip.language.R;
import com.learntoslip.language.activity.WordDtailActivity;
import com.learntoslip.language.model.Word;

import java.util.List;

/**
 * Created by Administrator on 2017/5/23.
 */
public class WordAdapter extends ArrayAdapter<Word> {
    public WordAdapter(Context context, @LayoutRes int resource, @NonNull List<Word> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 获取老师的数据
        final Word word = getItem(position);



        // 创建布局
        View oneWordView = LayoutInflater.from(getContext()).inflate(R.layout.word_list_item, parent, false);

        // 获取ImageView和TextView
        ImageView imageView = (ImageView) oneWordView.findViewById(R.id.word_small_imageView);
        TextView textView = (TextView) oneWordView.findViewById(R.id.word_name_textView);
        TextView translateView = (TextView) oneWordView.findViewById(R.id.word_translate_textView);
        TextView pronunciationView = (TextView) oneWordView.findViewById(R.id.word_pronunciation_textView);


        // 根据老师数据设置ImageView和TextView的展现
        imageView.setImageResource(word.getImageId());
        textView.setText(word.getName());
        pronunciationView.setText(word.getPronunciation());
        //textView.setTextColor();
        translateView.setText(word.getTranslate());
        oneWordView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //  初始化一个准备跳转到TeacherDetailActivity的Intent
                Intent intent = new Intent(getContext(), WordDtailActivity.class);

                // 往Intent中传入Teacher相关的数据，供TeacherDetailActivity使用
                intent.putExtra("wordId", word.getId());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                //  初始化一个准备跳转到TeacherDetailActivity的Intent
                getContext().startActivity(intent);
            }
        });
        return oneWordView;
    }
}
