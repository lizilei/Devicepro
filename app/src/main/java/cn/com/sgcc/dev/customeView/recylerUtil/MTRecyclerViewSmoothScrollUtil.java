package cn.com.sgcc.dev.customeView.recylerUtil;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * <p>@description:</p>
 *
 * @author lizilei
 * @version 1.0.0
 * @Email lizilei_warms@163.com
 * @since 2017/8/28
 */

public class MTRecyclerViewSmoothScrollUtil {

    public static void smoothScrollToPreOrNext(final LinearLayoutManager layoutManager,
                                               final RecyclerView recyclerView, final int curPosition) {
        int firstCompletePosition = layoutManager.findFirstCompletelyVisibleItemPosition();
        int lastCompletePosition = layoutManager.findLastCompletelyVisibleItemPosition();

        int firstPosition = layoutManager.findFirstVisibleItemPosition();
        int lastPosition = layoutManager.findLastVisibleItemPosition();
        if (firstPosition == curPosition) {
            if (curPosition > 0) {
                recyclerView.smoothScrollToPosition(curPosition - 1);
            } else {
                recyclerView.smoothScrollToPosition(0);
            }
        } else if (lastPosition == curPosition) {
            recyclerView.smoothScrollToPosition(curPosition + 1);
        } else if (firstCompletePosition == curPosition) {
            if (curPosition > 0) {
                recyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        recyclerView.smoothScrollToPosition(curPosition - 1);
                    }
                }, 0);
            } else {
                recyclerView.smoothScrollToPosition(0);
            }
        } else if (lastCompletePosition == curPosition) {
            recyclerView.smoothScrollToPosition(curPosition + 1);
        }
    }
}
