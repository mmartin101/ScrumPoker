package com.jackzilla.scrumpoker;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PokerDeckFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PokerDeckFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class PokerDeckFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_DECK_NUMBER = "deck_number";

    // TODO: Rename and change types of parameters
    private int mDeckNumber;
    private RecyclerView mRecyclerView;
    private PokerCardAdapter mAdapter;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment PokerDeckFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PokerDeckFragment newInstance(int param1) {
        PokerDeckFragment fragment = new PokerDeckFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_DECK_NUMBER, param1);
        fragment.setArguments(args);
        return fragment;
    }
    public PokerDeckFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mDeckNumber = getArguments().getInt(ARG_DECK_NUMBER);
        }
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_poker_deck, container, false);
        int arrayId = 0;
        switch (mDeckNumber) {
            case 1:
                arrayId = R.array.default_deck;
                break;
            case 2:
                arrayId = R.array.tshirt_deck;
                break;
            case 3:
                arrayId = R.array.fibonacci_deck;
                break;
            case 4:
                arrayId = R.array.sequential_deck;
                break;
        }

        String cards[] = getResources().getStringArray(arrayId);

        mAdapter = new PokerCardAdapter(cards);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        }
        catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnFragmentInteractionListener");
        }

        ((MainActivity) activity).onSectionAttached(getArguments().getInt(ARG_DECK_NUMBER));
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tv;

        public ViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);
            this.tv = (TextView) v.findViewById(R.id.poker_card_grid_item_text);
        }

        @Override
        public void onClick(View v) {
            int pos = mRecyclerView.getChildPosition(v);
            String cardText = mAdapter.getItem(pos);
            Intent i = new Intent(getActivity(), ShowCardActivity.class);
            i.putExtra("display_string", cardText);
            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), v, "abc");
            ActivityCompat.startActivity(getActivity(), i, options.toBundle());
        }
    }

    public class PokerCardAdapter extends RecyclerView.Adapter<ViewHolder> {
        private String[] mDataSet;

        public PokerCardAdapter(String[] mDataSet) {
            this.mDataSet = mDataSet;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.poker_card_grid_item, viewGroup, false);

            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            viewHolder.tv.setText(mDataSet[i]);

        }

        @Override
        public int getItemCount() {
            return mDataSet.length;
        }

        public String getItem(int i) {
            String value = "";
            if (mDataSet.length > i) {
                value = mDataSet[i];
            }

            return value;
        }
    }

}
