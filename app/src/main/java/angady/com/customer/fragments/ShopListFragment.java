package angady.com.customer.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
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
import angady.com.customer.LoginActivity;
import angady.com.customer.R;
import angady.com.customer.activities.ProductListActivity;
import angady.com.customer.adapters.OnItemClickListener;
import angady.com.customer.adapters.ShopListRecyclerViewAdapter;
import angady.com.customer.data.remote.ShopApi;
import angady.com.customer.model.entities.LoginResponse;
import angady.com.customer.model.entities.ShopListResponse;
import angady.com.customer.model.network.AuthorizeRequest;
import angady.com.customer.model.network.ShopListRequest;
import angady.com.customer.utils.Constants;
import angady.com.customer.utils.CustomAlertDialog;
import angady.com.customer.utils.PreferenceHelper;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static angady.com.customer.utils.Constants.BUNDLE_KEY_SHOP_NAME;


public class ShopListFragment extends BaseFragment {
    @BindView(R.id.RecyclerView)
    RecyclerView shopListRecyclerView;
    @Inject
    ShopApi shopApi;
    private ShopListRecyclerViewAdapter shopListRecyclerViewAdapter;
    public ShopListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ShopListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ShopListFragment newInstance() {
        ShopListFragment fragment = new ShopListFragment();
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

        shopListRecyclerViewAdapter = new ShopListRecyclerViewAdapter();
        shopListRecyclerView.setAdapter(shopListRecyclerViewAdapter);
        shopListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        shopListRecyclerView.setAdapter(shopListRecyclerViewAdapter);
        PreferenceHelper preferenceHelper = PreferenceHelper.getPrefernceHelperInstace();

        HashMap<String, String> header = new HashMap<>();
        header.put("Authorization-ID", preferenceHelper.getString(getActivity(),Constants.PREF_KEY_TOKEN, "" ));
        header.put("User-ID", preferenceHelper.getString(getActivity(), Constants.PREF_KEY_LOGIN_ID, ""));
        ShopListRequest shopListRequest = new ShopListRequest();
        shopListRequest.setLocation("uppal");
        shopApi.getShopsFromLocation(header, shopListRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ShopListResponse>() {
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
                    public void onNext(ShopListResponse shopListResponse){
                        progressDialog.dismiss();
                        if(shopListResponse.getStatus() == 200) {
                            shopListRecyclerViewAdapter.setItems(getShopsList(shopListResponse));
                            shopListRecyclerViewAdapter.notifyDataSetChanged();
                        }
                        else if(shopListResponse.getStatus() == 206){
                            //display error.
//                            new CustomAlertDialog().showAlert1(
//                                    getActivity(),
//                                    R.string.text_login_failed,
//                                    loginResponse.getMessage(),
//                                    null);
                        }
                        else if(shopListResponse.getStatus() == 401){
                            //display login screen after clearing session from shared preferences.
                        }

                    }
                });


        shopListRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(String storeName) {
                Intent intent = new Intent(getActivity(), ProductListActivity.class);
                intent.putExtra(BUNDLE_KEY_SHOP_NAME, storeName);
                startActivity(intent);
            }
        });
        return view;
    }
    private ArrayList<Object> getShopsList(ShopListResponse response){
        ArrayList<Object> items = new ArrayList<>();
        if(response.getShop() != null) {
            items.addAll(response.getShop());
        }
        return items;
    }
//    private ArrayList<Object> getSampleArrayList() {
//        ArrayList<Object> items = new ArrayList<>();
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
//        return items;
//    }
}
