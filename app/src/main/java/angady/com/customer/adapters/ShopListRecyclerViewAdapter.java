package angady.com.customer.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import angady.com.customer.R;
import angady.com.customer.model.entities.Shop;

/**
 * Created by shyam on 2/25/2018.
 */

public class ShopListRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Object> items;
    private final int SHOP_LIST_ITEM = 0;
    private OnItemClickListener listener;

    public ShopListRecyclerViewAdapter(){}
    public void setOnItemClickListener(OnItemClickListener clickListener){
        this.listener = clickListener;
    }
    public void setItems(List<Object> items) {
        this.items = items;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            if(viewType == SHOP_LIST_ITEM){
                View v1 = inflater.inflate(R.layout.layout_shoplistitem2, viewGroup, false);
                viewHolder = new ViewHolder1(v1);
            }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        switch (viewHolder.getItemViewType()) {
            case SHOP_LIST_ITEM:
                ViewHolder1 vh1 = (ViewHolder1) viewHolder;
                configureViewHolder1(vh1, position);
                break;
            default:
              //  RecyclerViewSimpleTextViewHolder vh = (RecyclerViewSimpleTextViewHolder) viewHolder;
              //  configureDefaultViewHolder(vh, position);
                break;
        }
    }

    @Override
    public int getItemCount() {
        if(items != null) {
            return this.items.size();
        }
        else{
            return 0;
        }
    }
    @Override
    public int getItemViewType(int position) {
        if (items.get(position) instanceof Shop) {
            return SHOP_LIST_ITEM;
        }
        return -1;
    }
    private void configureViewHolder1(ViewHolder1 vh1, int position) {
        Shop shopModel = (Shop) items.get(position);
        if (shopModel != null) {
            vh1.getTitle().setText(shopModel.getShopName());
            vh1.getSubtitle().setText(shopModel.getAddress1());
            vh1.bind(shopModel, listener);
        }
    }


    class ViewHolder1 extends RecyclerView.ViewHolder {

        private TextView title, subtitle;

        public TextView getTitle() {
            return title;
        }

        public void setTitle(TextView title) {
            this.title = title;
        }

        public TextView getSubtitle() {
            return subtitle;
        }

        public void setSubtitle(TextView subtitle) {
            this.subtitle = subtitle;
        }

        public ViewHolder1(View v) {
            super(v);
            title = (TextView) v.findViewById(R.id.title);
            subtitle = (TextView) v.findViewById(R.id.subtitle);
        }
        public void bind(final Shop shop, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(shop.getShopId(), shop.getShopName());
                }
            });
        }
    }

}
