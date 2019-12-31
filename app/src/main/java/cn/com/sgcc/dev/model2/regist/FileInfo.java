package cn.com.sgcc.dev.model2.regist;

/**
 * <p>@description:</p>
 *
 * @author lizilei
 * @version V1.0
 * @Email lizilei_warms@163.com
 * @since 2019/6/25 0025 下午 2:32
 */

public class FileInfo {
    private String fileName;
    private String filePath;
    private boolean isDirectory;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public boolean isDirectory() {
        return isDirectory;
    }

    public void setDirectory(boolean directory) {
        isDirectory = directory;
    }
}
