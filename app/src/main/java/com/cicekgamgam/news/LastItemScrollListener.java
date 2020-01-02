package com.cicekgamgam.news;


import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Objects;

public abstract class LastItemScrollListener extends RecyclerView.OnScrollListener {

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        RecyclerView.Adapter adapter = recyclerView.getAdapter();

        if (Objects.requireNonNull(layoutManager).getChildCount() > 0) {
            int indexOfLastItemViewVisible = layoutManager.getChildCount() - 1;
            View lastItemViewVisible = layoutManager.getChildAt(indexOfLastItemViewVisible);
            int adapterPosition = layoutManager.getPosition(Objects.requireNonNull(lastItemViewVisible));
            boolean isLastItemVisible = (adapterPosition == Objects.requireNonNull(adapter).getItemCount() - 1);

            if (isLastItemVisible)
                onLastItemVisible();
        }
    }

    public abstract void onLastItemVisible();

}
