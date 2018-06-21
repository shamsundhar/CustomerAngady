package angady.com.customer.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import angady.com.customer.BaseActivity;
import angady.com.customer.R;
import angady.com.customer.adapters.ProductListRecyclerViewAdapter;
import angady.com.customer.adapters.ViewCartListAdapter;
import angady.com.customer.model.CenterRepository;
import angady.com.customer.model.entities.Money;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static angady.com.customer.utils.Constants.BUNDLE_KEY_TOTAL_PAYABLE_AMOUNT;

public class ChangeLocationActivity extends BaseActivity {
    @BindView(R.id.RecyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    ViewCartListAdapter viewCartListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cart);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
//        viewCartListAdapter = new ViewCartListAdapter(ChangeLocationActivity.this, CenterRepository.getCenterRepository().getListOfProductsInShoppingList());
//        recyclerView.setAdapter(viewCartListAdapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(ChangeLocationActivity.this));
    }
}
