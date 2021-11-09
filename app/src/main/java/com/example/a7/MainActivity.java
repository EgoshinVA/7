package com.example.a7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    String fileName = "content.txt";
    File file = new
            File(Environment.getExternalStorageDirectory().getAbsolutePath(),
            fileName);

    private Button button1;
    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try
                {
                    EditText textBox = (EditText) findViewById(R.id.save_text);
                    String text = textBox.getText().toString();
                    FileOutputStream fos = new FileOutputStream(file);
                    fos.write(text.getBytes());
                    fos.close();
                    Toast.makeText(getApplicationContext(), "Текстовый файл успешно сохранён!", Toast.LENGTH_SHORT).show();

                } catch (FileNotFoundException e)
                {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Файл не найден!", Toast.LENGTH_SHORT).show();
                } catch (IOException e)
                {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Ошибка сохранения файла!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try
                {
                    FileInputStream fin = new FileInputStream(file);


                    byte[] bytes = new byte[fin.available()];
                    fin.read(bytes);
                    String text = new String(bytes);
                    TextView textView = (TextView) findViewById(R.id.open_text);
                    textView.setText(text);
                    fin.close();
                } catch (IOException ex)
                {
                    Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}