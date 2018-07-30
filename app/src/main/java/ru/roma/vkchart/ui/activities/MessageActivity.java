package ru.roma.vkchart.ui.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import ru.roma.vkchart.R;
import ru.roma.vkchart.ui.fragment.MessageFragment;
import ru.roma.vkchart.utils.MyLog;

public class MessageActivity extends AppCompatActivity implements MessageFragment.OnMessageFragmentListener{

    public static final String USER_NAME = "userName";
    public static final String PHOTO = "photo";
    public static final String ONLINE = "online";
    public static final String USER_ID = "userId";
    private static final String FRAGMENT_MESSAGE = "fragmentMessage";
    private static final String ACTION_MODE = "actionMode";
    private static final String Count_Items = "countItems";
    private MessageFragment fragment;
    private ActionMode actionCallback;
    private boolean isInActionMode = false;
    private int countSelectItems = 0;
    private int online;
    private String userName;
    private String photo;
    @BindView(R.id.photo_user)
    CircleImageView photoUser;
    @BindView(R.id.user_name)
    TextView name;
    @BindView(R.id.user_online)
    TextView userOnline;
    @BindView(R.id.message_tool_bar)
    Toolbar messageToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        ButterKnife.bind(this);

        Bundle user = getIntent().getExtras();
        userName = user.getString(USER_NAME);
        online = user.getInt(ONLINE);
        photo = user.getString(PHOTO);
        initializeToolBar();
        MyLog.log(String.valueOf(savedInstanceState == null));
        if (savedInstanceState!= null && savedInstanceState.getBoolean(ACTION_MODE)){
            isInActionMode = true;
            countSelectItems = savedInstanceState.getInt(Count_Items);
            showActionMode();
        }
        loadFragment(user);
    }

    private void loadFragment(Bundle user) {
        fragment = (MessageFragment) getSupportFragmentManager().findFragmentByTag(FRAGMENT_MESSAGE);
        if (fragment == null) {
            fragment = MessageFragment.newInstance(user);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.frame_messages, fragment, FRAGMENT_MESSAGE);
            ft.commit();
        }
    }

    private void initializeToolBar() {
        name.setText(userName);

        Glide
                .with(this)
                .load(photo)
                .into(photoUser);
        photoUser.setVisibility(View.VISIBLE);

        if (online == 1) {
            userOnline.setVisibility(View.VISIBLE);
        }
        messageToolBar.setContentInsetStartWithNavigation(0);
        setSupportActionBar(messageToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        actionCallback = null;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(ACTION_MODE,isInActionMode);
        outState.putInt(Count_Items,countSelectItems);
    }

    @Override
    public void startActionMode() {
        if (!isInActionMode){
            isInActionMode = true;
            showActionMode();
        }
    }

    @Override
    public void stopActionMode() {
        if (isInActionMode) {
            actionCallback.finish();
        }
    }

    @Override
    public void updateCountActionMode(int count) {
        if (isInActionMode) {
            actionCallback.setTitle(String.valueOf(count));
            countSelectItems = count;
        }
    }

    private void showActionMode() {
        MyLog.log("show Action Mode");
        actionCallback = startSupportActionMode(new ActionCallback());
        actionCallback.setTitle(String.valueOf(countSelectItems));
    }

    private class ActionCallback implements  ActionMode.Callback{

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.message_menu,menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()){
                case R.id.remove:
                    Toast.makeText(MessageActivity.this,"remove",Toast.LENGTH_LONG).show();
                    return true;
                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            actionCallback = null;
            isInActionMode = false;
            fragment.cancelActionMode();
            MyLog.log("onDestroyActionMode");
        }
    }
}
