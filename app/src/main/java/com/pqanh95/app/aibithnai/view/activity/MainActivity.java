package com.pqanh95.app.aibithnai.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.pqanh95.app.aibithnai.R;
import com.pqanh95.app.aibithnai.constant.Key;
import com.pqanh95.app.aibithnai.manager.GameManager;
import com.pqanh95.app.aibithnai.manager.ImageManager;
import com.pqanh95.app.aibithnai.view.dialog.ContinueDialog;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ContinueDialog.OnClickButtonDialog {

    private static final String TAG = "MainActivity";
    private static final int MAX_GOI_Y = 16;
    private Random random;
    private GameManager gameManager;
    private boolean isLevelNew;

    private int dollar;

    private Button[] btn;
    private Button[] btnResult;
    private ImageView img1;
    private ImageView img2;
    private ProgressBar pgb1;
    private ProgressBar pgb2;
    private TextView txtLevel;
    private TextView txtDapAn1;
    private TextView txtDapAn2;
    private TextView txtDollar;

    private ContinueDialog mDialog;

    private int[] sttResult;

    private StringBuilder result; // dãy đáp án được nhập từ người dùng

    private String resultsCorrect; // đáp án đúng

    private StringBuilder suggestions; // dãy đáp án gợi ý

    private int numberOfChar; // độ dài của đáp án ( từ 1 đến 8 )

    private void initComponent() {
        btn = new Button[]{
                findViewById(R.id.btn1),
                findViewById(R.id.btn2),
                findViewById(R.id.btn3),
                findViewById(R.id.btn4),
                findViewById(R.id.btn5),
                findViewById(R.id.btn6),
                findViewById(R.id.btn7),
                findViewById(R.id.btn8),
                findViewById(R.id.btn9),
                findViewById(R.id.btn10),
                findViewById(R.id.btn11),
                findViewById(R.id.btn12),
                findViewById(R.id.btn13),
                findViewById(R.id.btn14),
                findViewById(R.id.btn15),
                findViewById(R.id.btn16)
        };
        btnResult = new Button[]{
                findViewById(R.id.btnResult1),
                  findViewById(R.id.btnResult2),
                findViewById(R.id.btnResult3),
                findViewById(R.id.btnResult4),
                findViewById(R.id.btnResult5),
                findViewById(R.id.btnResult6),
                findViewById(R.id.btnResult7),
                findViewById(R.id.btnResult8)
        };
        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        pgb1 = findViewById(R.id.progress1);
        pgb2 = findViewById(R.id.progress2);
        txtDapAn1 = findViewById(R.id.txtAnswer1);
        txtDapAn2 = findViewById(R.id.txtAnswer2);
        txtLevel = findViewById(R.id.txtLevel);
        txtDollar = findViewById(R.id.txtDollar);

        gameManager = new GameManager();

        sttResult = new int[]{0, 0, 0, 0, 0, 0, 0, 0};

        result = new StringBuilder();
        suggestions = new StringBuilder();

        random = new Random();

        mDialog = new ContinueDialog(this);
        mDialog.setOnClickButtonDialog(this);

        dollar = 0;
    }


    private void initResetMap() {
        // tạo mới lại toàn bộ nội dung
        if (suggestions.length() != 0) {
            suggestions.delete(0, suggestions.length());
        }
        for (int i = 0; i < btnResult.length; i++) {
            btnResult[i].setText("");
            btnResult[i].setVisibility(View.GONE);
        }
        for (int i = 0; i < sttResult.length; i++) {
            sttResult[i] = 0;
        }
        for (int i = 0; i < btn.length; i++) {
            btn[i].setVisibility(View.VISIBLE);
            btn[i].setOnClickListener(this);
        }
        txtDapAn1.setVisibility(View.INVISIBLE);
        txtDapAn2.setVisibility(View.INVISIBLE);
        if (result.length() != 0) {
            result.delete(0, result.length());
        }
        txtLevel.setText("Level " + (gameManager.getLevel() + 1));
    }

    private void initData() {
        initResetMap();
        resultsCorrect = gameManager.getAnswer();
        // tạo dữ liệu cho phần tử trong mảng gợi ý
        suggestions.append(resultsCorrect);
        for (int i = 0; i < (MAX_GOI_Y - resultsCorrect.length()); i++) {
            int index = random.nextInt(Key.SUGGESTIONSCHAR.length);
            suggestions.append(Key.SUGGESTIONSCHAR[index] + "");
        }
        // sắp xếp lại các vị trí trong mảng đáp án gợi ý
        for (int i = 0; i < MAX_GOI_Y / 2; i++) {
            String s1 = suggestions.substring(i, i + 1);
            int r = random.nextInt(MAX_GOI_Y);
            String s2 = suggestions.substring(r, r + 1);
            suggestions.replace(i, i + 1, s2);
            suggestions.replace(r, r + 1, s1);
        }

        // set dữ liệu cho các nút button
        for (int i = 0; i < btn.length; i++) {
            btn[i].setText(suggestions.charAt(i) + "");
        }

        numberOfChar = gameManager.getLengthAnswer();
        // độ dài đáp án = số ô dãy chữ đáp án được hiển thị
        for (int i = 0; i < numberOfChar; i++) {
            btnResult[i].setVisibility(View.VISIBLE);
            btnResult[i].setOnClickListener(this);
        }
        txtDollar.setText(dollar + "");
    }

    private void playGame() {
        isLevelNew = false;
        initData();
        new ImageManager().loadImage(gameManager.getLink1(), img1, pgb1);
        new ImageManager().loadImage(gameManager.getLink2(), img2, pgb2);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        initComponent();
        playGame();

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (isLevelNew) {
            playGame();
        }
        Log.d(TAG, "OnStart()..");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "OnResume()..");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "OnPause()..");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop()..");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy()..");
    }

    // Bắt đầu xử lý sự kiện cho phần button nhấn gợi ý đáp án
    private void setResult(int i, String substring, int idBTN) {
        if (result.length() > numberOfChar) {
            Toast.makeText(this, "Xin lỗi hết cái để bạn nhấn mất rồi ! :) ", Toast.LENGTH_SHORT).show();
        } else {
            switch (i) {
                case 1:
                    sttResult[0] = idBTN;
                    break;
                case 2:
                    sttResult[1] = idBTN;
                    break;
                case 3:
                    sttResult[2] = idBTN;
                    break;
                case 4:
                    sttResult[3] = idBTN;
                    break;
                case 5:
                    sttResult[4] = idBTN;
                    break;
                case 6:
                    sttResult[5] = idBTN;
                    break;
                case 7:
                    sttResult[6] = idBTN;
                    break;
                case 8:
                    sttResult[7] = idBTN;
                    break;
            }
            updateChart();
        }
    }

    private void updateChart() {
        for (int i = 0; i < result.length(); i++) {
            if (!btnResult[i].getText().equals(result.substring(i, i + 1))) {
                if (!result.substring(i, i + 1).equals("0")) {
                    btnResult[i].setText(result.substring(i, i + 1));
                }
            }
        }
    }

    private boolean kiemTraSetNut() {
        if (result.length() <= numberOfChar) return true;
        if (result.length() > numberOfChar) if (result.indexOf("0") != -1) return true;
        return false;
    }

    private int kiemTraKetQua() {
        if (result.length() == numberOfChar) {
            if (result.indexOf("0") == -1) {
                // result đã có dữ liệu
                if (resultsCorrect.contentEquals(result)) {
                    return 1;
                } else {
                    return -1;
                }
            }
        }
        return 0;
    }

    private void resultTrue() {
        txtDapAn1.setText(gameManager.getAnswer1());
        txtDapAn1.setVisibility(View.VISIBLE);
        txtDapAn2.setText(gameManager.getAnswer2());
        txtDapAn2.setVisibility(View.VISIBLE);

//        Thread chuyenMan = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(1500);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                Intent i = new Intent(MainActivity.this, QuaManActivity.class);
//                i.putExtra(Key.LEVEL, gameManager.getLevel());
//                i.putExtra(Key.RESULT, gameManager.getAnswerVI());
//                startActivityForResult(i, Key.REQUEST_QUAMAN);
//
//            }
//        });
//        chuyenMan.start();
        mDialog.show();
    }

    private void selectionButton(int id) {
        if (result.length() == numberOfChar) {
            if (result.indexOf("0") == -1) {
                Toast.makeText(this, "Xin lỗi hết cái để bạn nhấn mất rồi ! :) ", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        int i = result.indexOf("0");

        // cv1: add giá trị vào dãy. Chú ý kiểm tra nếu dãy có đối tượng "0" thì add vào đó trước
        if (i == -1) {
            if (result.length() < numberOfChar) {
                result.append(btn[id].getText().toString());
                btn[id].setVisibility(View.INVISIBLE);
            }
            i = result.length();
        } else {
            result.replace(i, i + 1, btn[id].getText().toString());
            btn[id].setVisibility(View.INVISIBLE);
            i = i + 1;
        }

        // cv2: hiển thị lên trên btnResult và ẩn btn đã nhấn
        if (kiemTraSetNut()) setResult(i, result.substring(result.length() - 1), id + 1);

        // cv3: kiểm tra kết quả;
        if (kiemTraKetQua() == 1) {
            resultTrue();
        } else if (kiemTraKetQua() == -1) {
            Toast.makeText(this, "Đáp án sai!", Toast.LENGTH_SHORT).show();
        } else {
        }
    }
    // kết thúc xử lý sự kiện cho phần button nhấn gợi ý đáp án

    // bắt đầu phần xử lý sự kiện cho phần button đáp án lựa chọn
    private void resetButton(int idBTN) {
        btn[idBTN - 1].setVisibility(View.VISIBLE);
    }

    private void selectionResult(int id) {
        if (!btnResult[id].getText().toString().equals("")) {
            // cv1: hiện lại button đã lựa chọn ở btnResult này
            resetButton(sttResult[id]);
            // cv2: chuyển ký tự trong chuỗi thành 0 để quản lý
            result.replace(id, id + 1, "0");
            // cv3: set giá trị button về " "
            btnResult[id].setText("");
        } else {
            Toast.makeText(this, "Đã có giá trị đâu mà đòi xóa!", Toast.LENGTH_SHORT).show();
        }
    }
    // kết thúc phần xử lý sự kiện cho phần button đáp án lựa chọn

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // Các nút kết quả
            case R.id.btnResult1:
                selectionResult(0);
                break;
            case R.id.btnResult2:
                selectionResult(1);
                break;
            case R.id.btnResult3:
                selectionResult(2);
                break;
            case R.id.btnResult4:
                selectionResult(3);
                break;
            case R.id.btnResult5:
                selectionResult(4);
                break;
            case R.id.btnResult6:
                selectionResult(5);
                break;
            case R.id.btnResult7:
                selectionResult(6);
                break;
            case R.id.btnResult8:
                selectionResult(7);
                break;
            // Các nút nhấn để lựa chọn đáp án
            case R.id.btn1:
                selectionButton(0);
                break;
            case R.id.btn2:
                selectionButton(1);
                break;
            case R.id.btn3:
                selectionButton(2);
                break;
            case R.id.btn4:
                selectionButton(3);
                break;
            case R.id.btn5:
                selectionButton(4);
                break;
            case R.id.btn6:
                selectionButton(5);
                break;
            case R.id.btn7:
                selectionButton(6);
                break;
            case R.id.btn8:
                selectionButton(7);
                break;
            case R.id.btn9:
                selectionButton(8);
                break;
            case R.id.btn10:
                selectionButton(9);
                break;
            case R.id.btn11:
                selectionButton(10);
                break;
            case R.id.btn12:
                selectionButton(11);
                break;
            case R.id.btn13:
                selectionButton(12);
                break;
            case R.id.btn14:
                selectionButton(13);
                break;
            case R.id.btn15:
                selectionButton(14);
                break;
            case R.id.btn16:
                selectionButton(15);
                break;
        }
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == Key.REQUEST_QUAMAN && resultCode == Activity.RESULT_OK) {
//            int level = data.getIntExtra(Key.KEY_CONTINUE, 0);
//            isLevelNew = true;
//            gameManager.setLevel(level);
//        }
//    }

    @Override
    public void onClickButtonDialog() {
        gameManager.setLevel(gameManager.getLevel() + 1);
        dollar += 100;
        playGame();
    }
}
