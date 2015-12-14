package hanssem.p279;

import android.app.Activity;

        import android.app.Activity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.KeyEvent;
        import android.view.View;
        import android.view.View.OnClickListener;
        import android.widget.Button;

public class AnotherActivity extends Activity {

    public static final int REQUEST_CODE_ANOTHER = 1001;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.another);

        // 버튼을 눌렀을 때 메인 액티비티로 돌아갈 수 있도록 메소드 호출
        Button returnButton = (Button) findViewById(R.id.returnButton);
        returnButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                close();
            }
        });

    }


    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {      // 하드웨어 [back] 버튼이 눌렸을 경우 새로 정의한 close() 메소드 호출
            close();

            return true;
        }

        return false;
    }


    private void close() {
        // 결과값을 전달하기 위한 인텐트 객체 만들기 (호출된 액티비티로 결과값 전송)
        Intent resultIntent = new Intent();
        resultIntent.putExtra("name", "mike");

        // 결과값 전달
        setResult(Activity.RESULT_OK, resultIntent);

        // 이 액티비티 없애기
        finish();
    }

}

