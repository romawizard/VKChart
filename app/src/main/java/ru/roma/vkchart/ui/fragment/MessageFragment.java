package ru.roma.vkchart.ui.fragment;


import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import ru.roma.vkchart.R;
import ru.roma.vkchart.domain.entities.Message;
import ru.roma.vkchart.ui.MyApplication;
import ru.roma.vkchart.ui.adapters.MessageAdapter;
import ru.roma.vkchart.ui.presenter.MessagePresenter;
import ru.roma.vkchart.ui.ui_item.MessageUIItem;
import ru.roma.vkchart.utils.MyLog;


public class MessageFragment extends Fragment implements MessagePresenter.MessageView {

    public static final String USER_NAME = "userName";
    public static final String PHOTO = "photo";
    public static final String ONLINE = "online";
    public static final String USER_ID = "userId";
    private int userId;
    private int online;
    private String userName;
    private String photo;
    private MessageAdapter adapter;
    @Inject
    MessagePresenter presenter;
    private Parcelable recycleViewState;
    private OnMessageFragmentListener listener;
    private List<MessageUIItem> listSelectMessage = new ArrayList<>();
    private boolean actionMode = false;
    @BindView(R.id.text_msg)
    AppCompatEditText textMsg;
    @BindView(R.id.list_messages)
    RecyclerView listMessages;
    Unbinder unbinder;

    public static MessageFragment newInstance(Bundle user) {
        MessageFragment fragment = new MessageFragment();
        fragment.setArguments(user);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MyLog.log("onCreate() MessageFragment");

        MyApplication.getInstance().getAppComponent().inject(this);

        setRetainInstance(true);

        if (getArguments() != null) {
            Bundle arg = getArguments();
            MyLog.log("bundle in fragment = " + arg.toString());
            userId = arg.getInt(USER_ID);
            userName = arg.getString(USER_NAME);
            photo = arg.getString(PHOTO);
            online = arg.getInt(ONLINE);
        }
        adapter = new MessageAdapter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, container, false);
        unbinder = ButterKnife.bind(this, view);

        presenter.setView(this);

        initializeRecycleList(listMessages);
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
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnMessageFragmentListener) {
            listener = (OnMessageFragmentListener) context;
        }else {
            throw  new IllegalArgumentException(context.toString()
                    + "must implement OnMessageFragmentListener");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        recycleViewState = listMessages.getLayoutManager().onSaveInstanceState();
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detach();
    }

    @Override
    public void updateList(List<MessageUIItem> list) {
        adapter.setListMessages(list);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void update() {
        adapter.notifyDataSetChanged();
    }

    private void initializeRecycleList(RecyclerView list) {
        GestureDetectorCompat detector = new GestureDetectorCompat(getContext(),new GestureListener());
        list.addOnItemTouchListener(new RecyclerTouchListener(detector));
        final LinearLayoutManager lm = new LinearLayoutManager(getContext());
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        lm.setReverseLayout(true);


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

    @OnClick(R.id.send)
    public void onSend() {
        MessageUIItem message = new MessageUIItem(textMsg.getText().toString(),userId);
        presenter.sendMessage(message);
        adapter.addMessage(message);
        textMsg.setText("");
    }

    public void cancelActionMode() {
        actionMode = false;
        annulmentCheckedView();
        listSelectMessage.clear();
       }

    private void annulmentCheckedView() {
        Iterator<MessageUIItem> iterator = listSelectMessage.iterator();
        while (iterator.hasNext()){
            MessageUIItem  item = iterator.next();
            item.setCheck(false);
        }
        adapter.notifyDataSetChanged();
    }

    private void addViewToSelectedList(View child){
        int position = listMessages.getChildAdapterPosition(child);
        MessageUIItem message = adapter.getMessage(position);
        if (!listSelectMessage.contains(message)){
            listSelectMessage.add(message);
            message.setCheck(true);
            adapter.notifyDataSetChanged();
        }else {
            Iterator<MessageUIItem> iterator = listSelectMessage.iterator();
            while (iterator.hasNext()){
                Message m = iterator.next();
                if (m == message){
                    message.setCheck(false);
                    adapter.notifyDataSetChanged();
                    iterator.remove();
                }
                break;
            }
        }
        if (listSelectMessage.size() <1){
            actionMode = false;
            listener.stopActionMode();
        }
    }


    private class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetectorCompat gestureDetector;

        public RecyclerTouchListener(GestureDetectorCompat gestureDetector) {
            this.gestureDetector = gestureDetector;
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            gestureDetector.onTouchEvent(e);
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }


    private class GestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            View child = listMessages.findChildViewUnder(e.getX(),e.getY());
            if (actionMode){
                addViewToSelectedList(child);
                listener.updateCountActionMode(listSelectMessage.size());
            }
            return super.onSingleTapConfirmed(e);
        }

        @Override
        public void onLongPress(MotionEvent e) {
            View child = listMessages.findChildViewUnder(e.getX(),e.getY());
            if (!actionMode){
                actionMode = true;
                addViewToSelectedList(child);
                listener.startActionMode();
                listener.updateCountActionMode(1);
            }
            super.onLongPress(e);
        }
    }


    public interface OnMessageFragmentListener{

        void startActionMode();

        void stopActionMode();

        void updateCountActionMode(int count);

    }


}
