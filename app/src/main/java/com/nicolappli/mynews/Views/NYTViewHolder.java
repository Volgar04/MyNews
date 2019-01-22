package com.nicolappli.mynews.Views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.nicolappli.mynews.Models.NYTTopStories;
import com.nicolappli.mynews.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NYTViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.article_layout)
    RelativeLayout layoutArticle;
    @BindView(R.id.article_image)
    ImageView imageArticle;
    @BindView(R.id.article_Title)
    TextView txtTitleArticle;
    @BindView(R.id.article_date)
    TextView txtDateArticle;
    @BindView(R.id.article_summary)
    TextView txtSummaryArticle;

    public NYTViewHolder(View articleView){
        super(articleView);
        ButterKnife.bind(this, articleView);
    }

    public void updateWithNYTTopStories(NYTTopStories.Result result, RequestManager glide){
        this.txtTitleArticle.setText(result.getSection());
        this.txtSummaryArticle.setText(result.getTitle());
        this.txtDateArticle.setText(result.getPublishedDate());
        glide.load(result.getMultimedia().get(0).getUrl()).apply(RequestOptions.noTransformation()).into(imageArticle);
    }
}
