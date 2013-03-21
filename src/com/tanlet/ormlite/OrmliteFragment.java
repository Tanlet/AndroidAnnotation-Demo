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

package com.tanlet.ormlite;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EFragment;
import com.googlecode.androidannotations.annotations.ItemClick;
import com.googlecode.androidannotations.annotations.ItemLongClick;
import com.googlecode.androidannotations.annotations.OrmLiteDao;
import com.googlecode.androidannotations.annotations.ViewById;
import com.j256.ormlite.dao.Dao;
import com.tanlet.annotationdemo.R;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

/**
 * Thanks to <a href='https://github.com/johanpoirier'> Johan Poirier</a>, you
 * may inject your OrmLite DAOs with the <a
 * href='https://github.com/excilys/androidannotations/wiki/Ormlite'>
 * OrmLiteDao</a> annotation. About <a href='http://ormlite.com/'>OrmLite</a>
 * 
 * @author <a href='mailto:tanletwork@gmail.com'>Tanlet</a>
 */
@EFragment(R.layout.ormlite_fragment)
public class OrmliteFragment extends Fragment {
	private final String TAG = OrmliteFragment.class.getName();

	@ViewById
	protected EditText etName;
	@ViewById
	protected EditText etAddress;
	@ViewById(R.id.lv_orm_main)
	protected ListView lvOrmShow;

	@OrmLiteDao(helper = PersonHelp.class, model = PersonBean.class)
	Dao<PersonBean, Integer> personDao;

	ArrayAdapter<PersonBean> ormLiteAdapter;

	@Click(R.id.btAddPersons)
	protected void addPerson() {
		PersonBean addPerson = new PersonBean();
		addPerson.name = this.etName.getText().toString();
		addPerson.address = this.etAddress.getText().toString();

		insertData(addPerson);
	}

	@Click
	protected void btClearPerson() {
		this.etAddress.setText("");
		this.etName.setText("");
	}

	@ItemClick
	protected void lv_orm_main(PersonBean person) {
		Toast.makeText(getActivity(), person.name + "'s Id is " + person.id,
				Toast.LENGTH_SHORT).show();
	}

	@ItemLongClick(R.id.lv_orm_main)
	protected void ormLiteLongClick(PersonBean person) {
		try {
			personDao.delete(person);
			Toast.makeText(getActivity(), person.name + "is delete",
					Toast.LENGTH_SHORT).show();
			showPersonDB();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@AfterViews
	protected void init() {
		this.showPersonDB();
	}

	private void insertData(PersonBean addPerson) {
		try {
			personDao.create(addPerson);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Log.v(TAG, "Fail to create a person。The name can't be null");
			e.printStackTrace();
		}
		showPersonDB();
	}

	private void showPersonDB() {
		try {
			List<PersonBean> allPerson = personDao.queryForAll();
			if (allPerson != null) {
				if (this.ormLiteAdapter == null) {
					this.ormLiteAdapter = new ArrayAdapter<PersonBean>(
							getActivity(), android.R.layout.simple_list_item_1);
					lvOrmShow.setAdapter(ormLiteAdapter);
				}
				this.ormLiteAdapter.clear();
				for (PersonBean personBean : allPerson) {
					this.ormLiteAdapter.add(personBean);
				}
				this.ormLiteAdapter.notifyDataSetChanged();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Log.v(TAG, "Fail to query all person.");

			e.printStackTrace();
		}
	}
}
