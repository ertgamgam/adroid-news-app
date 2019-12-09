package com.cicekgamgam.news;


import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public abstract class LastItemScrollListener extends RecyclerView.OnScrollListener {

    @Override
    public void onScrolled(@NonNull  RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        // init
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        RecyclerView.Adapter adapter = recyclerView.getAdapter();

        if (layoutManager.getChildCount() > 0) {
            // Calculations..
            int indexOfLastItemViewVisible = layoutManager.getChildCount() -1;
            View lastItemViewVisible = layoutManager.getChildAt(indexOfLastItemViewVisible);
            int adapterPosition = layoutManager.getPosition(lastItemViewVisible);
            boolean isLastItemVisible = (adapterPosition == adapter.getItemCount() -1);

            // check
            if (isLastItemVisible)
                onLastItemVisible(); // callback
        }
    }

    /**
     * Here you should load more items because user is seeing the last item of the list.
     * Advice: you should add a bollean value to the class
     * so that the method {@link #onLastItemVisible()} will be triggered only once
     * and not every time the user touch the screen ;)
     **/
    public abstract void onLastItemVisible();

}
