package angady.com.customer.adapters;

import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.List;

import angady.com.customer.R;
import angady.com.customer.activities.ProductListActivity;
import angady.com.customer.model.CenterRepository;
import angady.com.customer.model.entities.Money;
import angady.com.customer.model.entities.Product;
import angady.com.customer.model.entities.Shop;

/**
 * Created by shyam on 2/26/2018.
 */

public class ProductListRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Object> items;
    private final int PRODUCT_LIST_ITEM = 0;
    private OnItemClickListener listener;
    ProductListActivity productListActivity;

    public ProductListRecyclerViewAdapter(ProductListActivity productListActivity, List<Object> items){
        this.items = items;
        this.productListActivity = productListActivity;
    }
    public void setOnItemClickListener(OnItemClickListener clickListener){
        this.listener = clickListener;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        if(viewType == PRODUCT_LIST_ITEM){
            View v1 = inflater.inflate(R.layout.layout_productlistitem, viewGroup, false);
            viewHolder = new ProductListRecyclerViewAdapter.ViewHolder1(v1);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        switch (viewHolder.getItemViewType()) {
            case PRODUCT_LIST_ITEM:
                ProductListRecyclerViewAdapter.ViewHolder1 vh1 = (ProductListRecyclerViewAdapter.ViewHolder1) viewHolder;
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
        return this.items.size();
    }
    @Override
    public int getItemViewType(int position) {
        if (items.get(position) instanceof Product) {
            return PRODUCT_LIST_ITEM;
        }
        return -1;
    }
    private void configureViewHolder1(final ProductListRecyclerViewAdapter.ViewHolder1 vh1,final int position) {
        Product product = (Product) items.get(position);
        if (product != null) {
            vh1.getItemName().setText(product.getProduct_name());
            vh1.getItemShortDesc().setText(product.getProduct_description());
            vh1.getItem_price().setText(product.getUnit_price());


            String sellCostString = Money.rupees(
                    BigDecimal.valueOf(Long.valueOf(product
                            .getSpecial_price()))).toString()
                    + "  ";

            String buyMRP = Money.rupees(
                    BigDecimal.valueOf(Long.valueOf(product
                            .getUnit_price()))).toString();

            String costString = sellCostString + buyMRP;

            vh1.getItem_price().setText(costString, TextView.BufferType.SPANNABLE);

            Spannable spannable = (Spannable) vh1.getItem_price().getText();

            spannable.setSpan(new StrikethroughSpan(), sellCostString.length(),
                    costString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            vh1.getAdd_item().setOnClickListener(
                    new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {


                            //current object
                            Product tempObj = (Product) items.get(position);


                            //if current object is already in shopping list
                            if (CenterRepository.getCenterRepository()
                                    .getListOfProductsInShoppingList().contains(tempObj)) {


                                //get position of current item in shopping list
                                int indexOfTempInShopingList = CenterRepository
                                        .getCenterRepository().getListOfProductsInShoppingList()
                                        .indexOf(tempObj);

                                // increase quantity of current item in shopping list
                                if (Integer.parseInt(tempObj.getQuantity()) == 0) {

                                   productListActivity.updateItemCount(true);

                                }


                                // update quanity in shopping list
                                CenterRepository
                                        .getCenterRepository()
                                        .getListOfProductsInShoppingList()
                                        .get(indexOfTempInShopingList)
                                        .setQuantity(
                                                String.valueOf(Integer
                                                        .valueOf(tempObj
                                                                .getQuantity()) + 1));


                                //update checkout amount
                                productListActivity.updateCheckOutAmount(
                                        BigDecimal
                                                .valueOf(Long
                                                        .valueOf(((Product) items.get(position))
                                                                .getSpecial_price())),
                                        true);

                                // update current item quanitity
                                vh1.getItem_count().setText(tempObj.getQuantity());

                            } else {

                                productListActivity.updateItemCount(true);

                                tempObj.setQuantity(String.valueOf(1));

                                vh1.getItem_count().setText(tempObj.getQuantity());

                                CenterRepository.getCenterRepository()
                                        .getListOfProductsInShoppingList().add(tempObj);

                                productListActivity.updateCheckOutAmount(
                                        BigDecimal
                                                .valueOf(Long
                                                        .valueOf(((Product) items.get(position))
                                                                .getSpecial_price())),
                                        true);

                            }

                         //   Utils.vibrate(getContext());

                        }
                    });

            vh1.getRemove_item().setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    Product tempObj = (Product) items.get(position);

                    if (CenterRepository.getCenterRepository().getListOfProductsInShoppingList()
                            .contains(tempObj)) {

                        int indexOfTempInShopingList = CenterRepository
                                .getCenterRepository().getListOfProductsInShoppingList()
                                .indexOf(tempObj);

                        if (Integer.valueOf(tempObj.getQuantity()) != 0) {

                            CenterRepository
                                    .getCenterRepository()
                                    .getListOfProductsInShoppingList()
                                    .get(indexOfTempInShopingList)
                                    .setQuantity(
                                            String.valueOf(Integer.valueOf(tempObj
                                                    .getQuantity()) - 1));

                            productListActivity.updateCheckOutAmount(
                                    BigDecimal.valueOf(Long.valueOf(((Product) items.get(position)).getSpecial_price())),
                                    false);

                            vh1.getItem_count().setText(CenterRepository
                                    .getCenterRepository().getListOfProductsInShoppingList()
                                    .get(indexOfTempInShopingList).getQuantity());

                           // Utils.vibrate(getContext());

                            if (Integer.valueOf(CenterRepository
                                    .getCenterRepository().getListOfProductsInShoppingList()
                                    .get(indexOfTempInShopingList).getQuantity()) == 0) {

                                CenterRepository.getCenterRepository()
                                        .getListOfProductsInShoppingList()
                                        .remove(indexOfTempInShopingList);

                                notifyDataSetChanged();

                                productListActivity.updateItemCount(false);

                            }

                        }

                    } else {

                    }

                }

            });

        }
    }


    class ViewHolder1 extends RecyclerView.ViewHolder {

        private TextView item_name, item_short_desc;
        private TextView item_price;
        private TextView add_item;
        private TextView remove_item;
        private TextView item_count;

        public TextView getAdd_item() {
            return add_item;
        }

        public void setAdd_item(TextView add_item) {
            this.add_item = add_item;
        }

        public TextView getRemove_item() {
            return remove_item;
        }

        public void setRemove_item(TextView remove_item) {
            this.remove_item = remove_item;
        }

        public TextView getItem_count() {
            return item_count;
        }

        public void setItem_count(TextView item_count) {
            this.item_count = item_count;
        }

        public TextView getItem_price() {
            return item_price;
        }

        public void setItem_price(TextView item_price) {
            this.item_price = item_price;
        }

        public TextView getItemName() {
            return item_name;
        }

        public void setItemName(TextView item_name) {
            this.item_name = item_name;
        }

        public TextView getItemShortDesc() {
            return item_short_desc;
        }

        public void setItemShortDesc(TextView subtitle) {
            this.item_short_desc = subtitle;
        }

        public ViewHolder1(View v) {
            super(v);
            item_name = (TextView) v.findViewById(R.id.item_name);
            item_short_desc = (TextView) v.findViewById(R.id.item_short_desc);
            item_price = (TextView) v.findViewById(R.id.item_price);
            add_item = (TextView) v.findViewById(R.id.add_item);
            remove_item = (TextView) v.findViewById(R.id.remove_item);
            item_count = (TextView) v.findViewById(R.id.item_count);
        }
        public void bind(final Shop shop, final OnItemClickListener listener) {
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override public void onClick(View v) {
//                    listener.onItemClick(shop.getShopName());
//                }
//            });
        }
    }

}
