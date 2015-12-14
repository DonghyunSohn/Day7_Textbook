package hanssem.p300;


import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

/**
 * ScreenView Flipper
 *
 * @author Mike
 */
public class ScreenViewFlipper extends LinearLayout implements OnTouchListener {

    /**
     * Count of index buttons. Default is 3
     */
    public static int countIndexes = 3;

    /**
     * Button Layout
     */
    LinearLayout buttonLayout;

    /**
     * Index button images
     */
    ImageView[] indexButtons;

    /**
     * Views for the Flipper
     */
    View[] views;

    /**
     * Flipper instance
     */
    ViewFlipper flipper;

    /**
     * X coordinate for touch down
     */
    float downX;

    /**
     * X coordinate for touch up
     */
    float upX;

    /**
     * Current index
     */
    int currentIndex = 0;


    public ScreenViewFlipper(Context context) {
        super(context);

        init(context);
    }

    public ScreenViewFlipper(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }


    public void init(Context context) {
        setBackgroundColor(0xffbfbfbf);

        // 뷰플리퍼 화면을 위한 레이아웃 인플레이션
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.screenview, this, true);

        buttonLayout = (LinearLayout) findViewById(R.id.buttonLayout);
        flipper = (ViewFlipper) findViewById(R.id.flipper);

        // 뷰플리퍼 객체에 터치 이벤트 리스너 설정
        flipper.setOnTouchListener(this);

        LayoutParams params = new LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        params.leftMargin = 50;

        // 상단의 버튼들 객체 생성
        indexButtons = new ImageView[countIndexes];

        // 각 화면의 텍스트뷰들 객체 생성
        views = new TextView[countIndexes];
        for(int i = 0; i < countIndexes; i++) {
            indexButtons[i] = new ImageView(context);

            if (i == currentIndex) {
                indexButtons[i].setImageResource(R.drawable.green);
            } else {
                indexButtons[i].setImageResource(R.drawable.white);
            }

            indexButtons[i].setPadding(10, 10, 10, 10);
            buttonLayout.addView(indexButtons[i], params);

            TextView curView = new TextView(context);
            curView.setText("View #" + i);
            curView.setTextColor(Color.RED);
            curView.setTextSize(32);
            views[i] = curView;

            flipper.addView(views[i]);      // 플리퍼에 뷰 추가
        }


    }

    private void updateIndexes() {
        for(int i = 0; i < countIndexes; i++) {
            if (i == currentIndex) {
                indexButtons[i].setImageResource(R.drawable.green);
            } else {
                indexButtons[i].setImageResource(R.drawable.white);
            }
        }
    }

    public boolean onTouch(View v, MotionEvent event) {
        if(v != flipper) return false;

        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            downX = event.getX();
        }
        else if(event.getAction() == MotionEvent.ACTION_UP){
            upX = event.getX();

            // 뷰플리퍼에 애니메이션 설정
            if( upX < downX ) {

                flipper.setInAnimation(AnimationUtils.loadAnimation(getContext(),
                        R.anim.wallpaper_open_enter));
                flipper.setOutAnimation(AnimationUtils.loadAnimation(getContext(),
                        R.anim.wallpaper_open_exit));

                if (currentIndex < (countIndexes-1)) {
                    flipper.showNext();

                    currentIndex++;
                    updateIndexes();
                }
            } else if (upX > downX){

                flipper.setInAnimation(AnimationUtils.loadAnimation(getContext(),
                        R.anim.push_right_in));
                flipper.setOutAnimation(AnimationUtils.loadAnimation(getContext(),
                        R.anim.push_right_out));

                if (currentIndex > 0) {
                    flipper.showPrevious();     // 이전 화면을 보기 위한 showPrevious() 메소드 호출


                    currentIndex--;
                    updateIndexes();
                }
            }
        }

        return true;
    }

}