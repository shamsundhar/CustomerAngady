package angady.com.customer.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import angady.com.customer.R;
import angady.com.customer.activities.CheckoutActivity;
import angady.com.customer.activities.ProductListActivity;
import angady.com.customer.adapters.CheckoutAdapter;
import angady.com.customer.adapters.OnItemClickListener;
import angady.com.customer.model.entities.Checkout;
import angady.com.customer.model.entities.Divider;
import angady.com.customer.model.entities.ListHeader;
import angady.com.customer.model.entities.PaymentOption;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static angady.com.customer.utils.Constants.BUNDLE_KEY_SHOP_NAME;

/**
 * Created by shyam on 3/4/2018.
 */

public class CheckoutFragment extends Fragment {
    @BindView(R.id.RecyclerView)
    RecyclerView checkOutRecyclerView;
    @BindView(R.id.confirmButton)
    Button confirmButton;
    private CheckoutAdapter checkoutAdapter;
    public CheckoutFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ShopListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CheckoutFragment newInstance() {
        CheckoutFragment fragment = new CheckoutFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_checkout, null);
        ButterKnife.bind(this, view);
        checkoutAdapter = new CheckoutAdapter((CheckoutActivity) getActivity(), getSampleArrayList());
        checkOutRecyclerView.setAdapter(checkoutAdapter);
        checkOutRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        checkoutAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(String storeName) {
//                Intent intent = new Intent(getActivity(), ProductListActivity.class);
//                intent.putExtra(BUNDLE_KEY_SHOP_NAME, storeName);
//                startActivity(intent);
            }
        });
        return view;
    }
    private ArrayList<Object> getSampleArrayList() {
        ArrayList<Object> items = new ArrayList<>();
        ListHeader listHeader = new ListHeader();
        listHeader.setHeaderTitle("Delivery Details");
        items.add(listHeader);
        Checkout checkout = new Checkout();
        checkout.setCheckoutItemType(Checkout.CHECKOUT_ITEM_TYPE.PHONE);
        checkout.setIsPhoneNumberProvided(false);
        checkout.setPhoneNumber("Provide Mobile Number");
        items.add(checkout);
        items.add(new Divider());
        Checkout checkout1 = new Checkout();
        checkout1.setCheckoutItemType(Checkout.CHECKOUT_ITEM_TYPE.ADDRESS);
        checkout1.setAddressProvided(true);
        checkout1.setAddress("(Home) Flat:104, shiv sakthi enclave, westend colony, hyderabad, 500008");
        items.add(checkout1);
        ListHeader listHeader1 = new ListHeader();
        listHeader1.setHeaderTitle("Payment Details");
        items.add(listHeader1);
        PaymentOption paymentOption = new PaymentOption();
        paymentOption.setSelectedPaymentOption(PaymentOption.PAYMENT_OPTION.PAYTM_ON_DELIVERY);
        items.add(paymentOption);

        return items;
    }
    @OnClick(R.id.confirmButton)
    public void clickOnConfirmButton(){
        ((CheckoutActivity)getActivity()).displayOrderTrackFragment();
    }
}
