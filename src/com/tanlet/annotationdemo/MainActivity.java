package com.tanlet.annotationdemo;

import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.OptionsItem;

@EActivity(R.layout.activity_main)
public class MainActivity extends SherlockFragmentActivity {
	public static final String FRAGMENT_TAG = "TanletTom";

	public static final int SLIDE_LAYOUT_ID = 10086;
	MenuFragment menuFragment;

	@AfterViews
	public void initAfterInject() {
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		this.initMain();
	}

	@OptionsItem(android.R.id.home)
	public void actionBarClick() {
		Toast.makeText(getApplicationContext(), "Show Time", 1000).show();
		// switchMenu();
		getSupportFragmentManager().beginTransaction().remove(menuFragment)
				.commit();
	}

	private void initMain() {
		if (this.menuFragment == null) {
			this.menuFragment = MenuFragment_.builder().build();
		}
		switchMenu();
	}

	private void switchMenu() {
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.fl_main, menuFragment)
				.addToBackStack(FRAGMENT_TAG)
				.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
				.commit();
	}

}
