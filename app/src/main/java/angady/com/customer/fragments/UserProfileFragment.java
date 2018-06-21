package angady.com.customer.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import angady.com.customer.BaseFragment;
import angady.com.customer.R;
import butterknife.ButterKnife;

/**
 * Created by shyam on 3/20/2018.
 */

public class UserProfileFragment extends BaseFragment {
    public UserProfileFragment(){

    }
    public static UserProfileFragment newInstance() {
        UserProfileFragment fragment = new UserProfileFragment();
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_profile, null);
        ButterKnife.bind(this, view);

        return view;
    }
}
