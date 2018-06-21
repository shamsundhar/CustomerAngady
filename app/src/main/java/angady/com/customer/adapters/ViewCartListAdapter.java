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
import angady.com.customer.activities.ViewCartActivity;
import angady.com.customer.model.CenterRepository;
import angady.com.customer.model.entities.Money;
import angady.com.customer.model.entities.Product;
import angady.com.customer.model.entities.Shop;

/**
 * Created by shyam on 3/1/2018.
 */

public class ViewCartListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Product> items;
    private final int PRODUCT_LIST_ITEM = 0;
    private OnItemClickListener listener;
    ViewCartActivity viewCartActivity;

    public ViewCartListAdapter(ViewCartActivity viewCartActivity, List<Product> items){
        this.items = items;
        this.viewCartActivity = viewCartActivity;
    }
    public void setOnItemClickListener(OnItemClickListener clickListener){
        this.listener = clickListener;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        if(viewType == PRODUCT_LIST_ITEM){
            View v1 = inflater.inflate(R.layout.layout_cartlistitem, viewGroup, false);
            viewHolder = new ViewCartListAdapter.ViewHolder1(v1);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        switch (viewHolder.getItemViewType()) {
            case PRODUCT_LIST_ITEM:
                ViewCartListAdapter.ViewHolder1 vh1 = (ViewCartListAdapter.ViewHolder1) viewHolder;
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
    private void configureViewHolder1(final ViewCartListAdapter.ViewHolder1 vh1,final int position) {
        Product product = (Product) items.get(position);
        if (product != null) {
            vh1.getItemName().setText(product.getProductName());

            String sellCostString = "X "+Money.rupees(
                    BigDecimal.valueOf(Long.valueOf(product
                            .getSellMRP()))).toString();
            vh1.getItem_price().setText(sellCostString);
            vh1.getItem_count().setText(product.getQuantity());
                    Long totalValueOfEachProduct = getProductTotalValue(Long.valueOf(product.getSellMRP()),
                            Long.valueOf(product.getQuantity()));
            vh1.getItem_total_price().setText(Money.rupees(BigDecimal.valueOf(totalValueOfEachProduct)).toString());

            vh1.getAdd_item().setOnClickListener(
                    new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            //current object
                            Product tempObj = (Product) items.get(position);
                            //if current object is already in shopping list
                            if (CenterRepository.getCenterRepository()
                                    .getListOfProductsInShoppingList().contains(tempObj))
                            {
                                //get position of current item in shopping list
                                int indexOfTempInShopingList = CenterRepository
                                        .getCenterRepository().getListOfProductsInShoppingList()
                                        .indexOf(tempObj);

                                // increase quantity of current item in shopping list
//                                if (Integer.parseInt(tempObj.getQuantity()) == 0) {
//                                         //viewCartActivity.updateItemCount(true);
//                                }
                                // update quantity in shopping list
                                CenterRepository
                                        .getCenterRepository()
                                        .getListOfProductsInShoppingList()
                                        .get(indexOfTempInShopingList)
                                        .setQuantity(
                                                String.valueOf(Integer
                                                        .valueOf(tempObj
                                                                .getQuantity()) + 1));

                                //update checkout amount
                                viewCartActivity.updateTotalPayableAmount(
                                        BigDecimal
                                                .valueOf(Long
                                                        .valueOf(((Product) items.get(position))
                                                                .getSellMRP())),
                                        true);

                                // update current item quantity
                                vh1.getItem_count().setText(tempObj.getQuantity());
                                //update current item total price
                                Long _totalValueOfEachProduct = getProductTotalValue(Long.valueOf(tempObj.getSellMRP()),
                                        Long.valueOf(tempObj.getQuantity()));
                                vh1.getItem_total_price().setText(Money.rupees(BigDecimal.valueOf(_totalValueOfEachProduct)).toString());

                            }
                        }
                    });

            vh1.getRemove_item().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Product tempObj = (Product) items.get(position);
                    if (CenterRepository.getCenterRepository().getListOfProductsInShoppingList()
                            .contains(tempObj)) {
                        //get the index of current item in shopping list
                        int indexOfTempInShopingList = CenterRepository
                                .getCenterRepository().getListOfProductsInShoppingList()
                                .indexOf(tempObj);

                        if (Integer.valueOf(tempObj.getQuantity()) != 0)
                        {
                            //update the quantity of item in shopping list(-1)
                            CenterRepository
                                    .getCenterRepository()
                                    .getListOfProductsInShoppingList()
                                    .get(indexOfTempInShopingList)
                                    .setQuantity(
                                            String.valueOf(Integer.valueOf(tempObj
                                                    .getQuantity()) - 1));
                            //update total payable amount
                            viewCartActivity.updateTotalPayableAmount(
                                    BigDecimal.valueOf(Long.valueOf(((Product) items.get(position)).getSellMRP())),
                                    false);
                            //set the item quantity text
                            vh1.getItem_count().setText(CenterRepository
                                    .getCenterRepository().getListOfProductsInShoppingList()
                                    .get(indexOfTempInShopingList).getQuantity());

                            //update current item total price
                            Long _totalValueOfEachProduct = getProductTotalValue(Long.valueOf(tempObj.getSellMRP()),
                                    Long.valueOf(tempObj.getQuantity()));
                            vh1.getItem_total_price().setText(Money.rupees(BigDecimal.valueOf(_totalValueOfEachProduct)).toString());


                            if (Integer.valueOf(CenterRepository
                                    .getCenterRepository().getListOfProductsInShoppingList()
                                    .get(indexOfTempInShopingList).getQuantity()) == 0) {
                                CenterRepository.getCenterRepository()
                                        .getListOfProductsInShoppingList()
                                        .remove(indexOfTempInShopingList);
                                notifyDataSetChanged();
                            }
                        }
                    }
                }
            });

        }
    }


    class ViewHolder1 extends RecyclerView.ViewHolder {

        private TextView item_name;
        private TextView item_price;
        private TextView item_total_price;
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

        public TextView getItem_total_price() {
            return item_total_price;
        }

        public void setItem_total_price(TextView item_total_price) {
            this.item_total_price = item_total_price;
        }

        public ViewHolder1(View v) {
            super(v);
            item_name = (TextView) v.findViewById(R.id.item_name);
            item_price = (TextView) v.findViewById(R.id.item_price);
            item_total_price = (TextView)v.findViewById(R.id.item_total_price);
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
    public void updateCheckOutAmount(BigDecimal amount, boolean increment) {

//        if (increment) {
//            checkoutAmount = checkoutAmount.add(amount);
//        } else {
//            if (checkoutAmount.signum() == 1)
//                checkoutAmount = checkoutAmount.subtract(amount);
//        }
//
//        checkOutAmount.setText(Money.rupees(checkoutAmount).toString());
    }
    public Long getProductTotalValue(Long sellMrp, Long quantity){
        return sellMrp * quantity;
    }

}
