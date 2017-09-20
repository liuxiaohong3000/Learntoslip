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
import com.learntoslip.language.model.UserWord;

import java.util.List;

/**
 * Created by Administrator on 2017/5/23.
 */
public class UserWordAdapter extends ArrayAdapter<UserWord> {
    public UserWordAdapter(Context context, @LayoutRes int resource, @NonNull List<UserWord> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 获取老师的数据
        final UserWord userWord = getItem(position);



        // 创建布局
        View oneWordView = LayoutInflater.from(getContext()).inflate(R.layout.user_word_list_item, parent, false);

        // 获取ImageView和TextView
        TextView nameView = (TextView) oneWordView.findViewById(R.id.user_word_name_textView);
        final TextView contentView = (TextView) oneWordView.findViewById(R.id.user_word_content_textView);

        nameView.setText(userWord.getWordName());
        String pnuc=userWord.getPronunciation()==null ? "" :userWord.getPronunciation();
        String trans=userWord.getTranslate()==null ? "":userWord.getTranslate();
        final String content=pnuc+trans;
        if(content.length()>0){
            contentView.setText("显示内容");
            contentView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    contentView.setText(content);
                }
            });
        }



        oneWordView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //  初始化一个准备跳转到TeacherDetailActivity的Intent
                Intent intent = new Intent(getContext(), WordDtailActivity.class);

                // 往Intent中传入Teacher相关的数据，供TeacherDetailActivity使用
                intent.putExtra("wordId", userWord.getWordId());
                intent.putExtra("userWordId", userWord.getId());
                intent.putExtra("userWordForgetId", userWord.getUserForgettingCurveId());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                //  初始化一个准备跳转到TeacherDetailActivity的Intent
                getContext().startActivity(intent);
            }
        });
        return oneWordView;
    }
}
