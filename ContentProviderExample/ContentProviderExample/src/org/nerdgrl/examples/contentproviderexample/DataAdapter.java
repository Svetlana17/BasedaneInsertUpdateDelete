package org.nerdgrl.examples.contentproviderexample;

import org.nerdgrl.examples.contentproviderexample.provider.ContractClass;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DataAdapter extends CursorAdapter {
	
	private LayoutInflater mInflater;

	public DataAdapter(Context context, Cursor c, int flags) {
		super(context, c, flags);
		mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public void bindView(View view, Context ctx, Cursor cur) {
		long id = cur.getLong(cur.getColumnIndex(ContractClass.Classes._ID));
		String classNumber = cur.getString(cur.getColumnIndex(ContractClass.Classes.COLUMN_NAME_CLASS_NUMBER));
		String classLetter = cur.getString(cur.getColumnIndex(ContractClass.Classes.COLUMN_NAME_CLASS_LETTER));
		ViewHolder holder = (ViewHolder) view.getTag();
		if(holder != null) {
			holder.tvClassName.setText(classNumber+"\""+classLetter+"\"");
			holder.classID = id;
		}
	}

	@Override
	public View newView(Context ctx, Cursor cur, ViewGroup parent) {
		View root = mInflater.inflate(android.R.layout.simple_list_item_1, parent, false);
		ViewHolder holder = new ViewHolder();
		TextView tvClassName = (TextView)root.findViewById(android.R.id.text1);
		holder.tvClassName = tvClassName;
		root.setTag(holder);
		return root;
	}
	
	public static class ViewHolder {
		public TextView tvClassName;
		public long classID;
	}

}
