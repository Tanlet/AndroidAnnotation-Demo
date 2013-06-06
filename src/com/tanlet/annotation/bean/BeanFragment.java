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

package com.tanlet.annotation.bean;

import android.support.v4.app.Fragment;

import com.googlecode.androidannotations.annotations.Bean;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EFragment;
import com.tanlet.annotationdemo.R;

/**
 * @author <a href='http://tanlettom.com'>Tanlet</a>
 */
@EFragment(R.layout.bean_fragment)
public class BeanFragment extends Fragment {
	@Bean
	protected CalBean bean;

	@Click(R.id.btAdd)
	protected void calNum() {
		bean.CalResult();
	}
}
