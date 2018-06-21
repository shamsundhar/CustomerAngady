package angady.com.customer.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import angady.com.customer.R;
import angady.com.customer.activities.CheckoutActivity;
import angady.com.customer.adapters.CheckoutAdapter;
import angady.com.customer.adapters.OnItemClickListener;
import angady.com.customer.adapters.TrackOrderAdapter;
import angady.com.customer.model.entities.Checkout;
import angady.com.customer.model.entities.Divider;
import angady.com.customer.model.entities.ListHeader;
import angady.com.customer.model.entities.OrderStatus;
import angady.com.customer.model.entities.Orientation;
import angady.com.customer.model.entities.PaymentOption;
import angady.com.customer.model.entities.TimeLineModel;
import butterknife.BindView;
import butterknife.ButterKnife;

import static angady.com.customer.utils.Constants.BUNDLE_KEY_DISPLAY_ORDER_SUCCESS_POPUP;

/**
 * Created by shyam on 3/6/2018.
 */

public class TrackOrderFragment extends Fragment {
    @BindView(R.id.RecyclerView)
    RecyclerView trackOrderRecyclerView;
    private TrackOrderAdapter trackOrderAdapter;
    Dialog dialog;
    private Boolean displayPopup;
    private List<TimeLineModel> mDataList = new ArrayList<>();
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ShopListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TrackOrderFragment newInstance(Boolean displayPopup) {
        TrackOrderFragment fragment = new TrackOrderFragment();
        Bundle args = new Bundle();
        args.putBoolean(BUNDLE_KEY_DISPLAY_ORDER_SUCCESS_POPUP, displayPopup);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recyclerview, null);
        ButterKnife.bind(this, view);
        setDataListItems();
        trackOrderAdapter = new TrackOrderAdapter(mDataList, Orientation.VERTICAL, true);
        trackOrderRecyclerView.setAdapter(trackOrderAdapter);
        trackOrderRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        if(getArguments() != null) {
            displayPopup = getArguments().getBoolean(BUNDLE_KEY_DISPLAY_ORDER_SUCCESS_POPUP);
        }
//        trackOrderAdapter.setOnItemClickListener(new OnItemClickListener() {
//            @Override
//            public void onItemClick(String storeName) {
////                Intent intent = new Intent(getActivity(), ProductListActivity.class);
////                intent.putExtra(BUNDLE_KEY_SHOP_NAME, storeName);
////                startActivity(intent);
//            }
//        });

        if(displayPopup){
            showCustomDialog(getActivity());
        }
        return view;
    }
    private void setDataListItems(){
        mDataList.add(new TimeLineModel("Order Confirmed", "2017-02-11 09:30", OrderStatus.COMPLETED));
        mDataList.add(new TimeLineModel("Order Accepted by Angady", "2017-02-11 10:30", OrderStatus.COMPLETED));
        mDataList.add(new TimeLineModel("Order is getting packed", "2017-02-11 12:30", OrderStatus.ACTIVE));
        mDataList.add(new TimeLineModel("Order is on the way", "", OrderStatus.INACTIVE));
        mDataList.add(new TimeLineModel("Order deivered", "", OrderStatus.INACTIVE));
    }
    public void showCustomDialog(final Context context) {

        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.layout_order_successful, null, false);
        Button okButton = view.findViewById(R.id.okButton);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        ((Activity) context).getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        dialog.setContentView(view);
        final Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawableResource(R.color.cardview_light_background);
        window.setGravity(Gravity.CENTER);
        dialog.show();
    }
}
