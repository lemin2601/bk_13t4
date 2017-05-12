package com.leemin.minhtrung.ateamnews.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import com.leemin.minhtrung.ateamnews.bean.Category;
import com.leemin.minhtrung.ateamnews.bean.News;

import java.util.ArrayList;

/**
 * Created by Tu Doan Le on 5/8/2017.
 */
// Lớp này dùng để cho quản lý CSDL trong app
public class DBHelper extends SQLiteOpenHelper {
    public static final int DB_VERSION = 1;
    public static final String NAME_DATABASE = "MyDatabase.db"; // trong máy android sẽ có file này sau khi chạy app lần đầu tiên

    public DBHelper(Context context) {
        super(context, NAME_DATABASE, null, DB_VERSION);
    }

    //Tạo bảng cho CSDL
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Cái thèn db này dùng để thực thi các câu lệnh sql.
        //Tạo 2 bảng category, với news trong MyDatabase.db
        db.execSQL(DBContract.CategoryTable.SQL_CREATE_CATEGORY_TABLE);
        db.execSQL(DBContract.NewsTable.SQL_CREATE_NEWS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Khi đổi version thì bỏ Bảng cũ, thay bảng mới
        db.execSQL(DBContract.CategoryTable.SQL_DELETE_CATEGORY_TABLE);
        db.execSQL(DBContract.NewsTable.SQL_DELETE_NEWS_TABLE);
        //tạo lại bảng mới
        onCreate(db);
    }

    // OK! Giờ tạo các method cần dùng cho việc tương tác vs CSDL
    /*Giờ ta tạo các phương thức XÓA ALL -> THÊM ALL -> GET ALL DỮ LIỆU cho 2 bảng*/

    //Xóa all dl bảng category
    private boolean deleteAllCategory() {
        //Mỗi lần muốn làm gì vs CSDL luôn luôn có thèn db này :))
        SQLiteDatabase db = this.getWritableDatabase(); // muốn viết vào csdl thỳ dùng write, ngược lai dùng read;

        // db.delete(String tableName, String whereClause, String[] whereArgs);
        // If whereClause is null, it will delete all rows.
        // Trả về số hàng đã xóa
        int i = db.delete(DBContract.CategoryTable.TABLE_NAME, null, null);
        db.close(); //giải phóng bộ nhớ
        return (i > 0);
    }

    //Thêm dl mới
    private void insertAllCategory(ArrayList<Category> list) {
        SQLiteDatabase db = this.getWritableDatabase();
        for (Category category : list) {
            if (category == null) {
                continue;
            }
            ContentValues cv = new ContentValues();
            cv.put(DBContract.CategoryTable.COLUMN_ID, category.getCid());
            cv.put(DBContract.CategoryTable.COLUMN_NAME, category.getName());
            cv.put(DBContract.CategoryTable.COLUMN_ACTIVE, category.getActive());
            cv.put(DBContract.CategoryTable.COLUMN_LINKRSS, category.getLinkrss());
            long newRow = db.insert(DBContract.CategoryTable.TABLE_NAME, null, cv);
            if (newRow == -1) {
                Log.e("TAG1", category.getCid() + "lỗi khi chèn!");
            }
        }
        db.close();
    }

    //Lấy dữ liệu
    public ArrayList<Category> getListCat(ArrayList<Category> inList) {
        //Xóa dl cũ
        if (this.deleteAllCategory()) {
            //Chèn dl mới
            this.insertAllCategory(inList);
        } else {
            Log.e("TAG1", "Lỗi khi xóa dl");
            return null;
        }

        //get dl mới
        ArrayList<Category> outList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        //Tạo con trỏ tới bảng sau khi truy vấn
        Cursor cs = db.rawQuery("SELECT * FROM " + DBContract.CategoryTable.TABLE_NAME, null);
        while (cs.moveToNext()) {
            int id = cs.getInt(0); // hoac co the dung: cs.getInt(cs.getColumnIndex(DBContract.CategoryTable.COLUMN_ID))
            String name = cs.getString(1);
            int active = cs.getInt(2);
            String linkrss = cs.getString(3);

            Category category = new Category(id, name, active, linkrss);
            outList.add(category);
        }
        cs.close();
        db.close();
        return outList;
    }

    //Tương tự với News
    //Xóa all dl bảng news
    private boolean deleteAllNews() {
        //Mỗi lần muốn làm gì vs CSDL luôn luôn có thèn db này :))
        SQLiteDatabase db = this.getWritableDatabase(); // muốn viết vào csdl thỳ dùng write, ngược lai dùng read;

        // db.delete(String tableName, String whereClause, String[] whereArgs);
        // If whereClause is null, it will delete all rows.
        // Trả về số hàng đã xóa
        int i = db.delete(DBContract.NewsTable.TABLE_NAME, null, null);
        db.close(); //giải phóng bộ nhớ
        return (i > 0);
    }

    //Thêm dl mới
    private void insertAllNews(ArrayList<News> list) {
        SQLiteDatabase db = this.getWritableDatabase();
        for (News news : list) {
            if (news == null) {
                continue;
            }
            ContentValues cv = new ContentValues();
            cv.put(DBContract.NewsTable.COLUMN_ID, news.getNid());
            cv.put(DBContract.NewsTable.COLUMN_TITLE, news.getTitle());
            cv.put(DBContract.NewsTable.COLUMN_DESCRIPTION, news.getDescription());
            cv.put(DBContract.NewsTable.COLUMN_DETAIL, news.getDetail());
            cv.put(DBContract.NewsTable.COLUMN_LINK, news.getLink());
            cv.put(DBContract.NewsTable.COLUMN_DATACREATE, news.getDatacreate());
            cv.put(DBContract.NewsTable.COLUMN_CID, news.getCid());
            cv.put(DBContract.NewsTable.COLUMN_WRITER, news.getWriter());
            long newRow = db.insert(DBContract.CategoryTable.TABLE_NAME, null, cv);
            if (newRow == -1) {
                Log.e("TAG1", news.getCid() + "lỗi khi chèn!");
            }
        }
        db.close();
    }

    //Lấy dữ liệu
    public ArrayList<News> getListNews(ArrayList<News> inList) {

        if (this.deleteAllNews()) {
            this.insertAllNews(inList);
        } else {
            Log.e("TAG1", "Lỗi khi xóa dữ liệu bảng news");
        }

        ArrayList<News> outList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        //Tạo con trỏ tới bảng sau khi truy vấn
        Cursor cs = db.rawQuery("SELECT * FROM " + DBContract.NewsTable.TABLE_NAME, null);
        while (cs.moveToNext()) {
            int nid = cs.getInt(0);
            String title = cs.getString(1);
            String description = cs.getString(2);
            String detail = cs.getString(3);
            String link = cs.getString(4);
            String datacreate = cs.getString(5);
            int cid = cs.getInt(6);
            String writer = cs.getString(7);
            News news = new News(nid, title, description, detail, link, datacreate, writer, cid);
            outList.add(news);
        }
        cs.close();
        db.close();
        return outList;
    }

    public ArrayList<News> getListNewsByIdCat(int idCat) {
        ArrayList<News> outList = new ArrayList<>();
        //Đầu tiên, lấy các cột cần get ra
        String[] columns = {
                DBContract.NewsTable.COLUMN_ID,
                DBContract.NewsTable.COLUMN_TITLE,
                DBContract.NewsTable.COLUMN_DESCRIPTION,
                DBContract.NewsTable.COLUMN_DETAIL,
                DBContract.NewsTable.COLUMN_LINK,
                DBContract.NewsTable.COLUMN_DATACREATE,
                DBContract.NewsTable.COLUMN_CID,
                DBContract.NewsTable.COLUMN_WRITER
        };
        // Đặt câu đk WHERE
        String selection = DBContract.NewsTable.COLUMN_CID + " = ?";
        // Đặt tham số cho đk
        String[] selectionArgs = {String.valueOf(idCat)};

        //Viết câu truy vấn
        SQLiteDatabase db = this.getReadableDatabase();
        // thực ra chỗ tham số columns có thể dùng null -> get hết các côt.
        Cursor cs = db.query(DBContract.NewsTable.TABLE_NAME, columns,selection, selectionArgs,null,null,null);
        while (cs.moveToNext()) {
            int nid = cs.getInt(0);
            String title = cs.getString(1);
            String description = cs.getString(2);
            String detail = cs.getString(3);
            String link = cs.getString(4);
            String datacreate = cs.getString(5);
            int cid = cs.getInt(6);
            String writer = cs.getString(7);
            News news = new News(nid, title, description, detail, link, datacreate, writer, cid);
            outList.add(news);
        }
        cs.close();
        db.close();
        return outList;
    }

}
