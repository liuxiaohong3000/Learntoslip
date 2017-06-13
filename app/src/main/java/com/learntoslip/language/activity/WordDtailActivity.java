package com.learntoslip.language.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.learntoslip.language.R;
import com.learntoslip.language.config.MindConfig;
import com.learntoslip.language.model.ForgettingCurve;
import com.learntoslip.language.model.UserWord;
import com.learntoslip.language.model.dto.WordDTO;
import com.learntoslip.language.service.busservice.ForgetService;
import com.learntoslip.language.service.busservice.UserWordService;
import com.learntoslip.language.service.busservice.WordService;

import java.util.List;

public class WordDtailActivity extends AppCompatActivity {

    private long wordId;
    private long userWordId;
    private long userWordForgetId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list_item_detail);

        // 从Intent获取数据
        wordId = getIntent().getLongExtra("wordId", 0L);
        userWordId = getIntent().getLongExtra("userWordId", 0L);
        userWordForgetId = getIntent().getLongExtra("userWordForgetId", 0L);

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
            new Thread(networkForgetTask).start();
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
    Handler userWordHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            String val = data.getString("value");
            Log.i("mylog", "请求结果为-->" + val);
            UserWord userWord=UserWordService.convertUserWord(val);
            if(userWord !=null){
                userWordId=userWord.getId();
                userWordForgetId=userWord.getUserForgettingCurveId();
            }
            initForgets(forgets);
        }
    };

    /**
     * 网络操作相关的子线程
     */
    Runnable networkuserWordTask = new Runnable() {

        @Override
        public void run() {
            // TODO
            // 在这里进行 http request.网络请求相关操作
            Message msg = new Message();
            Bundle data = new Bundle();
            data.putString("value", UserWordService.getUserWord(MindConfig.userId,wordId));
            msg.setData(data);
            userWordHandler.sendMessage(msg);
        }
    };

    private List<ForgettingCurve> forgets;
    Handler ForgetHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            String val = data.getString("value");
            Log.i("mylog", "请求结果为-->" + val);
            forgets=ForgetService.converForgets(val);
            if(userWordId ==0){
                new Thread(networkuserWordTask).start();
            }else{
                initForgets(forgets);
            }
        }
    };

    /**
     * 网络操作相关的子线程
     */
    Runnable networkForgetTask = new Runnable() {

        @Override
        public void run() {
            // TODO
            // 在这里进行 http request.网络请求相关操作
            Message msg = new Message();
            Bundle data = new Bundle();
            data.putString("value", ForgetService.getForgets());
            msg.setData(data);
            ForgetHandler.sendMessage(msg);
        }
    };
    private void initForgets(List<ForgettingCurve> forgets){
        if(forgets !=null ) {
            LinearLayout myLayout = (LinearLayout) findViewById(R.id.detail_forgets_layout);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            for (ForgettingCurve forget : forgets) {

                TextView textView = new TextView(this);
                //Drawable drawable= getResources().getDrawable(R.drawable.green_draw);
                //drawable.setBounds(0, 0, 45, 45);//必要,不然会不显示 45为宽高
                layoutParams.setMargins(0, 5, 10, 5);
                textView.setTextSize(15);
                if(forget.getId() == userWordForgetId){
                    textView.setBackgroundColor(getResources().getColor(R.color.app_blue));
                }
                //textView.setBackgroundResource(R.drawable.label_style); //设置背景
                //textView.setCompoundDrawables(drawable, null, null, null);
                textView.setCompoundDrawablePadding(5);
                textView.setText(forget.getName());
                textView.setLayoutParams(layoutParams);
                textView.setId(forget.getId().intValue());
                textView.setClickable(true);
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View arg0) {
                        AddUserWordTask networkAddUserWordTask=new AddUserWordTask();
                        networkAddUserWordTask.setFid(new Integer(arg0.getId()).longValue());
                        new Thread(networkAddUserWordTask).start();
                    }
                });
                myLayout.addView(textView);
            }
        }
    }

    Handler addUserWordHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            String val = data.getString("value");
            Log.i("mylog", "请求结果为-->" + val);
            Toast.makeText(WordDtailActivity.this, "更新成功！", Toast.LENGTH_LONG).show();

        }
    };

    class AddUserWordTask implements  Runnable{
        private long fid;

        public void setFid(long fid) {
            this.fid = fid;
        }

        @Override
        public void run() {
            Message msg = new Message();
            Bundle data = new Bundle();
            if(userWordId==0L){
                data.putString("value",UserWordService.addUserWord(MindConfig.userId,fid,wordId));
            }else{
                data.putString("value",UserWordService.modifyUserWord(userWordId,fid));
            }
            msg.setData(data);
            addUserWordHandler.sendMessage(msg);
        }
    }
}
