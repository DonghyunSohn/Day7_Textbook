package hanssem.p295;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;


public class MainActivity extends ActionBarActivity {

    boolean isPageOpen = false; //슬라이딩 페이지가 보이는지 여부

    // 애니메이션 객체
    Animation translateLeftAnim;
    Animation translateRightAnim;

    // 슬라이딩으로 보여줄 페이지
    LinearLayout slidingPage01;

    Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = (Button) findViewById(R.id.button1);

        // 슬라이딩으로 보여질 레이아웃 객체 참조
        slidingPage01 = (LinearLayout) findViewById(R.id.slidingPage01);

        // 애니메이션 객체 로딩
        translateLeftAnim = AnimationUtils.loadAnimation(this, R.anim.translate_left);
        translateRightAnim = AnimationUtils.loadAnimation(this, R.anim.translate_right);

        // 애니메이션 객체에 리스너 설정 (슬라이딩 애니메이션을 감시할 리스너)
        SlidingPageAnimationListener animListener = new SlidingPageAnimationListener();
        translateLeftAnim.setAnimationListener(animListener);
        translateRightAnim.setAnimationListener(animListener);

    }

    public void onButton1Clicked(View v) {
        // 애니메이션 적용
        if (isPageOpen) {
            slidingPage01.startAnimation(translateRightAnim);  // 페이지가 열려 있으면 오른쪽으로 애니메이션
        } else {
            slidingPage01.setVisibility(View.VISIBLE);
            slidingPage01.startAnimation(translateLeftAnim);  // 페이지가 닫혀 있으면 보이도록 한 후 왼쪽으로 애니메이션
        }
    }

    // 애니메이션 리스너 정의
    private class SlidingPageAnimationListener implements Animation.AnimationListener {

        // 애니메이션 끝날 때 호출되는 메소드
        public void onAnimationEnd(Animation animation) {
            if (isPageOpen) {
                slidingPage01.setVisibility(View.INVISIBLE);

                button1.setText("Open");    //페이지가 열려 있으면 안 보이도록 하고, 버튼의 텍스트를 'open'으로 변경
                isPageOpen = false;
            } else {
                button1.setText("Close");   // 페이지가 닫혀 있으면 버튼의 텍스트를 'close'로 변경
                isPageOpen = true;
            }
        }

        public void onAnimationRepeat(Animation animation) {

        }

        public void onAnimationStart(Animation animation) {

        }

    }
}
