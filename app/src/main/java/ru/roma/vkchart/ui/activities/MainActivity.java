package ru.roma.vkchart.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import ru.roma.vkchart.R;
import ru.roma.vkchart.ui.fragment.DialogsFragment;
import ru.roma.vkchart.ui.fragment.MessageFragment;
import ru.roma.vkchart.utils.MyLog;

public class MainActivity extends AppCompatActivity implements DialogsFragment.OnFragmentInteractionListener {

    private static final String FRAGMENT_DIALOG = "fragmentDialogs";
    private static final String FRAGMENT_MESSAGE = "fragmentMessage";
    private DialogsFragment dialogFragment;
    private MessageFragment messageFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        loadFragment();
    }

    private void loadFragment() {
        FragmentTransaction fTran = getSupportFragmentManager().beginTransaction();
        dialogFragment = (DialogsFragment) getSupportFragmentManager().findFragmentByTag(FRAGMENT_DIALOG);
        if (dialogFragment == null) {
            MyLog.log("null in DialogsFragment");
            dialogFragment = new DialogsFragment();
            fTran.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            fTran.add(R.id.frame_layout, dialogFragment, FRAGMENT_DIALOG).commit();
        }
    }

    @Override
    public void onFragmentInteraction(Bundle user) {
        Intent intent = new Intent(this,MessageActivity.class);
        intent.putExtras(user);
        startActivity(intent);
    }
}
