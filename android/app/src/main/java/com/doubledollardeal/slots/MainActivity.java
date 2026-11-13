package com.doubledollardeal.slots;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import java.util.Locale;

/** Shows the game (index.html) full-screen and gives it a voice. */
public class MainActivity extends Activity {
    private WebView web;
    private TextToSpeech tts;
    private boolean ttsReady = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        web = new WebView(this);
        web.setBackgroundColor(Color.parseColor("#07060b"));
        WebSettings s = web.getSettings();
        s.setJavaScriptEnabled(true);
        s.setDomStorageEnabled(true);                 // saves balance and settings
        s.setMediaPlaybackRequiresUserGesture(false); // sounds and music

        // Android's in-app browser has no built-in speech, so the announcer
        // voice uses the phone's text-to-speech engine through this bridge.
        tts = new TextToSpeech(this, status -> {
            if (status == TextToSpeech.SUCCESS) {
                tts.setLanguage(Locale.US);
                tts.setPitch(0.85f);
                tts.setSpeechRate(1.05f);
                ttsReady = true;
            }
        });
        web.addJavascriptInterface(new Voice(), "AndroidVoice");

        setContentView(web);
        web.loadUrl("file:///android_asset/index.html");
        hideSystemBars();
    }

    /** Called from the game's JavaScript. */
    private class Voice {
        @JavascriptInterface
        public void speak(String text) {
            if (ttsReady) tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, "ddd");
        }

        @JavascriptInterface
        public void stop() {
            if (ttsReady) tts.stop();
        }
    }

    @SuppressWarnings("deprecation")
    private void hideSystemBars() {
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) hideSystemBars();
    }

    @Override
    protected void onPause() {
        super.onPause();
        web.evaluateJavascript("window.onAndroidPause && window.onAndroidPause()", null);
        web.onPause();
        web.pauseTimers();   // no music or auto spin while the app is in the background
        if (ttsReady) tts.stop();
    }

    @Override
    protected void onResume() { super.onResume(); web.onResume(); web.resumeTimers(); }

    @Override
    protected void onDestroy() {
        if (tts != null) tts.shutdown();
        super.onDestroy();
    }

    /** Back button: close the settings panel if it is open; otherwise leave the app. */
    @SuppressWarnings("deprecation")
    @Override
    public void onBackPressed() {
        web.evaluateJavascript("window.onAndroidBack ? window.onAndroidBack() : false", result -> {
            if (!"true".equals(result)) finish();
        });
    }
}
