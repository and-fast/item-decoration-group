package and.fast.itemdecorationgroup;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;

import and.fast.itemdecorationgroup.model.Timeline;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AlbumItemDecoration extends RecyclerView.ItemDecoration {

    private int mItemSize; // 条目大小

    private int mSpanCount; // 横向数量

    private long mLastTimestamp;

    private int timelineSpace, horizontalSpace;

    public AlbumItemDecoration(RecyclerView recyclerView) {
        Resources resources = recyclerView.getContext().getResources();
        timelineSpace = resources.getDimensionPixelSize(R.dimen.timeline_space);
        horizontalSpace = resources.getDimensionPixelSize(R.dimen.horizontal_space);

        // 设置边距
        recyclerView.setPadding((recyclerView.getPaddingLeft() + timelineSpace), recyclerView.getPaddingTop(),
                recyclerView.getPaddingRight(), recyclerView.getPaddingBottom());
    }


    @Override
    public void getItemOffsets(
            @NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {

        resetItemViewSize(parent, view);

        // 条目间距
        int position = parent.getChildLayoutPosition(view);
        if ((mSpanCount - (position % mSpanCount)) != 1) {
            outRect.right = horizontalSpace;
        }

        outRect.top = horizontalSpace;
    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        RecyclerView.Adapter adapter = parent.getAdapter();

        if (adapter instanceof Timeline) {

            Timeline timeline = (Timeline) adapter;
            int childCount = parent.getChildCount();

            for (int index = 0; index < childCount; index++) {

                if (mLastTimestamp != timeline.getTimestamp(index)) {
                    mLastTimestamp = timeline.getTimestamp(index);
                    int position = parent.getChildLayoutPosition(parent.getChildAt(index));
                    drawTimeline(c, position);
                }
            }

        } else {
            throw new ClassCastException("RecyclerView.Adapter必须实现Timeline对象");
        }
    }

    // 绘制时间线
    private void drawTimeline(Canvas canvas, int position) {

    }

    // 重置条目大小
    private void resetItemViewSize(RecyclerView parent, View view) {
        if (mItemSize == 0) {
            int parentWidth = parent.getWidth() - (parent.getPaddingLeft() + parent.getPaddingRight());
            GridLayoutManager layoutManager = (GridLayoutManager) parent.getLayoutManager();
            mSpanCount = layoutManager.getSpanCount();
            mItemSize = parentWidth / mSpanCount;
        }

        ViewGroup.LayoutParams lp = view.getLayoutParams();
        lp.height = lp.width = mItemSize;
        view.setLayoutParams(lp);
    }

}
