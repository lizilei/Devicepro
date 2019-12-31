package cn.com.sgcc.dev.view.activity;

import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;

import java.io.File;

import cn.com.sgcc.dev.R;
import cn.com.sgcc.dev.utils.ToastUtils;

/**
 * <p>@description:</p>
 *
 * @author lizilei
 * @version 1.0
 * @Email lizilei_warms@163.com
 * @since 2017/12/20
 */

public class PDFViewActivity extends BaseActivity {

    private PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_view);

        pdfView = (PDFView) findViewById(R.id.pdf_view);
        File file = new File(getIntent().getStringExtra("path"));
        pdfView.fromFile(file).defaultPage(1).onPageChange(new OnPageChangeListener() {
            @Override
            public void onPageChanged(int page, int pageCount) {
                ToastUtils.showToast(PDFViewActivity.this, page + "/" + pageCount);
            }
        }).load();
    }
}
