package com.learntoslip.language.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;
import com.learntoslip.language.R;
import com.learntoslip.language.model.dto.WordDTO;
import com.learntoslip.language.service.busservice.WordService;

public class WordHtmlDtailActivity extends AppCompatActivity {

    private long wordId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list_item_html_detail);

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
        if(dto.getTab()!=null){
            TextView tabotherInfoview = (TextView) findViewById(R.id.word_detail_html_textView);
            if(dto.getTab().getOtherInfo()!= null && dto.getTab().getOtherInfo().length()>0){
                tabotherInfoview.setText(Html.fromHtml(dto.getTab().getOtherInfo()));

            }
        }
    };
}
