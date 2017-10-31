package com.learntoslip.language.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
        final TextView textNameView = (TextView) oneWordView.findViewById(R.id.word_name_textView);
        final TextView textContentView = (TextView) oneWordView.findViewById(R.id.word_content_textView);


        // 根据数据设置ImageView和TextView的展现
        textNameView.setText(word.getName());
        String pnuc=word.getPronunciation()==null ? "" :word.getPronunciation();
        String trans=word.getTranslate()==null ? "":word.getTranslate();
        final String content="\n"+pnuc+"\n"+trans;
        if(content.length()>0){
            textContentView.setText("显示内容");
            textContentView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    textContentView.setText("");
                    textNameView.setText(word.getName()+content);
                }
            });
        }
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
