package com.learntoslip.language.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import com.learntoslip.language.R;
import com.learntoslip.language.adapter.WordAdapter;
import com.learntoslip.language.service.WordService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WordAdapter wordAdapter = new WordAdapter(this, R.layout.word_item, WordService.listWord());

        ListView listView = (ListView) findViewById(R.id.wordListView);

        listView.setAdapter(wordAdapter);
    }
}
