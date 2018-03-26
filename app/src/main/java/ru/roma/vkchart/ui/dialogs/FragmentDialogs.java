package ru.roma.vkchart.ui.dialogs;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
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
import ru.roma.vkchart.models.entities.Dialog;
import ru.roma.vkchart.ui.adapters.DialogsAdapter;
import ru.roma.vkchart.utils.MyLog;


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
            presenter.getDialogs();
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
        final LinearLayoutManager lm = new LinearLayoutManager(getContext());
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        lm.setReverseLayout(false);


        list.setLayoutManager(lm);
        list.setAdapter(adapter);
        list.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                super.onScrolled(recyclerView, dx, dy);
                int visibleItemCount        = lm.getChildCount();
                int totalItemCount          = lm.getItemCount();
                int firstVisibleItemPosition= lm.findFirstVisibleItemPosition();

                // Load more if we have reach the end to the recyclerView
                if ( (visibleItemCount + firstVisibleItemPosition) >= totalItemCount && firstVisibleItemPosition >= 0) {
                   presenter.getDialogs();
                }
            }
            });
    }

    @Override
    public void updateList(List<Dialog> dataDialogs) {

        adapter.setDialogs(dataDialogs);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
