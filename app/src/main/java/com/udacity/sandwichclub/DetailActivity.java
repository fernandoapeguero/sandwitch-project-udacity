package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @BindView(R.id.origin_tv)
    TextView originTextView;
    @BindView(R.id.description_tv)
    TextView descriptionTextView;
    @BindView(R.id.ingredients_tv)
    TextView ingredientsTextView;
    @BindView(R.id.also_known_tv)
    TextView alsoKnownTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = 0;
        if (intent != null) {
            position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        }
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich.getDescription(), sandwich.getIngredients(), sandwich.getAlsoKnownAs(), sandwich.getMainName());
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(String description, List<String> ingredientsData, List<String> knownData, String name) {
        originTextView.setText(name);
        descriptionTextView.setText(description);

        StringBuilder knownNameBuilder = new StringBuilder();
        for (int i = 0; i < knownData.size(); i++) {
           knownNameBuilder.append(knownData.get(i));
        }

        StringBuilder ingredientsBuilder = new StringBuilder();
        for (int i =0 ; i < ingredientsData.size(); i++){
            ingredientsBuilder.append(ingredientsData.get(i));
        }

        ingredientsBuilder.append(".");
        ingredientsTextView.setText(ingredientsBuilder);
        alsoKnownTv.setText(knownNameBuilder);


    }
}
