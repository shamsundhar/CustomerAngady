package angady.com.customer.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import angady.com.customer.BaseActivity;
import angady.com.customer.R;
import angady.com.customer.data.remote.ShopApi;
import angady.com.customer.fragments.ProductListFragment;
import angady.com.customer.fragments.ShopListFragment;
import angady.com.customer.model.CenterRepository;
import angady.com.customer.model.entities.Money;
import angady.com.customer.model.entities.Product;
import angady.com.customer.model.network.ProductListRequest;
import angady.com.customer.model.network.ProductListResponse;
import angady.com.customer.model.network.ShopListRequest;
import angady.com.customer.utils.Constants;
import angady.com.customer.utils.PreferenceHelper;
import angady.com.customer.utils.TinyDB;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static angady.com.customer.utils.Constants.BUNDLE_KEY_SHOP_ID;
import static angady.com.customer.utils.Constants.BUNDLE_KEY_SHOP_NAME;
import static angady.com.customer.utils.Constants.BUNDLE_KEY_TOTAL_PAYABLE_AMOUNT;

public class ProductListActivity extends BaseActivity {
    static ViewPager viewPager;
    static TabLayout tabLayout;
    private TextView checkOutAmount;
    private TextView itemCountTextView;
    @Inject
    ShopApi shopApi;
    @BindView(R.id.viewCart)
    TextView viewCart;
    private int itemCount = 0;
    private String TAG = "ProductListActivity";
    private BigDecimal checkoutAmount = new BigDecimal(BigInteger.ZERO);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        ButterKnife.bind(this);
        activityComponent().inject(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            if(bundle.containsKey(BUNDLE_KEY_SHOP_NAME)){
                setTitle(bundle.getString(BUNDLE_KEY_SHOP_NAME));
            }
        }
        final ProgressDialog progressDialog = new ProgressDialog(ProductListActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Loading please wait...");
        progressDialog.show();
        PreferenceHelper preferenceHelper = PreferenceHelper.getPrefernceHelperInstace();

        HashMap<String, String> header = new HashMap<>();
        header.put("Authorization-ID", preferenceHelper.getString(ProductListActivity.this, Constants.PREF_KEY_TOKEN, "" ));
        header.put("User-ID", preferenceHelper.getString(ProductListActivity.this, Constants.PREF_KEY_LOGIN_ID, ""));
        ProductListRequest productListRequest = new ProductListRequest();
        productListRequest.setShopkeeperId(bundle.getString(BUNDLE_KEY_SHOP_ID));
        shopApi.getProductsByShop(header, productListRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ProductListResponse>() {
                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ProductListResponse productListResponse) {
                        Log.d(TAG +" Product name ",productListResponse.getProducts().get(0).getProduct_name());
                    }
                });
        if (viewPager != null) {
            setupViewPager(viewPager);
            tabLayout.setupWithViewPager(viewPager);
        }

        CenterRepository.getCenterRepository().setListOfProductsInShoppingList(
                new TinyDB(getApplicationContext()).getListObject(
                        PreferenceHelper.MY_CART_LIST_LOCAL, Product.class));

        itemCount = CenterRepository.getCenterRepository().getListOfProductsInShoppingList()
                .size();


        itemCountTextView = (TextView) findViewById(R.id.item_count);
        itemCountTextView.setSelected(true);
        itemCountTextView.setText(String.valueOf(itemCount));

        checkOutAmount = (TextView) findViewById(R.id.checkout_amount);
        checkOutAmount.setSelected(true);
        checkOutAmount.setText(Money.rupees(checkoutAmount).toString());


    }
    private void setTitle(String title){
        getSupportActionBar().setTitle(title);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }
    @OnClick(R.id.viewCart)
    public void clickOnViewCart(){
        Intent intent = new Intent(this, ViewCartActivity.class);
        intent.putExtra(BUNDLE_KEY_TOTAL_PAYABLE_AMOUNT, checkoutAmount);
        startActivity(intent);
    }
    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        ProductListFragment fragment = ProductListFragment.newInstance();
        Bundle bundle = new Bundle();
        bundle.putInt("type", 1);
        fragment.setArguments(bundle);
        adapter.addFragment(fragment, getString(R.string.item_1));
        fragment = ProductListFragment.newInstance();
        bundle = new Bundle();
        bundle.putInt("type", 2);
        fragment.setArguments(bundle);
        adapter.addFragment(fragment, getString(R.string.item_2));
        fragment = ProductListFragment.newInstance();
        bundle = new Bundle();
        bundle.putInt("type", 3);
        fragment.setArguments(bundle);
        adapter.addFragment(fragment, getString(R.string.item_3));
        fragment = ProductListFragment.newInstance();
        bundle = new Bundle();
        bundle.putInt("type", 4);
        fragment.setArguments(bundle);
        adapter.addFragment(fragment, getString(R.string.item_4));
        fragment = ProductListFragment.newInstance();
        bundle = new Bundle();
        bundle.putInt("type", 5);
        fragment.setArguments(bundle);
        adapter.addFragment(fragment, getString(R.string.item_5));
        fragment = ProductListFragment.newInstance();
        bundle = new Bundle();
        bundle.putInt("type", 6);
        fragment.setArguments(bundle);
        adapter.addFragment(fragment, getString(R.string.item_6));
        viewPager.setAdapter(adapter);
        fragment = ProductListFragment.newInstance();
        bundle = new Bundle();
        bundle.putInt("type", 7);
        fragment.setArguments(bundle);
        adapter.addFragment(fragment, getString(R.string.item_7));
        viewPager.setAdapter(adapter);
    }


    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }

    public void updateItemCount(boolean ifIncrement) {
        if (ifIncrement) {
            itemCount++;
            itemCountTextView.setText(String.valueOf(itemCount));

        } else {
            itemCountTextView.setText(String.valueOf(itemCount <= 0 ? 0
                    : --itemCount));
        }
    }

    public void updateCheckOutAmount(BigDecimal amount, boolean increment) {

        if (increment) {
            checkoutAmount = checkoutAmount.add(amount);
        } else {
            if (checkoutAmount.signum() == 1)
                checkoutAmount = checkoutAmount.subtract(amount);
        }

        checkOutAmount.setText(Money.rupees(checkoutAmount).toString());
    }
}
