package ru.roma.vkchart.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ru.roma.vkchart.R;
import ru.roma.vkchart.adapters.DialogsAdapter;
import ru.roma.vkchart.holder.DataDialogs;
import ru.roma.vkchart.presenters.DialogFragmentPresenter;
import ru.roma.vkchart.utils.MyLog;
import ru.roma.vkchart.view.PositionListener;
import ru.roma.vkchart.view.ViewDialog;


public class FragmentDialogs extends Fragment implements SwipeRefreshLayout.OnRefreshListener, ViewDialog {


    RecyclerView listDialogs;

    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout swiperefresh;
    Unbinder unbinder;
    @BindView(R.id.frame_layout_dialog)
    FrameLayout frameLayoutDialog;

    private boolean firstCreate = true;

    private Parcelable recycleViewState;

    private DialogFragmentPresenter presenter;

    private OnFragmentInteractionListener mListener;

    private DialogsAdapter adapter;


    public FragmentDialogs() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MyLog.log("onCreate() FragmentDialogs");

        setRetainInstance(true);

        presenter = new DialogFragmentPresenter(this);
        adapter = new DialogsAdapter();
        adapter.setPositionListener(new PositionListener() {
            @Override
            public void lastPosition(int position) {
                presenter.getListData(position);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fragment_dialogs, container, false);
        unbinder = ButterKnife.bind(this, view);

        listDialogs = view.findViewById(R.id.list_dialogs);


        initilializeRecycleList(listDialogs);

        if (recycleViewState != null) {

            listDialogs.getLayoutManager().onRestoreInstanceState(recycleViewState);
        }
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (firstCreate) {
            presenter.getListData(0);
            firstCreate = false;
        }
    }

    @Override
    public void onResume() {
        MyLog.log("onResume  FragmentDialogs");
        super.onResume();
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

    @Override
    public void onRefresh() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        recycleViewState = listDialogs.getLayoutManager().onSaveInstanceState();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detach();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    private void initilializeRecycleList(RecyclerView list) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.setReverseLayout(false);


        list.setLayoutManager(layoutManager);
        list.setAdapter(adapter);
    }

    @Override
    public void updateList(List<DataDialogs> dataDialogs) {

        adapter.setDialogs(dataDialogs);
    }

    @Override
    public void failed(String message) {
        Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
