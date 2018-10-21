package com.developer.willshuffy.willstore.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.developer.willshuffy.willstore.R;
import com.developer.willshuffy.willstore.responses.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductItemAdapter extends RecyclerView.Adapter<ProductItemAdapter.ViewHolder> {
    private Context mContext;
    private List<Product> mProductList;
    private LayoutInflater mLayoutInflater;
    private OnItemClickListener mListener;


    public ProductItemAdapter(Context context, List<Product> productList){
        mContext = context;
        mProductList = productList;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void setListener(OnItemClickListener listener){
        mListener = listener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder = new ViewHolder(mLayoutInflater.inflate(R.layout.item_product, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Product product = mProductList.get(position);

        holder.nameTextView.setText(product.getName());
        holder.priceTextView.setText("Belum Ada Deal!");

        Picasso.with(mContext).load(product.getPhoto()).into(holder.imageView);

        if (mListener != null){
            holder.container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onItemClick(product.getId());
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return mProductList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        public FrameLayout container;
        public ImageView imageView;
        public TextView nameTextView;
        public TextView priceTextView;

        public ViewHolder(View itemView){
            super(itemView);

            container = itemView.findViewById(R.id.container);
            imageView = itemView.findViewById(R.id.iv_product_image);
            nameTextView = itemView.findViewById(R.id.tv_product_name);
            priceTextView = itemView.findViewById(R.id.tv_product_price);

        }
    }
    public interface OnItemClickListener{
        public void onItemClick(String productId);
    }
}