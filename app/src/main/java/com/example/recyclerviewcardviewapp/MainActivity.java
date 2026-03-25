package com.example.recyclerviewcardviewapp; // Defines the app package (namespace for this class)

import android.animation.ObjectAnimator;
import android.os.Bundle; // Used to pass data between Android components
import android.view.View;

import androidx.activity.EdgeToEdge; // Enables edge-to-edge UI (content goes behind system bars)
import androidx.appcompat.app.AppCompatActivity; // Base class for activities using AppCompat support
import androidx.core.splashscreen.SplashScreen;
import androidx.recyclerview.widget.LinearLayoutManager; // Layout manager for RecyclerView
import androidx.recyclerview.widget.RecyclerView; // RecyclerView component

import java.util.ArrayList; // Dynamic array implementation
import java.util.List; // List interface


public class MainActivity extends AppCompatActivity { // Main entry screen of your app

    private RecyclerView recyclerView;
    // RecyclerView that will display categories
    private CategoryAdapter categoryAdapter;
    // Adapter that binds category data to RecyclerView

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Install splash screen
        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);


        // Exit Splash screen animation
        splashScreen.setOnExitAnimationListener(splashView -> {

            // Get splash screen view
            View view = splashView.getView();

            // Create slide-up animation (moves view up)
            ObjectAnimator slideUp = ObjectAnimator.ofFloat(
                    view,
                    View.TRANSLATION_Y,
                    0f,
                    -view.getHeight()
            );

            // Animation speed (0.6 seconds)
            slideUp.setDuration(600); // Animation speed (0.6 seconds)

            slideUp.start(); // Start animation

            slideUp.addListener(new android.animation.AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(android.animation.Animator animation) {
                    splashView.remove(); // Remove splash after animation ends
                }
            });
        });


        super.onCreate(savedInstanceState);
        // Calls parent implementation

        EdgeToEdge.enable(this);
        // Enables full-screen UI (behind status/navigation bars)

        setContentView(R.layout.activity_main);
        // Sets the UI layout for this activity

        // Link RecyclerView from XML to Java
        recyclerView = findViewById(R.id.recyclerView);

        // Create LayoutManager (VERTICAL list of categories)
        recyclerView.setLayoutManager(
                new LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        );

        // Create adapter instance and pass context
        categoryAdapter = new CategoryAdapter(this);

        // Set data into adapter
        categoryAdapter.setData(getListCategory());

        // Attach adapter to RecyclerView
        recyclerView.setAdapter(categoryAdapter);
    }

    // Method to create and return list of categories
    private List<Category> getListCategory() {

        List<Category> listCategory = new ArrayList<>(); // List that holds all categories

        // ------------------ CREATE CARD ITEMS ------------------

        List<CardItem> listItems = new ArrayList<>(); // List of cards for each category

        listItems.add(new CardItem(R.drawable.heart_health, "Heart Health", "Monitor your heart rate"));
        listItems.add(new CardItem(R.drawable.doctor_consultation, "Consultation", "Talk to certified doctors"));
        listItems.add(new CardItem(R.drawable.medicatiom_reminder, "Medication", "Track your daily meds"));
        listItems.add(new CardItem(R.drawable.lab_results, "Lab Results", "View your test reports"));
        listItems.add(new CardItem(R.drawable.emergency_help, "Emergency", "Get help instantly"));
        listItems.add(new CardItem(R.drawable.healthy_food, "Nutrition", "Eat healthy daily"));
        listItems.add(new CardItem(R.drawable.mental_wellness, "Mental Health", "Care for your mind"));
        listItems.add(new CardItem(R.drawable.stay_active, "Stay Active", "Keep your body fit"));

        // ------------------ ADD CATEGORIES ------------------

        // Each category contains the SAME list of items (you can later customize this)
        listCategory.add(new Category("Health Services", listItems));
        listCategory.add(new Category("Wellness", listItems));
        listCategory.add(new Category("Quick Access", listItems));

        return listCategory;
        // Return the full list to adapter
    }
}