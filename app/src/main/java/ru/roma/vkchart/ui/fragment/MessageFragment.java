package ru.roma.vkchart.ui.fragment;


import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import ru.roma.vkchart.R;
import ru.roma.vkchart.domain.entities.Message;
import ru.roma.vkchart.ui.adapters.MessageAdapter;
import ru.roma.vkchart.ui.presenter.MessagePresenter;
import ru.roma.vkchart.utils.MyLog;


public class MessageFragment extends Fragment implements ru.roma.vkchart.ui.fragment.View<Message> {

    private static final String USER_ID = "userId";
    //    @BindView(R.id.list_messages)
    RecyclerView listMessages;
    Unbinder unbinder;
    private int userId;
    private MessageAdapter adapter;
    private MessagePresenter presenter;
    private Parcelable recycleViewState;


    public MessageFragment() {

    }

    public static MessageFragment newInstance(int userId) {
        MessageFragment fragment = new MessageFragment();
        Bundle args = new Bundle();
        args.putInt(USER_ID, userId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        MyLog.log("onCreate() MessageFragment");

        setRetainInstance(true);

        if (getArguments() != null) {
            userId = getArguments().getInt(USER_ID);
        }
        presenter = new MessagePresenter(this);
        adapter = new MessageAdapter();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, container, false);
        unbinder = ButterKnife.bind(this, view);
        listMessages = view.findViewById(R.id.list_messages);
        initilializeRecycleList(listMessages);
        if (recycleViewState != null) {
            listMessages.getLayoutManager().onRestoreInstanceState(recycleViewState);
        }
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter.getMessages(userId);
    }

    @Override
    public void updateList(List<Message> list) {
        adapter.setListMesages(list);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detach();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        recycleViewState = listMessages.getLayoutManager().onSaveInstanceState();
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
                int visibleItemCount = lm.getChildCount();
                int totalItemCount = lm.getItemCount();
                int firstVisibleItemPosition = lm.findFirstVisibleItemPosition();

                // Load more if we have reach the end to the recyclerView
                if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount && firstVisibleItemPosition >= 0) {
                    MyLog.log("TROUBLE");
                    presenter.getMessages(userId);
                }
            }
        });
    }
}
