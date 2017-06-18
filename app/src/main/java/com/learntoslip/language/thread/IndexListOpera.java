package com.learntoslip.language.thread;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import com.learntoslip.language.R;
import com.learntoslip.language.adapter.WordAdapter;
import com.learntoslip.language.fragment.IndexFragment;
import com.learntoslip.language.model.Word;

import java.util.ArrayList;
import java.util.List;

import static com.learntoslip.language.R.layout.word_list_header;

/**
 * Created by Administrator on 2017/5/25.
 */
public class IndexListOpera{
    private Context activity;
    private Context context;
    private LayoutInflater inflater;
    private View fragView;
    private IndexFragment frament;
    private View headerView;
    private ListView listView;
    private View moreView;
    //适配器
    private WordAdapter wordAdapter;
    //private  Button bt;
    private ProgressBar pg;

    private int pageNum=1;
    private int pageSize=15;

    private List<Word> allword;

    private Long typeId;

    private boolean showMore;

    public void initListView(){
        //实例化列表布局
        listView = (ListView)fragView.findViewById(R.id.wordListView);

    }
    public void initSpinnerView(){
        // 实例化header布局
        headerView = inflater.inflate(word_list_header, null);
        listView.addHeaderView(headerView);
    }
    public void initMoreView(){
        // 实例化底部布局
        moreView = inflater.inflate(R.layout.word_list_more_data, null);
        //bt = (Button) moreView.findViewById(R.id.bt_load);
        pg = (ProgressBar) moreView.findViewById(R.id.pg);
        //默认隐藏加载按钮
        //bt.setVisibility(View.INVISIBLE);
        //设置列表布局的底部和数据处理器
        listView.addFooterView(moreView);
//        bt.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                pg.setVisibility(View.VISIBLE);// 将进度条可见
//                bt.setVisibility(View.GONE);// 按钮不可见
//                IndexListRun indexListRun=new IndexListRun();
//                indexListRun.setListOpera(this);
//                new Thread(indexListRun).start();
//            }
//        });
    }
    public void initAdapter(){
        //实例化数据list
        pageNum=1;
        allword=new ArrayList<Word>();
        // UI界面的更新等相关操作
        wordAdapter = new WordAdapter(context, R.layout.word_list_item, allword);
        listView.setAdapter(wordAdapter);
        // 绑定滚动监听器
        listView.setOnScrollListener(frament);
    }

    public Context getActivity() {
        return activity;
    }

    public void setActivity(Context activity) {
        this.activity = activity;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public LayoutInflater getInflater() {
        return inflater;
    }

    public void setInflater(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    public View getFragView() {
        return fragView;
    }

    public void setFragView(View fragView) {
        this.fragView = fragView;
    }

    public IndexFragment getFrament() {
        return frament;
    }

    public void setFrament(IndexFragment frament) {
        this.frament = frament;
    }

    public View getHeaderView() {
        return headerView;
    }

    public void setHeaderView(View headerView) {
        this.headerView = headerView;
    }

    public ListView getListView() {
        return listView;
    }

    public void setListView(ListView listView) {
        this.listView = listView;
    }

    public View getMoreView() {
        return moreView;
    }

    public void setMoreView(View moreView) {
        this.moreView = moreView;
    }

    public WordAdapter getWordAdapter() {
        return wordAdapter;
    }

    public void setWordAdapter(WordAdapter wordAdapter) {
        this.wordAdapter = wordAdapter;
    }

//    public Button getBt() {
//        return bt;
//    }
//
//    public void setBt(Button bt) {
//        this.bt = bt;
//    }

    public ProgressBar getPg() {
        return pg;
    }

    public void setPg(ProgressBar pg) {
        this.pg = pg;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<Word> getAllword() {
        return allword;
    }

    public void setAllword(List<Word> allword) {
        this.allword = allword;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public boolean getShowMore() {
        return showMore;
    }

    public void setShowMore(boolean showMore) {
        this.showMore = showMore;
    }
}
