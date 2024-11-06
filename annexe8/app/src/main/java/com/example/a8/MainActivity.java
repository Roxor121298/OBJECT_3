package com.example.a8;

import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.graphics.Path;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.view.animation.PathInterpolator;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a8.R;

public class MainActivity extends AppCompatActivity {

    private LinearLayout imageContainer,imageContainer2;
    private boolean isVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageContainer = findViewById(R.id.imageContainer);

        imageContainer2 = findViewById(R.id.imageContainer2);

        imageContainer2.setOnClickListener(v -> toggleContainerVisibility());

        // Set a click listener on the blue strip to toggle visibility
        imageContainer.setOnClickListener(v -> startSmoothCircularAnimation());

       /* imageContainer.setOnClickListener(v ->{
                        startSmoothCircularAnimation();
                findViewById(android.R.id.content).requestFocus();});*/

    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_SPACE && event.getAction() == KeyEvent.ACTION_DOWN) {
            startCombinedAnimation();
            return true;
        }
        return super.dispatchKeyEvent(event);
    }

    private void toggleContainerVisibility() {
        float targetTranslationY = isVisible ? 100f : -100f; // vecteur(math == direction) pour l'animation
        ObjectAnimator animator = ObjectAnimator.ofFloat(imageContainer2, "translationY", targetTranslationY); // parametre pour un animation
        animator.setDuration(300); // sur combien de temps on fait cette animation
        animator.setInterpolator(new BounceInterpolator()); // pour mettre des mods sur l'animation
        animator.start();
        isVisible = !isVisible;
    }


    /*private void animationSpecial(){
        ObjectAnimator animation2 = ObjectAnimator.ofArgb(imageContainer, "backgroundColor", Color.RED, Color.GREEN);
    }*/

    private void startSmoothCircularAnimation() {
        // Get the current X and Y coordinates of the view
        float currentX = imageContainer.getX();
        float currentY = imageContainer.getY();

        // Define a path that starts from the current position
        Path path = new Path();
        path.moveTo(currentX, currentY);  // Start from the current position

        // Create arcs to form a smooth circle relative to the current position
        path.arcTo(currentX, currentY, currentX + 400, currentY + 400, 0, 90, false);   // Quarter circle
        path.arcTo(currentX, currentY, currentX + 400, currentY + 400, 90, 90, false);  // Next quarter
        path.arcTo(currentX, currentY, currentX + 400, currentY + 400, 180, 90, false); // Third quarter
        path.arcTo(currentX, currentY, currentX + 400, currentY + 400, 270, 90, false); // Final quarter

        // Create ObjectAnimator that follows the path
        ObjectAnimator animator = ObjectAnimator.ofFloat(imageContainer, View.X, View.Y, path);
        animator.setDuration(2000); // Longer duration for smoother motion

        // Use PathInterpolator for a more natural, curved motion
        animator.setInterpolator(new PathInterpolator(0.5f, 0.1f, 0.3f, 1f));

        // Start the animation
        animator.start();
    }

    private void startCombinedAnimation() {
        // Movement animation: Move view down by 200 pixels
        ObjectAnimator moveAnimator = ObjectAnimator.ofFloat(imageContainer, "translationY", imageContainer.getTranslationY() + 200f);
        moveAnimator.setDuration(5000);

        // Color change animation: Change color from red to green
        ObjectAnimator colorAnimator = ObjectAnimator.ofArgb(imageContainer, "backgroundColor", Color.RED, Color.GREEN);
        colorAnimator.setDuration(5000);
        colorAnimator.setEvaluator(new ArgbEvaluator()); // Smooth color transition

        // Combine animations with AnimatorSet
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(moveAnimator, colorAnimator); // Run animations in parallel
        animatorSet.start();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {

            case KeyEvent.KEYCODE_SPACE:
                startCombinedAnimation();
                return true;

            case KeyEvent.KEYCODE_X:
                imageContainer.setRotationX(imageContainer.getRotation() - 15);
                return true;

            case KeyEvent.KEYCODE_Z:
                imageContainer.setRotationX(imageContainer.getRotation() + 15);
                return true;

            case KeyEvent.KEYCODE_A:
                // Trigger animation or logic for "A" key press
                imageContainer.setTranslationX(imageContainer.getTranslationX() - 25f);
                //toggleContainerVisibility();
                return true;

            case KeyEvent.KEYCODE_S:
                // Trigger animation or logic for "S" key press
                // Example: Reverse animation or another effect
                imageContainer.setTranslationY(imageContainer.getTranslationY() + 25f);
                //toggleContainerVisibility();
                return true;

            case KeyEvent.KEYCODE_D:
                // Trigger animation or logic for "D" key presss
                // Example: Move container to the right
                imageContainer.setTranslationX(imageContainer.getTranslationX() + 25f);
                return true;

            case KeyEvent.KEYCODE_W:
                // Trigger animation or logic for "W" key pressds
                // Example: Move container up
                imageContainer.setTranslationY(imageContainer.getTranslationY() - 25f);
                return true;

            default:
                return super.onKeyDown(keyCode, event);
        }
    }
}
