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

import angady.com.customer.R;
import angady.com.customer.adapters.ProductListRecyclerViewAdapter;
import angady.com.customer.adapters.ViewCartListAdapter;
import angady.com.customer.model.CenterRepository;
import angady.com.customer.model.entities.Money;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static angady.com.customer.utils.Constants.BUNDLE_KEY_TOTAL_PAYABLE_AMOUNT;

public class ViewCartActivity extends AppCompatActivity {
    @BindView(R.id.RecyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.total_payable_amount)
    TextView totalPayableAmount;
    @BindView(R.id.continueButton)
    Button continueButton;
    ViewCartListAdapter viewCartListAdapter;
    private BigDecimal totalAmount = new BigDecimal(BigInteger.ZERO);
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
        viewCartListAdapter = new ViewCartListAdapter(ViewCartActivity.this, CenterRepository.getCenterRepository().getListOfProductsInShoppingList());
        recyclerView.setAdapter(viewCartListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ViewCartActivity.this));
        totalPayableAmount.setSelected(true);
        totalPayableAmount.setText(Money.rupees(totalAmount).toString());
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            if(bundle.containsKey(BUNDLE_KEY_TOTAL_PAYABLE_AMOUNT)){
                totalAmount = (BigDecimal) bundle.get(BUNDLE_KEY_TOTAL_PAYABLE_AMOUNT);
                totalPayableAmount.setText(
                        Money.rupees(totalAmount)
                                .toString()
                );
            }
        }
    }
    public void updateTotalPayableAmount(BigDecimal amount, boolean increment) {
        if (increment) {
            totalAmount = totalAmount.add(amount);
        } else {
            if (totalAmount.signum() == 1)
                totalAmount = totalAmount.subtract(amount);
        }
        totalPayableAmount.setText(Money.rupees(totalAmount).toString());
    }
    @OnClick(R.id.continueButton)
    public void clickContinueButton(){
        Intent intent = new Intent(ViewCartActivity.this, CheckoutActivity.class);
        intent.putExtras(getIntent().getExtras());
        startActivity(intent);
    }
}
