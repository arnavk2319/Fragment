package com.arnav.akapplications.fragmentexample;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;


///**
// * A simple {@link Fragment} subclass.
// * Activities that contain this fragment must implement the
// * {@link SimpleFragment.OnFragmentInteractionListener} interface
// * to handle interaction events.
// * Use the {@link SimpleFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class SimpleFragment extends Fragment {
    final static private int YES = 0;
    final static private int NO = 1;

    private static final int NONE = 0;

    private static final String CHOICE = "choice";

    public int radioButtonChoice = NONE;

    OnFragmentInteractionListener fragmentInteractionListener;

    RatingBar ratingBar;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

//    private OnFragmentInteractionListener mListener;

    public SimpleFragment() {
        // Required empty public constructor
    }

//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment SimpleFragment.
//     */
//    // TODO: Rename and change types and number of parameters
    public static SimpleFragment newInstance(int choice)
    {
        SimpleFragment simpleFragment = new SimpleFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(CHOICE,choice);
        simpleFragment.setArguments(arguments);
        return simpleFragment;
    }


//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        final View rootView = inflater.inflate(R.layout.fragment_simple,container, false);
        final RadioGroup radioGroup = rootView.findViewById(R.id.radioGroup);

        ratingBar = rootView.findViewById(R.id.ratingBar);
//        LayerDrawable stars = (LayerDrawable)ratingBar.getProgressDrawable();
//        stars.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);


//        if(getArguments().containsKey(CHOICE))
//        {
//            radioButtonChoice = getArguments().getInt(CHOICE);
//            if(radioButtonChoice != NONE)
//            {
//                radioGroup.check(radioGroup.getChildAt(radioButtonChoice).getId());
//            }
//        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                View radioButton = radioGroup.findViewById(i);
                int index = radioGroup.indexOfChild(radioButton);
                TextView textView = rootView.findViewById(R.id.fragment_header);

                switch(index)
                {
                    case YES :
                        textView.setText(R.string.like_message);
                        radioButtonChoice = YES;
                        fragmentInteractionListener.onRadioButtonChoice(YES);
                        break;
                    case NO :
                        textView.setText(R.string.thanks_message);
                        radioButtonChoice = NO;
                        fragmentInteractionListener.onRadioButtonChoice(NO);
                        break;

                    default:


                        break;
                }
            }
        });

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                String finalRating = (getString(R.string.my_rating) + ratingBar.getRating());
                Toast.makeText(getContext(),finalRating,Toast.LENGTH_LONG).show();
            }
        });

        return rootView;
    }

    public interface OnFragmentInteractionListener
    {
        void onRadioButtonChoice(int choice);
    }


//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }
//
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            fragmentInteractionListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }
//
//    /**
//     * This interface must be implemented by activities that contain this
//     * fragment to allow an interaction in this fragment to be communicated
//     * to the activity and potentially other fragments contained in that
//     * activity.
//     * <p>
//     * See the Android Training lesson <a href=
//     * "http://developer.android.com/training/basics/fragments/communicating.html"
//     * >Communicating with Other Fragments</a> for more information.
//     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
}
