package ca.surveillancerights.surveillancewatch;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;

public class WelcomeActivity extends WebViewActivity {

	static final int SET_PREFERENCES = 0;

	WebView welcomeHtml;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.welcome);
		
		//((TextView) findViewById(R.id.WelcomeText)).setMovementMethod(new ScrollingMovementMethod());
		welcomeHtml = (WebView) findViewById(R.id.welcome_html);
		
		setupMainWebView();
		
		welcomeHtml.setBackgroundColor(0x00000000);
		welcomeHtml.loadUrl("file:///android_asset/www/welcome.html");
	}

	public void onShowMapClick(View view) {
		OptionsMenu.loadMap(this);
	}

	public void onViewListClick(View view) {
		OptionsMenu.loadListOfInstallations(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return OptionsMenu.create(this, menu);
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		return OptionsMenu.selectItem(this, featureId, item);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// Check if the key event was the Back button and if there's history
	    if ((keyCode == KeyEvent.KEYCODE_BACK) && welcomeHtml.canGoBack()) {
	    	welcomeHtml.goBack();
	        return true;
	    }
	    // If it wasn't the Back key or there's no web page history, bubble up to the default
	    // system behavior (probably exit the activity)
	    return super.onKeyDown(keyCode, event);
	}
	

	@Override
	protected void onActivityResult(int requestCode, int resultCode,
			Intent intent) {
		if (requestCode == SET_PREFERENCES) {
			if (resultCode == RESULT_OK) {
				super.onActivityResult(requestCode, resultCode, intent);
				//this.loadUrl(this.getVeosUrl()+"/app.html#/overview-map.html");
			}
		} else {
			super.onActivityResult(requestCode, resultCode, intent);
		}
	}
	
	public WebView getMainWebView() {
		return welcomeHtml;
	}
	
}
