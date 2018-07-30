package ru.roma.vkchart.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ru.roma.vkchart.R;
import ru.roma.vkchart.domain.entities.Dialog;
import ru.roma.vkchart.ui.MyApplication;
import ru.roma.vkchart.ui.adapters.DialogDiffUtil;
import ru.roma.vkchart.ui.adapters.DialogsAdapter;
import ru.roma.vkchart.ui.presenter.DialogPresenter;
import ru.roma.vkchart.utils.MyLog;


public class DialogsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, DialogPresenter.DialogView {

    @BindView(R.id.list_dialogs)
    RecyclerView listDialogs;
    @BindView(R.id.frame_layout_dialog)
    FrameLayout frameLayoutDialog;
    Unbinder unbinder;
    @Inject
    DialogPresenter presenter;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.toolbar_header)
    TextView toolbarHeader;
    private Parcelable recycleViewState;
    private OnFragmentInteractionListener mListener;
    private DialogsAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyLog.log("onCreate() DialogsFragment");
        setRetainInstance(true);
        MyApplication.getInstance().getAppComponent().inject(this);
        adapter = new DialogsAdapter();
        adapter.setOnItemClickListener(new DialogsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Dialog dialog) {

                Bundle user = new Bundle();
                user.putInt("userId", dialog.getUserId());
                user.putString("userName", dialog.getUserName());
                user.putString("photo", dialog.getPhoto100());
                user.putInt("online", dialog.getOnline());

                mListener.onFragmentInteraction(user);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_dialogs, container, false);
        unbinder = ButterKnife.bind(this, view);
        initializeSwipeRefresh();
        initializeRecycleList(listDialogs);

        presenter.setView(this);

        if (recycleViewState != null) {
            listDialogs.getLayoutManager().onRestoreInstanceState(recycleViewState);
        }
        return view;
    }

    private void initializeSwipeRefresh() {
        swipeRefresh.setOnRefreshListener(this);
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

        swipeRefresh.post(new Runnable() {
            @Override
            public void run() {
                presenter.getDialogs(adapter.getItemCount());
            }
        });
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
        presenter.getDialogs(0);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        recycleViewState = listDialogs.getLayoutManager().onSaveInstanceState();
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detach();
    }

    private void initializeRecycleList(RecyclerView list) {
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
                int visibleItemCount = lm.getChildCount();
                int totalItemCount = lm.getItemCount();
                int firstVisibleItemPosition = lm.findFirstVisibleItemPosition();

                // Load more if we have reach the end to the recyclerView
                if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount && firstVisibleItemPosition >= 0) {
                    presenter.getDialogs(adapter.getItemCount());
                }
            }
        });
    }

    @Override
    public void showLoading() {
        swipeRefresh.setRefreshing(true);
        toolbarHeader.setText(getContext().getResources().getString(R.string.update));
    }

    @Override
    public void hideLoading() {
        swipeRefresh.setRefreshing(false);
        toolbarHeader.setText(getContext().getResources().getString(R.string.messages));
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void update() {

    }

    @Override
    public void updateListDialogs(List<Dialog> dialogs) {
        DialogDiffUtil diffUtil = new DialogDiffUtil(adapter.getData(), dialogs);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffUtil);
        adapter.setListDialogs(dialogs);
        diffResult.dispatchUpdatesTo(adapter);
    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Bundle user);
    }
}
