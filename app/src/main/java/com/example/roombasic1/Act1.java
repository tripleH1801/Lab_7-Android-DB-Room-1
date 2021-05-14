package com.example.roombasic1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Act1 extends AppCompatActivity {


    public static int idSelected;
    private RecyclerView rec;
    private Button btnAdd;
    private Button btnRemove;
    private Button btnCancel;
    private TextView tvName;

    private Database db;
    private UserDao userDao;

    private Adapter adt;
    private List<User> list;
    private Context ctx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act1);

        idSelected = -1;

        rec = findViewById(R.id.rec1);
        btnAdd = findViewById(R.id.btnAdd);
        btnRemove = findViewById(R.id.btnRemove);
        btnCancel = findViewById(R.id.btnCancel);
        tvName = findViewById(R.id.tvAdd);

        db = Room.databaseBuilder(getApplicationContext(),
                Database.class, "database-name")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
        userDao = db.userDao();

//        this.runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                userDao.insertAll(new User("Thanh Hóa"));
//                userDao.insertAll(new User("Đà Lạt"));
//                userDao.insertAll(new User("Buôn Mê Thuộc"));
//                userDao.insertAll(new User("Cần Thơ"));
//                userDao.insertAll(new User("Phú Quốc"));
//                userDao.insertAll(new User("Lý Sơn"));
//            }
//        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = tvName.getText().toString();
                if(name.trim() == ""){
                    return;
                }
                User entity = new User(name);
                userDao.insertAll(entity);
                loadDataToList();
                idSelected = -1;
                Adapter.selectedView = null;
            }
        });

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(idSelected == -1){
                    Toast.makeText(ctx, "Khong co dong nao duoc chon", Toast.LENGTH_SHORT).show();
                    return;
                }
                xoaUser(new User(idSelected, ""));
                loadDataToList();
                idSelected = -1;
                Adapter.selectedView = null;
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvName.setText("");
                idSelected = -1;
                Adapter.selectedView = null;
            }
        });

        loadDataToList();
    }
    public void loadDataToList(){
        List<User> list = new ArrayList<>();
        list = db.userDao().getAll();
        adt = new Adapter(list, this);
        rec.setAdapter(adt);
        rec.setLayoutManager(new LinearLayoutManager(this));
    }

    public void xoaUser(User u){
        userDao.delete(u);
        loadDataToList();
    }
}