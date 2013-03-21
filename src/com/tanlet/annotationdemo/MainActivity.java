package com.tanlet.annotationdemo;

import android.support.v4.app.Fragment;
import android.widget.FrameLayout;

import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;
import com.slidingmenu.lib.SlidingMenu;
import com.slidingmenu.lib.app.SlidingFragmentActivity;
import com.tanlet.annotation.bean.BeanFragment;
import com.tanlet.annotation.system.SystemFragment;
import com.tanlet.ormlite.OrmliteFragment;
import com.tanlet.picture.PictureFragment;

@EActivity(R.layout.activity_main)
public class MainActivity extends SlidingFragmentActivity {

	public static final int SLIDE_LAYOUT_ID = 10086;
	MainFragment mainFragment;

	SlidingMenu slideMenu;

	@AfterViews
	public void initAfterInject() {
		getSupportActionBar().setHomeButtonEnabled(true);
		FrameLayout slideLayout = new FrameLayout(getApplicationContext());
		slideLayout.setId(SLIDE_LAYOUT_ID);
		setBehindContentView(slideLayout);
		MenuFragment menuFragment = new MenuFragment();
		getSupportFragmentManager().beginTransaction()
				.replace(SLIDE_LAYOUT_ID, menuFragment).commit();

		slideMenu = new SlidingMenu(getApplicationContext());
		slideMenu.setShadowWidthRes(R.dimen.shadow_width);
		slideMenu.setShadowDrawable(R.drawable.shadow);
		slideMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		slideMenu.setFadeDegree(0.35f);
		slideMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		// slideMenu.attachToActivity(this, SlidingMenu.LEFT);

		this.initMain();
	}

	@Click(android.R.id.home)
	public void actionBarClick() {
		toggle();
	}

	private void initMain() {
		if (this.mainFragment == null) {
			this.mainFragment = MainFragment_.builder().build();
		}
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.fl_main, mainFragment).commit();
	}

}
