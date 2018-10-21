package com.developer.willshuffy.willstore.adapters;


import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.developer.willshuffy.willstore.R;
import com.developer.willshuffy.willstore.responses.Store;
import com.squareup.picasso.Picasso;

import java.util.List;

public class StoreItemAdapter extends RecyclerView.Adapter<StoreItemAdapter.ViewHolder> {
    private Context mContext;
    private List<Store> mStoreList;
    private LayoutInflater mLayoutInflater;
    private OnItemClickListener mListener;

    public StoreItemAdapter(Context context, List<Store> storeList) {
        mContext = context;
        mStoreList = storeList;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder = new ViewHolder(mLayoutInflater.inflate(R.layout.item_store, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Store store = mStoreList.get(position);

        holder.nameTextView.setText(store.getName());
        holder.dealTextView.setText("Belum Ada Deal!");
        Picasso.with(mContext)
                .load(store.getPhoto())
                .resize(90,90)
                .into(holder.imageView);

        if (mListener != null){
            holder.container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onItemClick(store.getId());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mStoreList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public CardView cardView;
        public FrameLayout container;
        public ImageView imageView;
        public TextView nameTextView;
        public TextView dealTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            container = itemView.findViewById(R.id.container);
            imageView = itemView.findViewById(R.id.iv_store_image);
            nameTextView = itemView.findViewById(R.id.tv_store_name);
            dealTextView = itemView.findViewById(R.id.tv_store_deal);
            cardView =itemView.findViewById(R.id.cv_store);

        }
    }

    public void setListener(OnItemClickListener listener){
        mListener = listener;
    }

    public interface OnItemClickListener{
        public void onItemClick(String storeId);
    }
}

