package itstep.learning.androidpv211;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AnimActivity extends AppCompatActivity {
    Animation alphaAnimation;
    Animation rotateAnimation;
    Animation rotate2Animation;
    Animation scaleAnimation;
    Animation ringAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_anim);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        alphaAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
        rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_rotate);
        findViewById(R.id.anim_v_alpha).setOnClickListener(
                v -> v.startAnimation(alphaAnimation)
        );
        findViewById(R.id.anim_v_rotate).setOnClickListener(
                v -> v.startAnimation(rotateAnimation)
        );

        rotate2Animation = AnimationUtils.loadAnimation(this, R.anim.anim_rotate2);
        findViewById(R.id.anim_v_rotate2).setOnClickListener(
                v -> v.startAnimation(rotate2Animation)
        );

        scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_scale);
        findViewById(R.id.anim_v_scale).setOnClickListener(
                v -> v.startAnimation(scaleAnimation)
        );

        scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_scale);
        findViewById(R.id.anim_v_scale).setOnClickListener(
                v -> v.startAnimation(scaleAnimation)
        );

        ringAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_ring);
        findViewById(R.id.anim_v_ring).setOnClickListener(
                v -> v.startAnimation(ringAnimation)
        );
    }
}

// Анімації
// Анімації в Андроід - це аналог CSS transition
// правила поступової зміни числових характеристик
// від початкового до кінцевого значення
// Створюємо ресурсну директорію anim
// (res - new res directory - anim)
// у ній анімаційний ресурс
// anim - new anim res file - alpha як кореневий
