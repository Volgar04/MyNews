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
import com.nicolappli.mynews.Models.NYTMostPopular;
import com.nicolappli.mynews.R;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewAdapterMostPopular extends RecyclerView.Adapter<RecyclerViewAdapterMostPopular.NYTViewHolder>{

    private List<NYTMostPopular.Result> mResults;
    private RequestManager mGlide;

    public RecyclerViewAdapterMostPopular(List<NYTMostPopular.Result> resultsMostPopular, RequestManager glide) {
        this.mResults = resultsMostPopular;
        this.mGlide = glide;
    }

    @NonNull
    @Override
    public RecyclerViewAdapterMostPopular.NYTViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_item, parent, false);
        return new RecyclerViewAdapterMostPopular.NYTViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterMostPopular.NYTViewHolder holder, int position) {
        holder.updateWithNYTMostPopular(this.mResults.get(position), this.mGlide);
    }

    @Override
    public int getItemCount() {
        return mResults.size();
    }

    class NYTViewHolder extends RecyclerView.ViewHolder {
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

        NYTViewHolder(View articleView) {
            super(articleView);
            ButterKnife.bind(this, articleView);
        }

        void updateWithNYTMostPopular(NYTMostPopular.Result result, RequestManager glide) {
            this.txtTitleArticle.setText(result.getSection());
            this.txtSummaryArticle.setText(result.getTitle());
            this.txtDateArticle.setText(result.getPublishedDate());
            if ((result.getMedia().get(0).getMediaMetadata() != null) && (!result.getMedia().get(0).getMediaMetadata().isEmpty()))
                glide.load(result.getMedia().get(0).getMediaMetadata().get(0).getUrl()).into(imageArticle);
            if ((result.getSubsection() != null) && (!result.getSubsection().isEmpty()))
                this.txtTitleArticle.setText(result.getSection()+" > "+result.getSubsection());
        }
    }
}
