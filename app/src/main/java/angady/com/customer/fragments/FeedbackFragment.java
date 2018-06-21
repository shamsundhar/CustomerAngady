package angady.com.customer.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import angady.com.customer.BuildConfig;
import angady.com.customer.R;
import butterknife.BindView;
import butterknife.ButterKnife;

import static angady.com.customer.utils.Constants.BUNDLE_KEY_MOBILE_NUMBER;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FeedbackFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FeedbackFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FeedbackFragment extends Fragment {
    @BindView(R.id.about_us)
    TextView _aboutUsText;

    public FeedbackFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FeedbackFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FeedbackFragment newInstance() {
        FeedbackFragment fragment = new FeedbackFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feedback, null);
        ButterKnife.bind(this, view);
        String text = String.format(getActivity().getString(R.string.text_about_us),
                BuildConfig.VERSION_NAME);
        CharSequence styledText = Html.fromHtml(text);
        _aboutUsText.setText(styledText);
        // Inflate the layout for this fragment
        return view;
    }
}
