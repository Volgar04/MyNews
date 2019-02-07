package com.nicolappli.mynews.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bumptech.glide.RequestManager;
import com.nicolappli.mynews.Models.NYTSearchArticles;
import com.nicolappli.mynews.R;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewAdapterSearchArticles extends RecyclerView.Adapter<RecyclerViewAdapterSearchArticles.NYTViewHolder>{

    private List<NYTSearchArticles.Doc> mResults;
    private RequestManager mGlide;

    public RecyclerViewAdapterSearchArticles(List<NYTSearchArticles.Doc> resultsSearchArticles, RequestManager glide) {
        this.mResults = resultsSearchArticles;
        this.mGlide = glide;
    }

    @NonNull
    @Override
    public RecyclerViewAdapterSearchArticles.NYTViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_item, parent, false);
        return new RecyclerViewAdapterSearchArticles.NYTViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterSearchArticles.NYTViewHolder holder, int position) {
        holder.updateWithNYTSearchArticles(this.mResults.get(position), this.mGlide);
    }

    @Override
    public int getItemCount() {
        return mResults.size();
    }

    class NYTViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.article_image)
        ImageView imageArticle;
        @BindView(R.id.article_Title)
        TextView txtTitleArticle;
        @BindView(R.id.article_date)
        TextView txtDateArticle;
        @BindView(R.id.article_summary)
        TextView txtSummaryArticle;

        NYTViewHolder(View articleView) {
            super(articleView);
            ButterKnife.bind(this, articleView);
        }

        void updateWithNYTSearchArticles(NYTSearchArticles.Doc result, RequestManager glide) {
            this.txtTitleArticle.setText(result.getNewsDesk());
            this.txtSummaryArticle.setText(result.getSnippet());
            this.txtDateArticle.setText(result.getPubDate());
            if ((result.getMultimedia() != null) && (!result.getMultimedia().isEmpty()))
                glide.load("https://static01.nyt.com/"+result.getMultimedia().get(0).getUrl()).into(imageArticle);
        }
    }
}
