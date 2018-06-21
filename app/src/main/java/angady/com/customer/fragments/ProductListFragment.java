package angady.com.customer.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import angady.com.customer.R;
import angady.com.customer.activities.ProductListActivity;
import angady.com.customer.adapters.OnItemClickListener;
import angady.com.customer.adapters.ProductListRecyclerViewAdapter;
import angady.com.customer.model.entities.Product;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by shyam on 2/26/2018.
 */

public class ProductListFragment extends Fragment {
    @BindView(R.id.RecyclerView)
    RecyclerView recyclerView;
    private ProductListRecyclerViewAdapter productListRecyclerViewAdapter;
    public ProductListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ShopListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProductListFragment newInstance() {
        ProductListFragment fragment = new ProductListFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_list, null);
        ButterKnife.bind(this, view);
        productListRecyclerViewAdapter = new ProductListRecyclerViewAdapter((ProductListActivity) getActivity(), getSampleArrayList());
        recyclerView.setAdapter(productListRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        productListRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
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
        items.add(new Product("Item 1", "this is item1", "250", "240"));
        items.add(new Product("Item 2", "this is item2", "240", "230"));
        items.add(new Product("Item 3", "this is item3", "230", "220"));
        items.add(new Product("Item 4", "this is item4", "220", "210"));
        items.add(new Product("Item 5", "this is item5", "210", "200"));
        items.add(new Product("Item 6", "this is item6", "260", "250"));
        items.add(new Product("Item 7", "this is item7", "270", "260"));
        items.add(new Product("Item 8", "this is item8", "290", "280"));
        return items;
    }
}
