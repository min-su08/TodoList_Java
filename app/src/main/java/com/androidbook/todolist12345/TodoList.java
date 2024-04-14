package com.androidbook.todolist12345;

import android.annotation.SuppressLint;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class TodoList extends AppCompatActivity {

    ArrayList<String> toDoList;
    ArrayAdapter<String> adapter;
    ListView listView;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todolist);

        // 초기화
        toDoList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, toDoList);
        listView = findViewById(R.id.list_view);
        editText = findViewById(R.id.edit_text);

        // 어댑터 적용
        listView.setAdapter(adapter);

        // 할일 추가 버튼 이벤트
        Button addBtn = findViewById(R.id.add_btn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItemToList();
            }
        });

        // 리스트 아이템 클릭 했을 때 이벤트
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView textView = (TextView) view;
                // 완료선 추가
                textView.setPaintFlags(textView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            }
        });

        // 리스트 아이템 롱클릭 했을 때 이벤트 (삭제 기능)
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                // 선택된 아이템 삭제
                toDoList.remove(i);
                adapter.notifyDataSetChanged();

                // 여기에 원하는 추가 작업을 수행할 수 있습니다 (예: 삭제된 아이템에 대한 처리)
                Toast.makeText(getApplicationContext(),  " 항목이 삭제되었습니다.", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    // 할일 추가 메서드
    public void addItemToList() {
        // 입력된 텍스트를 리스트에 추가
        String item = editText.getText().toString().trim();
        if (!item.isEmpty()) {
            toDoList.add(item);
            adapter.notifyDataSetChanged();
            editText.setText(""); // 입력창 초기화
        }
    }
}
