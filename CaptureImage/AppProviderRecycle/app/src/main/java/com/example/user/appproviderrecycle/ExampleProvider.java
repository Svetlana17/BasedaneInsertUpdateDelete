package com.example.user.appproviderrecycle;

import java.util.HashMap;
import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.util.Log;

public class ExampleProvider extends ContentProvider {
	private static final int DATABASE_VERSION = 1;
	private static HashMap<String, String> sStudentsProjectionMap;
	private static HashMap<String, String> sClassesProjectionMap;
	private static final int STUDENTS = 1;
	private static final int STUDENTS_ID = 2;
	private static final int CLASSES = 3;
	private static final int CLASSES_ID = 4;
	private static final UriMatcher sUriMatcher;
	private DatabaseHelper dbHelper;
	static {
		sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		sUriMatcher.addURI(ContractClass.AUTHORITY, "students", STUDENTS);
		sUriMatcher.addURI(ContractClass.AUTHORITY, "students/#", STUDENTS_ID);
		sUriMatcher.addURI(ContractClass.AUTHORITY, "classes", CLASSES);
		sUriMatcher.addURI(ContractClass.AUTHORITY, "classes/#", CLASSES_ID);
		sStudentsProjectionMap = new HashMap<String, String>();
		for(int i=0; i < ContractClass.Students.DEFAULT_PROJECTION.length; i++) {
			sStudentsProjectionMap.put(
				ContractClass.Students.DEFAULT_PROJECTION[i],
				ContractClass.Students.DEFAULT_PROJECTION[i]);
		}
		sClassesProjectionMap = new HashMap<String, String>();
		for(int i=0; i < ContractClass.Classes.DEFAULT_PROJECTION.length; i++) {
			sClassesProjectionMap.put(
				ContractClass.Classes.DEFAULT_PROJECTION[i],
				ContractClass.Classes.DEFAULT_PROJECTION[i]);
		}
	}
	private static class DatabaseHelper extends SQLiteOpenHelper {
		private static final String DATABASE_NAME = "ContractClassDB";
		public static final String DATABASE_TABLE_STUDENTS = ContractClass.Students.TABLE_NAME;
		public static final String DATABASE_TABLE_CLASSES = ContractClass.Classes.TABLE_NAME;
		public static final String KEY_ROWID  = "_id";
		public static final String KEY_FIRST_NAME   = "first_name";
		public static final String KEY_SECOND_NAME   = "second_name";
		public static final String KEY_CLASS_LETTER   = "class_letter";
		public static final String KEY_FK_CLASS_ID   = "fk_class_id";
		public static final String KEY_AVERAGE_SCORE   = "average_score";
		public static final String KEY_CLASS_NUMBER   = "class_number";
		private static final String DATABASE_CREATE_TABLE_STUDENTS =
			"create table "+ DATABASE_TABLE_STUDENTS + " ("
				+ KEY_ROWID + " integer primary key autoincrement, "
				+ KEY_FIRST_NAME + " string , "
				+ KEY_SECOND_NAME + " string , "
				+ KEY_AVERAGE_SCORE + " real , "
				+ KEY_FK_CLASS_ID + " integer, "
				+" foreign key ("+KEY_FK_CLASS_ID+") references "+DATABASE_TABLE_CLASSES+"("+KEY_ROWID+"));";
				
		private static final String DATABASE_CREATE_TABLE_CLASSES =
			"create table "+ DATABASE_TABLE_CLASSES + " ("
				+ KEY_ROWID + " integer primary key autoincrement, "
				+ KEY_CLASS_NUMBER + " string , "
				+ KEY_CLASS_LETTER + " string );";
				
		private Context ctx;
		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			ctx = context;
		}
		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(DATABASE_CREATE_TABLE_STUDENTS);
			db.execSQL(DATABASE_CREATE_TABLE_CLASSES);
			db.execSQL("insert into classes values (null, '11', 'A');");
			db.execSQL("insert into classes values (null, '11', 'B');");
			db.execSQL("insert into classes values (null, '11', 'C');");
			db.execSQL("insert into classes values (null, '10', 'A');");
			db.execSQL("insert into classes values (null, '10', 'B');");
			db.execSQL("insert into classes values (null, '10', 'C');");
			
			db.execSQL("insert into students values (null, 'Ivan', 'Petrov', 4.1, 0);");
			db.execSQL("insert into students values (null, 'Petr', 'Ivanov', 3.5, 0);");
			db.execSQL("insert into students values (null, 'Ivan', 'Sidorov', 4.9, 0);");
			
			db.execSQL("insert into students values (null, 'Ivan', 'Petrov', 4.1, 1);");
			db.execSQL("insert into students values (null, 'Petr', 'Ivanov', 3.5, 1);");
			db.execSQL("insert into students values (null, 'Ivan', 'Sidorov', 4.9, 1);");
			
			db.execSQL("insert into students values (null, 'Ivan', 'Petrov', 4.1, 2);");
			db.execSQL("insert into students values (null, 'Petr', 'Ivanov', 3.5, 2);");
			db.execSQL("insert into students values (null, 'Ivan', 'Sidorov', 4.9, 2);");
			
			db.execSQL("insert into students values (null, 'Ivan', 'Petrov', 4.1, 3);");
			db.execSQL("insert into students values (null, 'Petr', 'Ivanov', 3.5, 3);");
			db.execSQL("insert into students values (null, 'Ivan', 'Sidorov', 4.9, 3);");
			
			db.execSQL("insert into students values (null, 'Ivan', 'Petrov', 4.1, 4);");
			db.execSQL("insert into students values (null, 'Petr', 'Ivanov', 3.5, 4);");
			db.execSQL("insert into students values (null, 'Ivan', 'Sidorov', 4.9, 4);");
			
			db.execSQL("insert into students values (null, 'Ivan', 'Petrov', 4.1, 5);");
			db.execSQL("insert into students values (null, 'Petr', 'Ivanov', 3.5, 4);");
			db.execSQL("insert into students values (null, 'Ivan', 'Sidorov', 4.9, 5);");
		}
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_STUDENTS);
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_CLASSES);
			onCreate(db);
		}
	}
	@Override
	public int delete(Uri uri, String where, String[] whereArgs) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		String finalWhere;
		int count;
		switch (sUriMatcher.match(uri)) {
			case STUDENTS:
				count = db.delete(ContractClass.Students.TABLE_NAME,where,whereArgs);
				break;
			case STUDENTS_ID:
				finalWhere = ContractClass.Students._ID + " = " + uri.getPathSegments().get(ContractClass.Students.STUDENTS_ID_PATH_POSITION);
				if (where != null) {
					finalWhere = finalWhere + " AND " + where;
				}
				count = db.delete(ContractClass.Students.TABLE_NAME,finalWhere,whereArgs);
				break;
			case CLASSES:
				count = db.delete(ContractClass.Classes.TABLE_NAME,where,whereArgs);
				break;
			case CLASSES_ID:
				finalWhere = ContractClass.Classes._ID + " = " + uri.getPathSegments().get(ContractClass.Classes.CLASSES_ID_PATH_POSITION);
				if (where != null) {
					finalWhere = finalWhere + " AND " + where;
				}
				count = db.delete(ContractClass.Classes.TABLE_NAME,finalWhere,whereArgs);
				break;
		default:
			throw new IllegalArgumentException("Unknown URI " + uri);
		}
		getContext().getContentResolver().notifyChange(uri, null);
		return count;
	}
	@Override
	public String getType(Uri uri) {
		switch (sUriMatcher.match(uri)) {
			case STUDENTS:
				return ContractClass.Students.CONTENT_TYPE;
			case STUDENTS_ID:
				return ContractClass.Students.CONTENT_ITEM_TYPE;
			case CLASSES:
				return ContractClass.Classes.CONTENT_TYPE;
			case CLASSES_ID:
				return ContractClass.Classes.CONTENT_ITEM_TYPE;
			default:
				throw new IllegalArgumentException("Unknown URI " + uri);
		}
	}
	
	@Override
	public Uri insert(Uri uri, ContentValues initialValues) {
		if (
			sUriMatcher.match(uri) != STUDENTS &&
			sUriMatcher.match(uri) != CLASSES
		) {
			throw new IllegalArgumentException("Unknown URI " + uri);
		}
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		ContentValues values;
		if (initialValues != null) {
			values = new ContentValues(initialValues);
		}
		else {
			values = new ContentValues();
		}
		long rowId = -1;
		Uri rowUri = Uri.EMPTY;
		switch (sUriMatcher.match(uri)) {
			case STUDENTS:
				if (values.containsKey(ContractClass.Students.COLUMN_NAME_FIRST_NAME) == false) {
					values.put(ContractClass.Students.COLUMN_NAME_FIRST_NAME, "");
				}
				if (values.containsKey(ContractClass.Students.COLUMN_NAME_SECOND_NAME) == false) {
					values.put(ContractClass.Students.COLUMN_NAME_SECOND_NAME, "");
				}
				if (values.containsKey(ContractClass.Students.COLUMN_NAME_AVERAGE_SCORE) == false) {
					values.put(ContractClass.Students.COLUMN_NAME_AVERAGE_SCORE, 0.0);
				}
				if (values.containsKey(ContractClass.Students.COLUMN_NAME_FK_CLASS_ID) == false) {
					values.put(ContractClass.Students.COLUMN_NAME_FK_CLASS_ID, 0);
				}
			rowId = db.insert(ContractClass.Students.TABLE_NAME,
				ContractClass.Students.COLUMN_NAME_FIRST_NAME,
				values);
			if (rowId > 0) {
				rowUri = ContentUris.withAppendedId(ContractClass.Students.CONTENT_ID_URI_BASE, rowId);
				getContext().getContentResolver().notifyChange(rowUri, null);
			}
			break;
			case CLASSES:
				if (values.containsKey(ContractClass.Classes.COLUMN_NAME_CLASS_NUMBER) == false) {
					values.put(ContractClass.Classes.COLUMN_NAME_CLASS_NUMBER, "");
				}
				if (values.containsKey(ContractClass.Classes.COLUMN_NAME_CLASS_LETTER) == false) {
					values.put(ContractClass.Classes.COLUMN_NAME_CLASS_LETTER, "");
				}
			rowId = db.insert(ContractClass.Classes.TABLE_NAME,
				ContractClass.Classes.COLUMN_NAME_CLASS_NUMBER,
				values);
			if (rowId > 0) {
				rowUri = ContentUris.withAppendedId(ContractClass.Classes.CONTENT_ID_URI_BASE, rowId);
				getContext().getContentResolver().notifyChange(rowUri, null);
			}
			break;
		}
		return rowUri;
	}
	@Override
	public boolean onCreate() {
		dbHelper = new DatabaseHelper(getContext());
		return true;
	}
	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
		String orderBy = null;
		switch (sUriMatcher.match(uri)) {
			case STUDENTS:
				qb.setTables(ContractClass.Students.TABLE_NAME);
				qb.setProjectionMap(sStudentsProjectionMap);
				orderBy = ContractClass.Students.DEFAULT_SORT_ORDER;
				break;
			case STUDENTS_ID:
				qb.setTables(ContractClass.Students.TABLE_NAME);
				qb.setProjectionMap(sStudentsProjectionMap);
				qb.appendWhere(ContractClass.Students._ID + "=" + uri.getPathSegments().get(ContractClass.Students.STUDENTS_ID_PATH_POSITION));
				orderBy = ContractClass.Students.DEFAULT_SORT_ORDER;
				break;
			case CLASSES:
				qb.setTables(ContractClass.Classes.TABLE_NAME);
				qb.setProjectionMap(sClassesProjectionMap);
				orderBy = ContractClass.Classes.DEFAULT_SORT_ORDER;
				break;
			case CLASSES_ID:
				qb.setTables(ContractClass.Classes.TABLE_NAME);
				qb.setProjectionMap(sClassesProjectionMap);
				qb.appendWhere(ContractClass.Classes._ID + "=" + uri.getPathSegments().get(ContractClass.Classes.CLASSES_ID_PATH_POSITION));
				orderBy = ContractClass.Classes.DEFAULT_SORT_ORDER;
				break;
			default:
				throw new IllegalArgumentException("Unknown URI " + uri);
		}
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor c = qb.query(db, projection, selection, selectionArgs, null, null, orderBy);
		c.setNotificationUri(getContext().getContentResolver(), uri);
		return c;
	}
	@Override
	public int update(Uri uri, ContentValues values, String where, String[] whereArgs) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		int count;
		String finalWhere;
		String id;
		switch (sUriMatcher.match(uri)) {
			case STUDENTS:
				count = db.update(ContractClass.Students.TABLE_NAME, values, where, whereArgs);
				break;
			case STUDENTS_ID:
				id = uri.getPathSegments().get(ContractClass.Students.STUDENTS_ID_PATH_POSITION);
				finalWhere = ContractClass.Students._ID + " = " + id;
				if (where !=null) {
					finalWhere = finalWhere + " AND " + where;
				}
				count = db.update(ContractClass.Students.TABLE_NAME, values, finalWhere, whereArgs);
				break;
			case CLASSES:
				count = db.update(ContractClass.Classes.TABLE_NAME, values, where, whereArgs);
				break;
			case CLASSES_ID:
				id = uri.getPathSegments().get(ContractClass.Classes.CLASSES_ID_PATH_POSITION);
				finalWhere = ContractClass.Classes._ID + " = " + id;
				if (where !=null) {
					finalWhere = finalWhere + " AND " + where;
				}
				count = db.update(ContractClass.Classes.TABLE_NAME, values, finalWhere, whereArgs);
				break;
			default:
				throw new IllegalArgumentException("Unknown URI " + uri);
		}
		getContext().getContentResolver().notifyChange(uri, null);
		return count;
	}
}
