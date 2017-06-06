package com.learntoslip.language.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import com.learntoslip.language.R;
import com.learntoslip.language.model.dto.WordDTO;
import com.learntoslip.language.service.busservice.WordService;

public class WordDtailActivity extends AppCompatActivity {

    private long wordId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list_item_detail);

        // 从Intent获取数据
        wordId = getIntent().getLongExtra("wordId", 0L);
        //开始加载数据
        new Thread(networkTask).start();
    }
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            String val = data.getString("value");
            Log.i("mylog", "请求结果为-->" + val);
            WordDTO dto = WordService.convertWord(val);
            flushView(dto);
        }
    };

    /**
     * 网络操作相关的子线程
     */
    Runnable networkTask = new Runnable() {

        @Override
        public void run() {
            // TODO
            // 在这里进行 http request.网络请求相关操作
            Message msg = new Message();
            Bundle data = new Bundle();
            data.putString("value", WordService.getWord(wordId));
            msg.setData(data);
            handler.sendMessage(msg);
        }
    };

    private void flushView(WordDTO dto){
//         获取特定的视图
//        ImageView imageView = (ImageView) findViewById(R.id.teacher_large_imageView);
//         根据数据设置视图展现
//        imageView.setImageResource(imageId);
        if(dto.getWord()!=null){
            TextView tabnameview = (TextView) findViewById(R.id.word_tab_name_textView);
            tabnameview.setText(dto.getWord().getName());

            TextView tabtranslaview = (TextView) findViewById(R.id.word_tab_transla_textView);
            tabtranslaview.setText(dto.getWord().getTranslate());
        }
        if(dto.getTab()!=null){
            TextView tabclassview = (TextView) findViewById(R.id.word_tab_wordClasses_textView);
            tabclassview.setText(dto.getTab().getWordClasses());

            TextView tabshorthandview = (TextView) findViewById(R.id.word_tab_shorthand_textView);
            tabshorthandview.setText(dto.getTab().getShorthand());

            TextView tabsynonymview = (TextView) findViewById(R.id.word_tab_synonym_textView);
            tabsynonymview.setText(dto.getTab().getSynonym());

            TextView tabphrasesview = (TextView) findViewById(R.id.word_tab_phrases_textView);
            tabphrasesview.setText(dto.getTab().getPhrases());

            TextView tabotherInfoview = (TextView) findViewById(R.id.word_tab_otherInfo_textView);
            tabotherInfoview.setText(dto.getTab().getOtherInfo());
        }




    }
}
