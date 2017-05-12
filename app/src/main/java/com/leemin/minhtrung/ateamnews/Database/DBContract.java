package com.leemin.minhtrung.ateamnews.Database;

import android.provider.BaseColumns;

/*Lớp này định nghĩa các hằng số để sử dụng cho việc tạo các bảng trong CSDL...
* nói chung là làm cho nó rõ cmnr code hơn, thay vì bỏ chung trong một class :))
* */
public final class DBContract {

    //Bảng Category
    public static class CategoryTable{

        public static final String TABLE_NAME = "category";
        public static final String COLUMN_ID = "cid";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_ACTIVE = "actice";
        public static final String COLUMN_LINKRSS = "linkrss";

        //Câu lệnh tạo bảng category - kiểu dl tự sửa nghe!
        protected static final String SQL_CREATE_CATEGORY_TABLE = "CREATE TABLE " + CategoryTable.TABLE_NAME
                + " (" + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + CategoryTable.COLUMN_NAME + " VARCHAR(100) NOT NULL,"
                + CategoryTable.COLUMN_ACTIVE + " INTEGER NOT NULL,"
                + CategoryTable.COLUMN_LINKRSS + " VARCHAR(100) NOT NULL)";
        //Câu lệnh xóa luôn cmn bảng category
        protected static final String SQL_DELETE_CATEGORY_TABLE = "DROP TABLE IF EXITS " + CategoryTable.TABLE_NAME;
    }

    //Tương tự với bảng NEWS
    public static class NewsTable{

        public static final String TABLE_NAME = "news";
        public static final String COLUMN_ID = "nid";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_DETAIL = "detail";
        public static final String COLUMN_LINK = "link";
        public static final String COLUMN_DATACREATE = "datacreate";
        public static final String COLUMN_WRITER = "writer";
        public static final String COLUMN_CID = "cid";

        //Câu lệnh tạo bảng news
        protected static final String SQL_CREATE_NEWS_TABLE = "CREATE TABLE " + NewsTable.TABLE_NAME
                + " (" + NewsTable.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + NewsTable.COLUMN_TITLE + " VARCHAR(100) NOT NULL,"
                + NewsTable.COLUMN_DESCRIPTION + " VARCHAR(100) NOT NULL,"
                + NewsTable.COLUMN_DETAIL + " TEXT,"
                + NewsTable.COLUMN_LINK + " VARCHAR(100) NOT NULL,"
                + NewsTable.COLUMN_DATACREATE + " VARCHAR(100) NOT NULL,"
                + NewsTable.COLUMN_CID + " INTEGER NOT NULL,"
                + NewsTable.COLUMN_WRITER + " VARCHAR(100) NOT NULL)";
        //Câu lệnh xóa luôn cmn bảng news
        protected static final String SQL_DELETE_NEWS_TABLE = "DROP TABLE IF EXITS " + NewsTable.TABLE_NAME;
    }

}
