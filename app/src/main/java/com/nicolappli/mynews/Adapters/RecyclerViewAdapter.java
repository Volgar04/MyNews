package com.nicolappli.mynews.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bumptech.glide.RequestManager;
import com.nicolappli.mynews.Models.NewYorkTimesAPI;
import com.nicolappli.mynews.R;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<NYTViewHolder> {

    private List<NewYorkTimesAPI.Result> mResults;
    private RequestManager mGlide;

    public RecyclerViewAdapter(List<NewYorkTimesAPI.Result> results, RequestManager glide) {
        this.mResults = results;
        this.mGlide = glide;
    }

    @NonNull
    @Override
    public NYTViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_item, parent, false);
        return new NYTViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NYTViewHolder holder, int position) {
        holder.updateWithNewYorkTimesAPI(this.mResults.get(position), this.mGlide);
    }

    @Override
    public int getItemCount() {
        return mResults.size();
    }

    public NewYorkTimesAPI.Result getUrl(int position) {
        return this.mResults.get(position);
    }
}
