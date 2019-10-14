package com.app.javatraining.mvpdagger.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.app.javatraining.mvpdagger.R;
import com.app.javatraining.mvpdagger.lobby.LobbyGreetingContract;
import com.app.javatraining.mvpdagger.lobby.LobbyPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import timber.log.Timber;

public class LobbyActivity extends AppCompatActivity implements LobbyGreetingContract.LobbyView {
    private static final String KEY_GREETING = "greeting";
    @Inject
    LobbyPresenter presenter;
    @BindView(R.id.greeting_textview)
    TextView greetingTextView;
    @BindView(R.id.loading_indicator)
    ProgressBar loadingIndicator;
    boolean done = false;
    Handler handler;
    Runnable runnable;
    @Override
    @OnClick(R.id.common_greeting_button)
    public void onCommonGreatingClicked() {
        greetingTextView.setText("");
        setLoadingIndicator(true);
        if(handler != null){
            handler.removeCallbacks(runnable);
            runnable = null;
            handler = null;
        }
        handler = new Handler();
        runnable = () -> presenter.loadCommonGreeting();
        handler.postDelayed(runnable,5000);

    }
    @Override
    @OnClick(R.id.lobby_greeting_button)
    public void onLobbyGreetingButtonClicked() {
        greetingTextView.setText("");
        setLoadingIndicator(true);
        if(handler != null){
            handler.removeCallbacks(runnable);
            runnable = null;
            handler = null;
        }
        handler = new Handler();
        runnable = () -> presenter.loadLobbyGreeting();
        (new Handler()).postDelayed(runnable,5000);

    }

    @Override
    public void displayGreeting(String greeting) {
        greetingTextView.setVisibility(View.VISIBLE);
        greetingTextView.setText(greeting);
    }

    @Override
    public void displayGreetingError(Throwable throwable) {
        Timber.e(throwable.getMessage());
        Toast.makeText(this,"something wrong",Toast.LENGTH_LONG).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        loadingIndicator.setVisibility((active ? View.VISIBLE:View.GONE));
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        if (!TextUtils.isEmpty(greetingTextView.getText())) {
            outState.putCharSequence(KEY_GREETING,greetingTextView.getText());
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        greetingTextView.setText(savedInstanceState.getCharSequence(KEY_GREETING));
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.stop();
    }
}
