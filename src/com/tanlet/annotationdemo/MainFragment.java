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

import android.net.http.SslError;
import android.support.v4.app.Fragment;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EFragment;
import com.googlecode.androidannotations.annotations.ViewById;

/**
 * @author <a href='mailto:tanletwork@gmail.com'>Tanlet</a>
 */
@EFragment(R.layout.main_fragment)
public class MainFragment extends Fragment {
	@ViewById
	protected WebView wv_annotation;
	public static String URL_TANLET_BLOG = "http://tanlettom.com";
	public static String URL_GITHUB_ANNOTATION = "https://github.com/excilys/androidannotations";

	@AfterViews
	protected void init() {
		loadUrl(URL_TANLET_BLOG);

		// this.wv_annotation.getSettings().set
		this.wv_annotation.getSettings().setJavaScriptEnabled(true);
		this.wv_annotation.getSettings().setDefaultTextEncodingName("gb2312");

	}

	public void loadUrl(String url) {
		this.wv_annotation.loadUrl(url);
	}
}
