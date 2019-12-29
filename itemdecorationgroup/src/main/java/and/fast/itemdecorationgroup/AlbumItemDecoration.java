package and.fast.itemdecorationgroup;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;
import android.util.Log;
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

        init(recyclerView);

        // 画毛
        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setTextSize(54);
    }

    // 设置填充
    private void init(final RecyclerView recyclerView) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
        mSpanCount = gridLayoutManager.getSpanCount();

        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {

            @Override
            public int getSpanSize(int position) {
                RecyclerView.Adapter adapter = recyclerView.getAdapter();
                Timeline timeline = (Timeline) adapter;
                long timestamp = timeline.getTimestamp(position);

                if (position < adapter.getItemCount() - 1) {
                    long nextTimestamp = timeline.getTimestamp(position + 1);
                    if (timestamp != nextTimestamp) {
                        return mSpanCount;
                    }
                }

                return 1;
            }

        });

        // throw new ClassCastException("RecyclerView.LayoutManager必须是GridLayoutManager");
    }

    @Override
    public void getItemOffsets(
            @NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {

        resetItemViewSize(parent, view);

        //int position = parent.getChildLayoutPosition(view);

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
        // 文字宽高
        float textWidth = mTextPaint.measureText(position + "月");
        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        float textHeight = fontMetrics.descent - fontMetrics.ascent;

        // 绘制文字
        canvas.drawText(
                position + "月",
                view.getX() - timelineSpace / 2 - textWidth / 2,
                view.getY() + textHeight,
                mTextPaint
        );
    }

    // 重置条目大小
    private void resetItemViewSize(RecyclerView parent, View view) {
        if (mItemSize == 0) {
            int parentWidth = parent.getWidth() - (parent.getPaddingLeft() + parent.getPaddingRight());
            mItemSize = parentWidth / mSpanCount;
        }

        ViewGroup.LayoutParams lp = view.getLayoutParams();
        lp.height = lp.width = mItemSize - horizontalSpace;
        view.setLayoutParams(lp);
    }

}
