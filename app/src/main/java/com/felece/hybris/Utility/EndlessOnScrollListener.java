package com.felece.hybris.Utility;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by hsmnzaydn on 13.10.2017.
 */

public abstract class EndlessOnScrollListener extends RecyclerView.OnScrollListener {

    public static String TAG = EndlessOnScrollListener.class.getSimpleName();

    private LinearLayoutManager llm;

    public EndlessOnScrollListener(LinearLayoutManager sglm) {
        this.llm = sglm;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        if (!recyclerView.canScrollVertically(-1)) {
            onScrolledToTop();
        } else if (!recyclerView.canScrollVertically(1)) {
            onScrolledToEnd();
        }
        if (dy < 0) {

            onScrolledUp(dy);
        } else if (dy > 0) {
            onScrolledDown(dy);
        }

    }

    public abstract void onScrolledToEnd();
    public void onScrolledUp(int dy) {
        onScrolledUp();
    }

    public void onScrolledDown(int dy) {
        onScrolledDown();
    }

    public void onScrolledUp() {}

    public void onScrolledDown() {}

    public void onScrolledToTop() {}

}