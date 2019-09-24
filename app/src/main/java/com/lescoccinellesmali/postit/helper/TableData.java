package com.lescoccinellesmali.postit.helper;
import android.provider.BaseColumns;
public class TableData {
	public TableData(){
	}
	public static abstract class TableInfo implements BaseColumns{
		public static final String USER_NAME = "user_name";
		public static final String USER_PASS = "user_pass";
		public static final String DATABASE_NAME = "Post_db";
		public static final String USER_TABLE_NAME = "user_table";
		public static final String POST_TABLE_NAME = "post_table";
		public static final String POST_TITLE = "post_title";
		public static final String POST_AUTHOR = "post_author";
		public static final String POST_DATE = "post_date";
		public static final String POST_LOCATION = "post_location";
		public static final String POST_DESCRIPTION = "post_description";
		public static final String POST_TYPE = "post_type";
		public static final String POST_EVENT = "post_event";
	}
}