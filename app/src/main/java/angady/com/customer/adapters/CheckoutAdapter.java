package angady.com.customer.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.List;

import angady.com.customer.R;
import angady.com.customer.activities.CheckoutActivity;
import angady.com.customer.activities.ViewCartActivity;
import angady.com.customer.model.CenterRepository;

import angady.com.customer.model.entities.Checkout;
import angady.com.customer.model.entities.Divider;
import angady.com.customer.model.entities.ListHeader;
import angady.com.customer.model.entities.Money;
import angady.com.customer.model.entities.PaymentOption;
import angady.com.customer.model.entities.Product;
import angady.com.customer.model.entities.Shop;

/**
 * Created by shyam on 3/3/2018.
 */

public class CheckoutAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Object> items;
    private final int LIST_HEADER_ITEM = 0;
    private final int DELIVERY_DETAILS_ITEM = 1;
    private final int LIST_DIVIDER = 2;
    private final int PAYMENT_DETAILS_ITEM = 3;
    private OnItemClickListener listener;
    CheckoutActivity checkoutActivity;

    public CheckoutAdapter(CheckoutActivity checkoutActivity, List<Object> items){
        this.items = items;
        this.checkoutActivity = checkoutActivity;
    }
    public void setOnItemClickListener(OnItemClickListener clickListener){
        this.listener = clickListener;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        if(viewType == LIST_HEADER_ITEM){
            View v1 = inflater.inflate(R.layout.layout_list_header, viewGroup, false);
            viewHolder = new CheckoutAdapter.ListHeaderViewHolder(v1);
        }
        else if(viewType == DELIVERY_DETAILS_ITEM){
            View v1 = inflater.inflate(R.layout.layout_delivery_details_list_item, viewGroup, false);
            viewHolder = new CheckoutAdapter.DeliveryDetailsViewHolder(v1);
        }
        else if(viewType == LIST_DIVIDER){
            View v1 = inflater.inflate(R.layout.layout_divider, viewGroup, false);
            viewHolder = new CheckoutAdapter.ListDividerHolder(v1);
        }
        else if(viewType == PAYMENT_DETAILS_ITEM){
            View v1 = inflater.inflate(R.layout.layout_payment_details_list_item, viewGroup, false);
            viewHolder = new CheckoutAdapter.PaymentDetailsViewHolder(v1);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        switch (viewHolder.getItemViewType()) {
            case LIST_HEADER_ITEM:
                CheckoutAdapter.ListHeaderViewHolder vh1 = (CheckoutAdapter.ListHeaderViewHolder) viewHolder;
                configureLayoutHeader(vh1, position);
                break;
            case DELIVERY_DETAILS_ITEM:
                CheckoutAdapter.DeliveryDetailsViewHolder vh2 = (CheckoutAdapter.DeliveryDetailsViewHolder)viewHolder;
                configureLayoutDeliveryDetails(vh2, position);
                break;
            case PAYMENT_DETAILS_ITEM:
                 CheckoutAdapter.PaymentDetailsViewHolder vh3 = (CheckoutAdapter.PaymentDetailsViewHolder)viewHolder;
                 configureLayoutPaymentDetails(vh3, position);
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
        if (items.get(position) instanceof ListHeader) {
            return LIST_HEADER_ITEM;
        }
        else if(items.get(position) instanceof Checkout){
            return DELIVERY_DETAILS_ITEM;
        }
        else if(items.get(position) instanceof Divider){
            return LIST_DIVIDER;
        }
        else if(items.get(position) instanceof PaymentOption){
            return PAYMENT_DETAILS_ITEM;
        }
        return -1;
    }
    //    private void configureViewHolder1(final CheckoutAdapter.ViewHolder1 vh1,final int position) {
//        Product product = (Product) items.get(position);
//        if (product != null) {
//            vh1.getItemName().setText(product.getItemName());
//
//            String sellCostString = "X "+ Money.rupees(
//                    BigDecimal.valueOf(Long.valueOf(product
//                            .getSellMRP()))).toString();
//            vh1.getItem_price().setText(sellCostString);
//            vh1.getItem_count().setText(product.getQuantity());
//            Long totalValueOfEachProduct = getProductTotalValue(Long.valueOf(product.getSellMRP()),
//                    Long.valueOf(product.getQuantity()));
//            vh1.getItem_total_price().setText(Money.rupees(BigDecimal.valueOf(totalValueOfEachProduct)).toString());
//        }
//    }
    private void configureLayoutHeader(final CheckoutAdapter.ListHeaderViewHolder listHeaderViewHolder,final int position) {
        ListHeader listHeader = (ListHeader) items.get(position);
        if (listHeader != null) {
            listHeaderViewHolder.getItem_title().setText(listHeader.getHeaderTitle());
        }
    }
    private void configureLayoutPaymentDetails(final CheckoutAdapter.PaymentDetailsViewHolder paymentDetailsViewHolder,final int position) {
        PaymentOption paymentOption = (PaymentOption) items.get(position);
        if (paymentOption != null) {
            if(paymentOption.getSelectedPaymentOption() == PaymentOption.PAYMENT_OPTION.CARD_ON_DELIVERY){
                paymentDetailsViewHolder.getItem_radio_group().check(paymentDetailsViewHolder.getItem_card_radio_button().getId());
            }
            else if(paymentOption.getSelectedPaymentOption() == PaymentOption.PAYMENT_OPTION.CASH_ON_DELIVERY){
                paymentDetailsViewHolder.getItem_radio_group().check(paymentDetailsViewHolder.getItem_cash_radio_button().getId());
            }
            else if(paymentOption.getSelectedPaymentOption() == PaymentOption.PAYMENT_OPTION.PAYTM_ON_DELIVERY){
                paymentDetailsViewHolder.getItem_radio_group().check(paymentDetailsViewHolder.getItem_paytm_radio_button().getId());
            }
        }
    }
    private void configureLayoutDeliveryDetails(final CheckoutAdapter.DeliveryDetailsViewHolder deliveryDetailsViewHolder,final int position) {
        Checkout checkout = (Checkout) items.get(position);
        if (checkout != null) {
            if(checkout.getCheckoutItemType() == Checkout.CHECKOUT_ITEM_TYPE.ADDRESS){
                if(checkout.getAddressProvided()){
                    deliveryDetailsViewHolder.getItem_title().setText(checkout.getAddress());
                    deliveryDetailsViewHolder.getItem_chevron().setVisibility(View.GONE);
                }
                else{
                    deliveryDetailsViewHolder.getItem_title().setText("Enter Delivery Address");
                    deliveryDetailsViewHolder.getItem_title().setTextColor(checkoutActivity.getResources().getColor(R.color.primary_dark));
                    deliveryDetailsViewHolder.getItem_chevron().setVisibility(View.VISIBLE);
                }

            }
            else if(checkout.getCheckoutItemType() == Checkout.CHECKOUT_ITEM_TYPE.PHONE){
                if(checkout.getIsPhoneNumberProvided()){
                    deliveryDetailsViewHolder.getItem_title().setText(checkout.getPhoneNumber());
                    deliveryDetailsViewHolder.getItem_chevron().setVisibility(View.GONE);
                }
                else{
                    deliveryDetailsViewHolder.getItem_title().setText("Enter Mobile Number");
                    deliveryDetailsViewHolder.getItem_title().setTextColor(checkoutActivity.getResources().getColor(R.color.primary_dark));
                    deliveryDetailsViewHolder.getItem_chevron().setVisibility(View.VISIBLE);
                }
                deliveryDetailsViewHolder.getItem_title().setText(checkout.getPhoneNumber());
            }
            else if(checkout.getCheckoutItemType() == Checkout.CHECKOUT_ITEM_TYPE.NAME){

            }
        }
    }
    class PaymentDetailsViewHolder extends RecyclerView.ViewHolder{
        private RadioGroup item_radio_group;
        private RadioButton item_card_radio_button;
        private RadioButton item_cash_radio_button;
        private RadioButton item_paytm_radio_button;

        public RadioButton getItem_card_radio_button() {
            return item_card_radio_button;
        }

        public void setItem_card_radio_button(RadioButton item_card_radio_button) {
            this.item_card_radio_button = item_card_radio_button;
        }

        public RadioButton getItem_cash_radio_button() {
            return item_cash_radio_button;
        }

        public void setItem_cash_radio_button(RadioButton item_cash_radio_button) {
            this.item_cash_radio_button = item_cash_radio_button;
        }

        public RadioButton getItem_paytm_radio_button() {
            return item_paytm_radio_button;
        }

        public void setItem_paytm_radio_button(RadioButton item_paytm_radio_button) {
            this.item_paytm_radio_button = item_paytm_radio_button;
        }

        public RadioGroup getItem_radio_group() {
            return item_radio_group;
        }
        public void setItem_radio_group(RadioGroup item_radio_group) {
            this.item_radio_group = item_radio_group;
        }
        public PaymentDetailsViewHolder(View v) {
            super(v);
            item_radio_group = (RadioGroup) v.findViewById(R.id.paymentRadioGroup);
            item_card_radio_button = (RadioButton)v.findViewById(R.id.cardondelivery);
            item_cash_radio_button = (RadioButton)v.findViewById(R.id.cashondelivery);
            item_paytm_radio_button = (RadioButton)v.findViewById(R.id.paytmondelivery);
        }
    }
    class ListHeaderViewHolder extends RecyclerView.ViewHolder{
        private TextView item_title;
        public TextView getItem_title() {
            return item_title;
        }
        public void setItem_title(TextView item_title) {
            this.item_title = item_title;
        }
        public ListHeaderViewHolder(View v) {
            super(v);
            item_title = (TextView) v.findViewById(R.id.item_title);
        }
    }
    class ListDividerHolder extends RecyclerView.ViewHolder{
        public ListDividerHolder(View v) {
            super(v);
        }
    }
    class DeliveryDetailsViewHolder extends RecyclerView.ViewHolder{
        private TextView item_title;
        private ImageView item_chevron;

        public ImageView getItem_chevron() {
            return item_chevron;
        }
        public void setItem_chevron(ImageView item_chevron) {
            this.item_chevron = item_chevron;
        }
        public TextView getItem_title() {
            return item_title;
        }
        public void setItem_title(TextView item_title) {
            this.item_title = item_title;
        }
        public DeliveryDetailsViewHolder(View v) {
            super(v);
            item_title = (TextView) v.findViewById(R.id.item_title);
            item_chevron = (ImageView)v.findViewById(R.id.item_chevron);
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
