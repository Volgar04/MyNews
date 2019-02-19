package com.nicolappli.mynews.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.nicolappli.mynews.Models.NewYorkTimesAPI;
import com.nicolappli.mynews.R;
import com.nicolappli.mynews.Utils.Util;

import java.text.ParseException;

import butterknife.BindView;
import butterknife.ButterKnife;

class NYTViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.article_image)
    ImageView imageArticle;
    @BindView(R.id.article_section)
    TextView txtSectionArticle;
    @BindView(R.id.article_date)
    TextView txtDateArticle;
    @BindView(R.id.article_title)
    TextView txtTitleArticle;

    NYTViewHolder(View articleView) {
        super(articleView);
        ButterKnife.bind(this, articleView);
    }

    void updateWithNewYorkTimesAPI(NewYorkTimesAPI.Result result, RequestManager glide) {
        // set Section
        if((result.getSection()!=null)&&(!result.getSection().isEmpty())){
            this.txtSectionArticle.setText(result.getSection());
            // set subsection if possible
            //if ((result.getSubsection() != null) && (!result.getSubsection().isEmpty()))
            //    this.txtTitleArticle.setText(result.getSection()+" > "+result.getSubsection());
        }else
            this.txtSectionArticle.setText(result.getNewsDesk());

        // set Title
        if((result.getTitle()!=null)&&(!result.getTitle().isEmpty())){
            this.txtTitleArticle.setText(result.getTitle());
        }else
            this.txtTitleArticle.setText(result.getSnippet());

        // set published date
        if((result.getPublishedDate()!=null)&&(!result.getPublishedDate().isEmpty())){
            this.txtDateArticle.setText(Util.parseDateToddMMyy(result.getPublishedDate()));
        }else
            this.txtDateArticle.setText(Util.parseDateToddMMyy(result.getPubDate()));

        // set image
        if ((result.getMultimedia() != null) && (!result.getMultimedia().isEmpty())){
            if(result.getMultimedia().get(0).getUrl().substring(0,5).equals("https")) {
                glide.load(result.getMultimedia().get(1).getUrl()).into(imageArticle);
            }else{
                glide.load("https://static01.nyt.com/"+result.getMultimedia().get(11).getUrl()).into(imageArticle);
            }
        }else if(result.getMedia() !=null&&!result.getMedia().isEmpty()&&result.getMedia().get(0).getMediaMetadata()!=null&&!result.getMedia().get(0).getMediaMetadata().isEmpty())
            glide.load(result.getMedia().get(0).getMediaMetadata().get(1).getUrl()).into(imageArticle);
    }
}
