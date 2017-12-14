package com.gnt.moviesapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gnt.utils.RecyclerAdapterMovies;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ShowsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ShowsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShowsFragment extends Fragment {
    private static final String TAG = "RecyclerViewFragment";
    private static final String KEY_LAYOUT_MANAGER = "layoutManager";
    private static final int SPAN_COUNT = 2;

    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }

    private ShowsFragment.LayoutManagerType mCurrentLayoutManagerType;
    private RecyclerView rvShows;
    private RecyclerView.LayoutManager lmShows;
    private RecyclerAdapterMovies showsAdapter;

    private static final String USERNAME = "username";
    private static final String TOKEN = "token";

    private String username;
    private String token;

    private ShowsFragment.OnFragmentInteractionListener mListener;

    public ShowsFragment() {
        // Required empty public constructor
    }

    public static ShowsFragment newInstance(){
        ShowsFragment fragment = new ShowsFragment();
        return fragment;
    }

    public static ShowsFragment newInstance(String username, String token) {
        ShowsFragment fragment = new ShowsFragment();
        Bundle args = new Bundle();
        args.putString(USERNAME, username);
        args.putString(TOKEN, token);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            username = getArguments().getString(USERNAME);
            token = getArguments().getString(TOKEN);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        rvShows = rootView.findViewById(R.id.recycler);
        lmShows = new GridLayoutManager(getActivity(), 1);
        rvShows.setLayoutManager(lmShows);
        rvShows.setHasFixedSize(true);
        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
