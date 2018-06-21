package angady.com.customer.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.List;

import angady.com.customer.R;
import angady.com.customer.model.entities.Money;
import angady.com.customer.model.entities.Order;

/**
 * Created by shyam on 4/2/2018.
 */

public class OrderHistoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Object> items;
    private final int ORDER_LIST_ITEM = 0;
    private OnItemClickListener listener;

    public OrderHistoryAdapter(){}
    public void setItems(List<Object> items) {
        this.items = items;
    }
    public void setOnItemClickListener(OnItemClickListener clickListener){
        this.listener = clickListener;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        if(viewType == ORDER_LIST_ITEM){
            View v1 = inflater.inflate(R.layout.layout_order_history_list_item, viewGroup, false);
            viewHolder = new OrderHistoryAdapter.ViewHolder1(v1);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        switch (viewHolder.getItemViewType()) {
            case ORDER_LIST_ITEM:
                OrderHistoryAdapter.ViewHolder1 vh1 = (OrderHistoryAdapter.ViewHolder1) viewHolder;
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
        if (items.get(position) instanceof Order) {
            return ORDER_LIST_ITEM;
        }
        return -1;
    }
    private void configureViewHolder1(OrderHistoryAdapter.ViewHolder1 vh1, int position) {
        Order order = (Order) items.get(position);
        if (order != null) {
            vh1.getOrderNumber().setText("Order Number : "+order.getOrderID());
            vh1.getOrderDate().setText(order.getOrderDate());
            vh1.getQuantity().setText("Total "+(position+1)*2+" Item");
            String orderAmount = Money.rupees(BigDecimal.valueOf(Long.valueOf(order.getOrderAmount()))).toString();
            vh1.getTotalPrice().setText(orderAmount);
            vh1.bind(order, listener);
        }
    }


    class ViewHolder1 extends RecyclerView.ViewHolder {

        private TextView orderNumber, quantity, status, orderDate, totalPrice;

        public TextView getOrderNumber() {
            return orderNumber;
        }

        public void setOrderNumber(TextView orderNumber) {
            this.orderNumber = orderNumber;
        }

        public TextView getQuantity() {
            return quantity;
        }

        public void setQuantity(TextView quantity) {
            this.quantity = quantity;
        }

        public TextView getStatus() {
            return status;
        }

        public void setStatus(TextView status) {
            this.status = status;
        }

        public TextView getOrderDate() {
            return orderDate;
        }

        public void setOrderDate(TextView orderDate) {
            this.orderDate = orderDate;
        }

        public TextView getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(TextView totalPrice) {
            this.totalPrice = totalPrice;
        }

        public ViewHolder1(View v) {
            super(v);
            orderNumber = (TextView) v.findViewById(R.id.order_number);
            quantity = (TextView) v.findViewById(R.id.quantity);
            status = (TextView)v.findViewById(R.id.status);
            orderDate = (TextView)v.findViewById(R.id.order_date);
            totalPrice = (TextView)v.findViewById(R.id.item_total_price);

        }
        public void bind(final Order order, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(order.getOrderAmount());
                }
            });
        }
    }

}
