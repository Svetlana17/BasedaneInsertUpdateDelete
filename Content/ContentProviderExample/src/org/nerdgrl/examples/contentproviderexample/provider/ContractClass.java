package org.nerdgrl.examples.contentproviderexample.provider;

import android.net.Uri;
import android.provider.BaseColumns;

public final class ContractClass {
	public static final String AUTHORITY = "org.nerdgrl.examples.contentproviderexample.provider.ContractClass";
	private ContractClass() {}
	public static final class Students implements BaseColumns {
		private Students() {}
		public static final String TABLE_NAME ="students";
		private static final String SCHEME = "content://";
		private static final String PATH_STUDENTS = "/students";
		private static final String PATH_STUDENTS_ID = "/students/";
		public static final int STUDENTS_ID_PATH_POSITION = 1;
		public static final Uri CONTENT_URI =  Uri.parse(SCHEME + AUTHORITY + PATH_STUDENTS);
		public static final Uri CONTENT_ID_URI_BASE = Uri.parse(SCHEME + AUTHORITY + PATH_STUDENTS_ID);
		//public static final Uri CONTENT_ID_URI_PATTERN = Uri.parse(SCHEME + AUTHORITY + PATH_STUDENTS_ID + "/#");
		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.org.nerdgrl.students";
		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.org.nerdgrl.students";
		public static final String DEFAULT_SORT_ORDER = "second_name ASC";
		public static final String COLUMN_NAME_FIRST_NAME   = "first_name";
		public static final String COLUMN_NAME_SECOND_NAME   = "second_name";
		public static final String COLUMN_NAME_AVERAGE_SCORE   = "average_score";
		public static final String COLUMN_NAME_FK_CLASS_ID   = "fk_class_id";
		public static final String[] DEFAULT_PROJECTION = new String[] {
			ContractClass.Students._ID,
			ContractClass.Students.COLUMN_NAME_FIRST_NAME,
			ContractClass.Students.COLUMN_NAME_SECOND_NAME,
			ContractClass.Students.COLUMN_NAME_AVERAGE_SCORE,
			ContractClass.Students.COLUMN_NAME_FK_CLASS_ID
		};
	}
	public static final class Classes implements BaseColumns {
		private Classes() {}
		public static final String TABLE_NAME ="classes";
		private static final String SCHEME = "content://";
		private static final String PATH_CLASSES = "/classes";
		private static final String PATH_CLASSES_ID = "/classes/";
		public static final int CLASSES_ID_PATH_POSITION = 1;
		public static final Uri CONTENT_URI =  Uri.parse(SCHEME + AUTHORITY + PATH_CLASSES);
		public static final Uri CONTENT_ID_URI_BASE = Uri.parse(SCHEME + AUTHORITY + PATH_CLASSES_ID);
		//public static final Uri CONTENT_ID_URI_PATTERN = Uri.parse(SCHEME + AUTHORITY + PATH_CLASSES_ID + "/#");
		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.org.nerdgrl.classes";
		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.org.nerdgrl.classes";
		public static final String DEFAULT_SORT_ORDER = "class_number ASC";
		public static final String COLUMN_NAME_CLASS_NUMBER   = "class_number";
		public static final String COLUMN_NAME_CLASS_LETTER   = "class_letter";
		public static final String[] DEFAULT_PROJECTION = new String[] {
			ContractClass.Classes._ID,
			ContractClass.Classes.COLUMN_NAME_CLASS_NUMBER,
			ContractClass.Classes.COLUMN_NAME_CLASS_LETTER
		};
	}
}

