package com.leoric.BBGame.android;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.leoric.brickbox.BBGame;
import com.leoric.brickbox.IGoogleServices;

public class AndroidLauncher extends AndroidApplication implements IGoogleServices {
	
	private static final String AD_UNIT_ID = "ca-app-pub-9070793621828141/1354264117";
	
	protected AdView adView;
	
	Handler uiThread;
    Context appContext;
	
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		
		RelativeLayout layout = new RelativeLayout(this);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
		
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		
		RelativeLayout.LayoutParams gameViewParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		gameViewParams.topMargin = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, 
				getResources().getDisplayMetrics());
		
		View gameView = initializeForView(new BBGame(this), config);
		
		layout.addView(gameView, gameViewParams);
		
		adView = new AdView(this);
		adView.setAdUnitId(AD_UNIT_ID);
	    adView.setAdSize(AdSize.SMART_BANNER);
		adView.setId(12345);
		adView.setBackgroundColor(Color.BLACK);

	    RelativeLayout.LayoutParams adParams = 
	        new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, 
	                RelativeLayout.LayoutParams.WRAP_CONTENT);
	    adParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
	    adParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);

	    layout.addView(adView, adParams);

	    setContentView(layout);		
	    
	    AdRequest adRequest = new AdRequest.Builder().build();
	    adView.loadAd(adRequest);	 	
	}
	
	@Override
	  public void onResume() {
	    super.onResume();
	    if (adView != null) adView.resume();
	}

	@Override
	 public void onPause() {
	   if (adView != null) adView.pause();
	   super.onPause();
	}

	@Override
	public void onDestroy() {
		if (adView != null) adView.destroy();
	    super.onDestroy();
	}

    @Override
    public void rateApp() {
	    Uri uri = Uri.parse("market://details?id=" + this.getPackageName());
	    Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
	    // To count with Play market backstack, After pressing back button, 
	    // to taken back to our application, we need to add following flags to intent. 
	    goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
	    try {
	        startActivity(goToMarket);
	    } catch (ActivityNotFoundException e) {
	        startActivity(new Intent(Intent.ACTION_VIEW,
	                Uri.parse("http://play.google.com/store/apps/details?id=" + this.getPackageName())));
	    }
    }
}
