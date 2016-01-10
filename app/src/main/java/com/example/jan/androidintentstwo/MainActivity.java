package com.example.jan.androidintentstwo;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.speech.RecognizerIntent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.inputUrl)
    TextView inputUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.openContacts)
    public void openContacts() {
        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        int requestCode = 1;
        if (intent.resolveActivity(getPackageManager()) != null)
            startActivityForResult(intent, requestCode);
        else {
            Toast.makeText(MainActivity.this, R.string.noActivity, Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.openDialer)
    public void openDialer() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        if (intent.resolveActivity(getPackageManager()) != null)
            startActivity(intent);
        else {
            Toast.makeText(MainActivity.this, R.string.noActivity, Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.openVoiceCommand)
    public void openVoiceCommand() {
        int requestCode = 2;
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        if (intent.resolveActivity(getPackageManager()) != null)
            startActivityForResult(intent, requestCode);
        else {
            Toast.makeText(MainActivity.this, R.string.noActivity, Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.openUrl)
    public void openUrl() {
        String url = inputUrl.getText().toString();
        if (!url.isEmpty() && !url.startsWith("http://")) {
            url = "http://" + url;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        if (intent.resolveActivity(getPackageManager()) != null)
            startActivity(intent);
        else {
            Toast.makeText(MainActivity.this, R.string.noActivity, Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.openGoogle)
    public void openGoogle() {
        String tekst = inputUrl.getText().toString();
        Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
        intent.putExtra(SearchManager.QUERY, tekst);
        if (intent.resolveActivity(getPackageManager()) != null)
            startActivity(intent);
        else {
            Toast.makeText(MainActivity.this, R.string.noActivity, Toast.LENGTH_SHORT).show();
        }
    }
}
