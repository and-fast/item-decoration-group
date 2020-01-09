package and.fast.widget.itemdecorationgroup;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.view.View;
import android.view.ViewGroup;

import java.util.Calendar;
import java.util.Date;

import and.fast.widget.itemdecorationgroup.model.TimestampProvider;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AlbumItemDecoration extends RecyclerView.ItemDecoration {

    // 画笔
    private TextPaint mMonthTextPaint, mYearTextPaint;

    // 条目大小
    private int mItemSize;

    // 间距
    private int timelineSpace, horizontalSpace, monthYearVerticalSpace;

    public AlbumItemDecoration(RecyclerView recyclerView) {
        Resources resources = recyclerView.getContext().getResources();
        horizontalSpace = resources.getDimensionPixelSize(R.dimen.horizontal_space);
        monthYearVerticalSpace = resources.getDimensionPixelSize(R.dimen.month_year_vertical_space);
        timelineSpace = (recyclerView.getPaddingLeft() + resources.getDimensionPixelSize(R.dimen.timeline_space));

        // 设置边距
        recyclerView.setPadding(
                timelineSpace,
                recyclerView.getPaddingTop(),
                recyclerView.getPaddingRight() - horizontalSpace,
                recyclerView.getPaddingBottom()
        );

        // 设置 SpanSizeLookup
        GridLayoutManager layoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
        layoutManager.setSpanSizeLookup(new AlbumSpanSizeLookup(recyclerView));

        // 月份 文字画笔
        mMonthTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mMonthTextPaint.setColor(Color.parseColor("#333333"));
        mMonthTextPaint.setTypeface(Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD));
        mMonthTextPaint.setTextSize(resources.getDimensionPixelSize(R.dimen.month_text_size));

        // 年份 文字画笔
        mYearTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mYearTextPaint.setColor(Color.parseColor("#666666"));
        mYearTextPaint.setTextSize(resources.getDimensionPixelSize(R.dimen.year_text_size));
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        resetItemViewSize(parent, view);
        outRect.top = horizontalSpace;
    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        RecyclerView.Adapter adapter = parent.getAdapter();

        if (adapter instanceof TimestampProvider) {
            TimestampProvider provider = (TimestampProvider) adapter;
            int childCount = parent.getChildCount();

            for (int index = 0; index < childCount; index++) {
                View view = parent.getChildAt(index);
                int position = parent.getChildLayoutPosition(view);
                long timestamp = provider.getTimestamp(position);

                if (position < adapter.getItemCount() - 1) {
                    long nextTimestamp = provider.getTimestamp(position + 1);
                    if (position == 0) {
                        drawDate(c, view, timestamp);

                    } else if (timestamp != nextTimestamp) {
                        drawDate(c, parent.getChildAt(index + 1), nextTimestamp);
                    }
                }
            }

        } else {
            throw new ClassCastException("RecyclerView.Adapter必须实现Timeline对象");
        }
    }

    // 绘制时间
    private void drawDate(Canvas canvas, View view, long timestamp) {
        // 时间
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp);
        int month = calendar.get(Calendar.MONTH) + 1;

        // 文字宽高
        float monthTextWidth = mMonthTextPaint.measureText(month + "月");
        Paint.FontMetrics monthFontMetrics = mMonthTextPaint.getFontMetrics();
        float monthTextHeight = monthFontMetrics.descent - monthFontMetrics.ascent;

        // 绘制月份文字
        canvas.drawText(
                month + "月",
                view.getX() - timelineSpace / 2 - monthTextWidth / 2,
                view.getY() + monthTextHeight,
                mMonthTextPaint
        );

        // 绘制年份文字
        Calendar nowCalendar = Calendar.getInstance();
        nowCalendar.setTime(new Date());
        if (nowCalendar.get(Calendar.YEAR) != calendar.get(Calendar.YEAR)) {
            float yearTextWidth = mYearTextPaint.measureText(calendar.get(Calendar.YEAR) + "年");
            Paint.FontMetrics yearFontMetrics = mYearTextPaint.getFontMetrics();
            float yearTextHeight = yearFontMetrics.descent - yearFontMetrics.ascent;

            canvas.drawText(
                    calendar.get(Calendar.YEAR) + "年",
                    view.getX() - timelineSpace / 2 - yearTextWidth / 2,
                    view.getY() + yearTextHeight + monthTextHeight + monthYearVerticalSpace,
                    mYearTextPaint
            );
        }
    }

    // 重置条目大小
    private void resetItemViewSize(RecyclerView parent, View view) {
        if (mItemSize == 0) {
            int parentWidth = parent.getWidth() - (parent.getPaddingLeft() + parent.getPaddingRight());
            GridLayoutManager gridLayoutManager = (GridLayoutManager) parent.getLayoutManager();
            mItemSize = parentWidth / gridLayoutManager.getSpanCount();
        }

        ViewGroup.LayoutParams lp = view.getLayoutParams();
        lp.height = lp.width = mItemSize - horizontalSpace;
        view.setLayoutParams(lp);
    }

}
