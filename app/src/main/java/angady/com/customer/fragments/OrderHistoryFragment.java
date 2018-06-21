package angady.com.customer.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;

import javax.inject.Inject;

import angady.com.customer.BaseFragment;
import angady.com.customer.R;
import angady.com.customer.activities.ProductListActivity;
import angady.com.customer.adapters.OnItemClickListener;
import angady.com.customer.adapters.OrderHistoryAdapter;
import angady.com.customer.adapters.ShopListRecyclerViewAdapter;
import angady.com.customer.data.remote.LoginApi;
import angady.com.customer.data.remote.ShopApi;
import angady.com.customer.model.entities.OrderListResponse;
import angady.com.customer.model.entities.Shop;
import angady.com.customer.model.entities.ShopListResponse;
import angady.com.customer.model.network.UserIdRequest;
import angady.com.customer.utils.Constants;
import angady.com.customer.utils.PreferenceHelper;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static angady.com.customer.utils.Constants.BUNDLE_KEY_SHOP_NAME;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OrderHistoryFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link OrderHistoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrderHistoryFragment extends BaseFragment {
    @BindView(R.id.RecyclerView)
    RecyclerView orderHistoryRecyclerView;
    @Inject
    LoginApi loginApi;
    private OrderHistoryAdapter orderHistoryAdapter;
    public OrderHistoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment OrderHistoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OrderHistoryFragment newInstance() {
        OrderHistoryFragment fragment = new OrderHistoryFragment();
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recyclerview, null);
        ButterKnife.bind(this, view);
        fragmentComponent().inject(this);
        final ProgressDialog progressDialog = new ProgressDialog(getActivity(),
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Loading please wait...");
        progressDialog.show();
        orderHistoryAdapter = new OrderHistoryAdapter();
        orderHistoryRecyclerView.setAdapter(orderHistoryAdapter);
        orderHistoryRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        PreferenceHelper preferenceHelper = PreferenceHelper.getPrefernceHelperInstace();

        HashMap<String, String> header = new HashMap<>();
        header.put("Authorization-ID", preferenceHelper.getString(getActivity(), Constants.PREF_KEY_TOKEN, "" ));
        header.put("User-ID", preferenceHelper.getString(getActivity(), Constants.PREF_KEY_LOGIN_ID, ""));
        UserIdRequest userIdRequest = new UserIdRequest();
        userIdRequest.setUserId(preferenceHelper.getString(getActivity(), Constants.PREF_KEY_LOGIN_ID, ""));
        loginApi.getUserOrders(header, userIdRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<OrderListResponse>() {
                    @Override
                    public void onError(Throwable e) {
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(OrderListResponse orderListResponse){
                        progressDialog.dismiss();
                        if(orderListResponse.getStatus() == 200) {
                            orderHistoryAdapter.setItems(getOrders(orderListResponse));
                            orderHistoryAdapter.notifyDataSetChanged();
                        }
                        else if(orderListResponse.getStatus() == 206){
                            //display error.
//                            new CustomAlertDialog().showAlert1(
//                                    getActivity(),
//                                    R.string.text_login_failed,
//                                    loginResponse.getMessage(),
//                                    null);
                        }
                        else if(orderListResponse.getStatus() == 401){
                            //display login screen after clearing session from shared preferences.
                        }

                    }
                });

        orderHistoryAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(String storeName) {

            }
        });
        return view;
    }
    private ArrayList<Object> getOrders(OrderListResponse response){
        ArrayList<Object> items = new ArrayList<>();
        if(response.getOrder() != null) {
            items.add(response.getOrder());
        }
        return items;
    }
    private ArrayList<Object> getSampleArrayList() {
        ArrayList<Object> items = new ArrayList<>();
//        items.add(new Shop("Prasanna Mart1", "T Nagar"));
//        items.add(new Shop("Prasanna Mart2", "Bangalore"));
//        items.add(new Shop("Prasanna Mart3", "Hyderabad"));
//        items.add(new Shop("Prasanna Mart4", "Secunderabad"));
//        items.add(new Shop("Prasanna Mart5", "vijayawada"));
//        items.add(new Shop("Prasanna Mart6", "Vishakapatnam"));
//        items.add(new Shop("Prasanna Mart7", "Kurnool"));
//        items.add(new Shop("Prasanna Mart8", "Kadapa"));
//        items.add(new Shop("Prasanna Mart9", "Chennai"));
//        items.add(new Shop("Prasanna Mart10", "Cochin"));
        return items;
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
}
