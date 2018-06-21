package angady.com.customer.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.BigInteger;

import angady.com.customer.R;
import angady.com.customer.adapters.ViewCartListAdapter;
import angady.com.customer.fragments.CheckoutFragment;
import angady.com.customer.fragments.ShopListFragment;
import angady.com.customer.fragments.TrackOrderFragment;
import angady.com.customer.model.CenterRepository;
import angady.com.customer.model.entities.Money;
import butterknife.BindView;
import butterknife.ButterKnife;

import static angady.com.customer.utils.Constants.BUNDLE_KEY_TOTAL_PAYABLE_AMOUNT;

/**
 * Created by shyam on 3/2/2018.
 */

public class CheckoutActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    ViewCartListAdapter viewCartListAdapter;
    private BigDecimal totalAmount = new BigDecimal(BigInteger.ZERO);
    private static final String CHECKOUT_FRAGMENT_TAG = "CHECKOUT_FRAGMENT";
    private static final String TRACK_ORDER_FRAGMENT_TAG = "TRACK_ORDER_FRAGMENT";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

//        totalPayableAmount.setSelected(true);
//        totalPayableAmount.setText(Money.rupees(totalAmount).toString());
//        Bundle bundle = getIntent().getExtras();
//        if(bundle != null){
//            if(bundle.containsKey(BUNDLE_KEY_TOTAL_PAYABLE_AMOUNT)){
//                totalAmount = (BigDecimal) bundle.get(BUNDLE_KEY_TOTAL_PAYABLE_AMOUNT);
//                totalPayableAmount.setText(
//                        Money.rupees(totalAmount)
//                                .toString()
//                );
//            }
//        }

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container,  CheckoutFragment.newInstance(), CHECKOUT_FRAGMENT_TAG)
                .commit();
    }
    public void displayOrderTrackFragment(){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container,  TrackOrderFragment.newInstance(true), TRACK_ORDER_FRAGMENT_TAG)
                .commit();
    }
}
