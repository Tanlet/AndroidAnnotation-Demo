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

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * @author <a href='http://tanlettom.com'>Tanlet</a>
 */
@DatabaseTable(tableName = "_persontable")
public class PersonBean {
	@DatabaseField(generatedId = true)
	public int id;
	@DatabaseField
	public String name;
	@DatabaseField
	public String address;

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuffer introduce = new StringBuffer();
		introduce.append(name + "  ").append(address);
		return introduce.toString();
	}

}
