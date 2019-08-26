package com.baidu.push.ommon_base.recyclerItemdecoration;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

/**
 *
 * 十字分割线
 */
public class RecyclerGridItemDecoration extends RecyclerView.ItemDecoration {
    private static final int [] ATTRS = new int[]{android.R.attr.listDivider};
    private Drawable mDrawable;

    public RecyclerGridItemDecoration(Context context) {
        final TypedArray array = context.obtainStyledAttributes(ATTRS);
        mDrawable =  array.getDrawable(0);
        array.recycle();;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        drawHorizontal(c,parent);
        drawVertical(c,parent);

    }
    private int getSpanCount(RecyclerView parent){
        int spanCount = -1;
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager){
            spanCount = ((GridLayoutManager) layoutManager).getSpanCount();

        }else if (layoutManager instanceof StaggeredGridLayoutManager){
            spanCount = ((StaggeredGridLayoutManager) layoutManager).getSpanCount();
        }

        return spanCount;
    }

    private void drawHorizontal(Canvas c, RecyclerView parent) {
        int childCount = parent.getChildCount();
        for (int i = 0;i<childCount;i++){
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int left = child.getLeft() - params.leftMargin;
            final int right = child.getRight() + params.rightMargin+mDrawable.getIntrinsicWidth();
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + mDrawable.getIntrinsicHeight();
            mDrawable.setBounds(left,top,right,bottom);
            mDrawable.draw(c);
        }
    }

    private void drawVertical(Canvas c, RecyclerView parent) {
            final int childCount = parent.getChildCount();
        for (int i =0;i<childCount;i++){
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int top = child.getTop() - params.topMargin;
            final int botton = child.getBottom() + params.bottomMargin;
            final int left = child.getRight() + params.rightMargin;
            final int right = left + mDrawable.getIntrinsicWidth();

            mDrawable.setBounds(left,top,right,botton);
            mDrawable.draw(c);
        }
    }
    private boolean isLastCoum(RecyclerView parent, int pos, int spanCount, int childCount){

        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager){
            if ((pos +1) % spanCount==0){//如果是最后一列则不需要绘制右边
                return true;
            }
        }else if (layoutManager instanceof StaggeredGridLayoutManager){
            int orientation = ((StaggeredGridLayoutManager) layoutManager).getOrientation();
            if (orientation == StaggeredGridLayoutManager.VERTICAL){
                if ((pos + 1) % spanCount == 0){
                    return true;
                }
            }else {
                childCount = childCount - childCount % spanCount;
                if (pos >= childCount)//如果最后一列,则不需要绘制右边
                return true;
            }
        }
        return false;
    }

    private boolean isLastRaw(RecyclerView parent, int pos, int spanCount, int childCount){
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager){
            childCount = childCount - childCount % spanCount;
            if (pos >= childCount)//如果是最后一行;则不需要绘制底部
                return true;
        }else if (layoutManager instanceof StaggeredGridLayoutManager){
            int orientation = ((StaggeredGridLayoutManager) layoutManager).getOrientation();
            if (orientation == StaggeredGridLayoutManager.VERTICAL){
                childCount = childCount - childCount % spanCount;
                if (pos >= childCount)
                    return true;
            }else {
                //如果是最后一行,则不需要绘制底部
                if ((pos + 1 ) % spanCount == 0){
                    return true;
                }
            }
        }
        return false;
    }



    @Override
    public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
        int spanCount = getSpanCount(parent);
        int childCount = parent.getAdapter().getItemCount();
        if (isLastRaw(parent,itemPosition,spanCount,childCount)){//如果是最后一行,则不需要绘制底部
            outRect.set(0,0,mDrawable.getIntrinsicWidth(),0);
        }else if (isLastCoum(parent,itemPosition,spanCount,childCount)) {//如果是最后一行,则不需要绘制右边
            outRect.set(0,0,0,mDrawable.getIntrinsicHeight());
        }else {
            outRect.set(0,0,mDrawable.getIntrinsicWidth(),mDrawable.getIntrinsicHeight());
        }
    }
}
