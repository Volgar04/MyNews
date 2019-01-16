package com.nicolappli.mynews.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.nicolappli.mynews.R;
import com.nicolappli.mynews.Views.ArticleItem;
import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>{

    private Context mCtx;
    private List<ArticleItem> articleList;

    public ArticleAdapter(Context ctx, List<ArticleItem> articleList) {
        mCtx = ctx;
        this.articleList = articleList;
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.cardview_layout, null);
        return new ArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        ArticleItem article = articleList.get(position);

        holder.textViewCategory.setText(article.getCategory());
        holder.textViewTitle.setText(article.getTitle());
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    class ArticleViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textViewTitle, textViewCategory;

        public ArticleViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageArticle);
            textViewTitle = itemView.findViewById(R.id.txtTitleArticle);
            textViewCategory = itemView.findViewById(R.id.txtCategoryArticle);
        }
    }
}
