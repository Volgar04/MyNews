package com.nicolappli.mynews.Adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TableRow;
import android.widget.TextView;
import com.bumptech.glide.RequestManager;
import com.nicolappli.mynews.Models.NewYorkTimesAPI;
import com.nicolappli.mynews.R;
import com.nicolappli.mynews.Utils.Util;
import butterknife.BindView;
import butterknife.ButterKnife;

class NYTViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.card_view_article)
    CardView cardViewArticle;
    @BindView(R.id.article_image)
    ImageView imageArticle;
    @BindView(R.id.article_section)
    TextView txtSectionArticle;
    @BindView(R.id.article_date)
    TextView txtDateArticle;
    @BindView(R.id.article_title)
    TextView txtTitleArticle;

    private Util mUtil = new Util();

    NYTViewHolder(View articleView) {
        super(articleView);
        ButterKnife.bind(this, articleView);
    }

    /**
     * Update the UI with the list of result
     * @param result List of result
     * @param glide For images
     */
    void updateWithNewYorkTimesAPI(NewYorkTimesAPI.Result result, RequestManager glide) {
        this.setArticleTitle(result);
        this.setArticleSection(result);
        this.setArticlePubDate(result);
        this.setArticleImage(result, glide);
    }

    private void setArticleSection(NewYorkTimesAPI.Result result){
        if((result.getSection()!=null)&&(!result.getSection().isEmpty())){
            this.txtSectionArticle.setText(result.getSection()); //for top stories and most popular
        }else
            this.txtSectionArticle.setText(result.getNewsDesk()); //for search articles
    }

    private void setArticlePubDate(NewYorkTimesAPI.Result result){
        if((result.getPublishedDate()!=null)&&(!result.getPublishedDate().isEmpty()))
            this.txtDateArticle.setText(mUtil.parseDateToddMMyy(result.getPublishedDate())); //for top stories and most popular
        else
            this.txtDateArticle.setText(mUtil.parseDateToddMMyy(result.getPubDate())); //for search articles
    }

    private void setArticleTitle(NewYorkTimesAPI.Result result){
        if((result.getTitle()!=null)&&(!result.getTitle().isEmpty()))
            this.txtTitleArticle.setText(result.getTitle()); //for top stories and most popular
        else
            this.txtTitleArticle.setText(result.getSnippet()); //for search articles
    }

    private void setArticleImage(NewYorkTimesAPI.Result result, RequestManager glide){
        if ((result.getMultimedia() != null) && (!result.getMultimedia().isEmpty())){
            if(result.getMultimedia().get(0).getUrl().substring(0,5).equals("https")) { // for Top Stories
                glide.load(result.getMultimedia().get(1).getUrl()).into(imageArticle);
            }else{ // for Search Articles
                try { // if it's a recent article put the eleventh image (better quality)
                    glide.load("https://static01.nyt.com/"+result.getMultimedia().get(11).getUrl()).into(imageArticle);
                } catch (Exception e) { // else put the first image
                    e.printStackTrace();
                    glide.load("https://static01.nyt.com/"+result.getMultimedia().get(1).getUrl()).into(imageArticle);
                }
            }
        }else if(result.getMedia() !=null&&!result.getMedia().isEmpty()&&result.getMedia().get(0).getMediaMetadata()!=null&&!result.getMedia().get(0).getMediaMetadata().isEmpty()) // for Most Popular
            glide.load(result.getMedia().get(0).getMediaMetadata().get(1).getUrl()).into(imageArticle);
        else{ // better text alignment when none image is available
            imageArticle.setVisibility(View.GONE);
            txtSectionArticle.setGravity(Gravity.START);
            txtTitleArticle.setGravity(Gravity.START);
            txtTitleArticle.setMaxLines(2);
        }
    }
}
