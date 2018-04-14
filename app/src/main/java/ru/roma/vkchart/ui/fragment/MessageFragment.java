package ru.roma.vkchart.ui.fragment;


import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import ru.roma.vkchart.R;
import ru.roma.vkchart.domain.entities.Message;
import ru.roma.vkchart.ui.adapters.MessageAdapter;
import ru.roma.vkchart.ui.presenter.MessagePresenter;
import ru.roma.vkchart.utils.MyLog;


public class MessageFragment extends Fragment implements ru.roma.vkchart.ui.fragment.View<Message> {

    public static final String USER_NAME = "userName";
    public static final String PHOTO = "photo";
    public static final String ONLINE = "online";
    public static final String USER_ID = "userId";
    @BindView(R.id.text_msg)
    AppCompatEditText textMsg;
    private int userId;
    private String userName;
    private String photo;
    private int online;
    private MessageAdapter adapter;
    private MessagePresenter presenter;
    private Parcelable recycleViewState;
    @BindView(R.id.list_messages)
    RecyclerView listMessages;
    Unbinder unbinder;
    @BindView(R.id.photo_user)
    CircleImageView photoUser;
    @BindView(R.id.user_name)
    TextView TVuserName;
    @BindView(R.id.user_online)
    TextView userOnline;
    @BindView(R.id.back_msg)
    Button backMsg;


    public MessageFragment() {

    }

    public static MessageFragment newInstance(Bundle user) {
        MessageFragment fragment = new MessageFragment();
        fragment.setArguments(user);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        MyLog.log("onCreate() MessageFragment");

        setRetainInstance(true);

        if (getArguments() != null) {
            Bundle arg = getArguments();
            userId = arg.getInt(USER_ID);
            userName = arg.getString(USER_NAME);
            photo = arg.getString(PHOTO);
            online = arg.getInt(ONLINE);
        }
        presenter = new MessagePresenter(this);
        adapter = new MessageAdapter();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, container, false);
        unbinder = ButterKnife.bind(this, view);

        intializateToolBar();
        initilializeRecycleList(listMessages);
        if (recycleViewState != null) {
            listMessages.getLayoutManager().onRestoreInstanceState(recycleViewState);
        }
        return view;
    }

    private void intializateToolBar() {
        TVuserName.setText(userName);

        Glide
                .with(getActivity())
                .load(photo)
                .into(photoUser);
        photoUser.setVisibility(View.VISIBLE);

        if (online == 1) {
            userOnline.setVisibility(View.VISIBLE);
        }
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
    public void update() {
        adapter.adtate();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        recycleViewState = listMessages.getLayoutManager().onSaveInstanceState();
        unbinder.unbind();
    }

    private void initilializeRecycleList(RecyclerView list) {
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

    @OnClick(R.id.back_msg)
    public void OnClick() {
        getActivity().onBackPressed();
    }

    @OnClick(R.id.send)
    public void onSend() {
        Message message = new Message(textMsg.getText().toString(),userId);
        presenter.sendMessage(message);
        adapter.addMessage(message);
        textMsg.setText("");
    }
}
