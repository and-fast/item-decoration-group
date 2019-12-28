package and.fast.itemdecorationgroup;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;
import android.view.View;
import android.view.ViewGroup;

import and.fast.itemdecorationgroup.model.Timeline;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AlbumItemDecoration extends RecyclerView.ItemDecoration {

    private TextPaint mTextPaint;

    private int mItemSize; // 条目大小

    private int mSpanCount; // 横向数量

    private long mLastTimestamp;

    private int timelineSpace, horizontalSpace;

    public AlbumItemDecoration(RecyclerView recyclerView) {
        Resources resources = recyclerView.getContext().getResources();
        horizontalSpace = resources.getDimensionPixelSize(R.dimen.horizontal_space);
        timelineSpace = (recyclerView.getPaddingLeft() + resources.getDimensionPixelSize(R.dimen.timeline_space));

        // 设置边距
        recyclerView.setPadding(
                timelineSpace,
                recyclerView.getPaddingTop(),
                recyclerView.getPaddingRight() - horizontalSpace,
                recyclerView.getPaddingBottom()
        );

        // 画毛
        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setTextSize(54);
    }

    @Override
    public void getItemOffsets(
            @NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {

        resetItemViewSize(parent, view);

        int position = parent.getChildLayoutPosition(view);

        outRect.top = horizontalSpace;
    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        RecyclerView.Adapter adapter = parent.getAdapter();

        if (adapter instanceof Timeline) {
            Timeline timeline = (Timeline) adapter;
            int childCount = parent.getChildCount();

            for (int index = 0; index < childCount; index++) {
                View view = parent.getChildAt(index);
                int position = parent.getChildLayoutPosition(view);
                long timestamp = timeline.getTimestamp(position);

                if (mLastTimestamp != timestamp) {
                    mLastTimestamp = timestamp;
                    drawTimeline(c, view, position);
                }
            }

        } else {
            throw new ClassCastException("RecyclerView.Adapter必须实现Timeline对象");
        }
    }

    // 绘制时间线
    private void drawTimeline(Canvas canvas, View view, int position) {
        String text = String.valueOf(position);
        float v = mTextPaint.measureText(text);

        canvas.drawText(
                text,
                view.getX() - timelineSpace / 2,
                view.getY(),
                mTextPaint
        );
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
        lp.height = lp.width = mItemSize - horizontalSpace;
        view.setLayoutParams(lp);
    }

}
