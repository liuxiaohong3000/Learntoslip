package com.learntoslip.language.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import com.learntoslip.language.R;

public class WordDtailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_detail);

        // 从Intent获取数据
        int imageId = getIntent().getIntExtra("word_image", 0);
        String desc = getIntent().getStringExtra("word_name");

        // 获取特定的视图
        ImageView imageView = (ImageView) findViewById(R.id.teacher_large_imageView);
        TextView textView = (TextView) findViewById(R.id.teacher_desc_textView);

        // 根据数据设置视图展现
        imageView.setImageResource(imageId);
        textView.setText(desc);
    }
}
