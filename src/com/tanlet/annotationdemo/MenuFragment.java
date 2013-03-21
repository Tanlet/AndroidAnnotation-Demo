/*
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tanlet.annotationdemo;

import android.support.v4.app.Fragment;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockFragment;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EFragment;
import com.googlecode.androidannotations.annotations.ItemClick;
import com.googlecode.androidannotations.annotations.ViewById;
import com.tanlet.annotation.bean.BeanFragment;
import com.tanlet.annotation.system.SystemFragment;
import com.tanlet.ormlite.OrmliteFragment;
import com.tanlet.picture.PictureFragment;

/**
 * @author <a href='mailto:tanletwork@gmail.com'>Tanlet</a>
 */
@EFragment(R.layout.menu_fragment)
public class MenuFragment extends SherlockFragment {
	private final String ORM_TITLE = "ORMLite DataBase";
	private final String SYSTEM_TITLE = "Take a picture";
	private final String BEAN_TITLE = "System Serviice";
	private final String PICTURE_TITLE = "Bean Module";
	private final String MAIN_TITLE = "Android Annotation";
	private final String[] menuTitle = new String[] { ORM_TITLE, SYSTEM_TITLE,
			BEAN_TITLE, PICTURE_TITLE, MAIN_TITLE };

	private OrmliteFragment ormFragment;
	private SystemFragment systemFragment;
	private BeanFragment beanFragment;
	private PictureFragment pictureFragment;
	private MainFragment mainFragment;

	@ViewById
	protected ListView lvMenu;

	@AfterViews
	protected void inti() {
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
				getActivity(), android.R.layout.simple_list_item_1, menuTitle);

		this.lvMenu.setAdapter(arrayAdapter);
	}

	@ItemClick
	protected void lvMenu(int position) {
		String result = this.menuTitle[position];
		if (result.endsWith(ORM_TITLE)) {
			if (this.ormFragment == null) {
				this.ormFragment = new OrmliteFragment();
			}
			this.commitMainFragment(ormFragment);
		} else if (result.equals(SYSTEM_TITLE)) {
			if (this.systemFragment == null) {
				this.systemFragment = new SystemFragment();
			}
			this.commitMainFragment(systemFragment);
		} else if (result.equals(BEAN_TITLE)) {
			if (this.beanFragment == null) {
				this.beanFragment = new BeanFragment();
			}
			this.commitMainFragment(beanFragment);
		} else if (result.equals(PICTURE_TITLE)) {
			if (this.pictureFragment == null) {
				this.pictureFragment = new PictureFragment();
			}
			this.commitMainFragment(pictureFragment);
		} else if (result.equals(MAIN_TITLE)) {
			if (this.mainFragment == null) {
				this.mainFragment = new MainFragment();
			}
			this.commitMainFragment(mainFragment);
		}
	}

	private void commitMainFragment(Fragment main) {

		getActivity().getSupportFragmentManager().beginTransaction()
				.replace(R.id.fl_main, main).commit();
	}
}
