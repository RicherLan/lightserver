package lan.qxc.lightserver.util;

import net.coobird.thumbnailator.Thumbnails;

import java.io.File;
import java.io.IOException;

public class ThumbnailImageUtil {

    //缩略图后缀
    private static final String SSUFFIX = "_thumbnail";

    //按比例缩略 25%
    private static final float SCALE = 0.25f;

    public static String thumbnailImage(String dirPath,String filename,String toDirPath){
        String filenameNotSuffix = filename.substring(0,filename.lastIndexOf("."));
        String thumbnailFileName = filenameNotSuffix+SSUFFIX+filename.substring(filename.lastIndexOf("."));

        try {
            Thumbnails.of(dirPath+ File.separator+filename)
                    .scale(SCALE)
                    .toFile(toDirPath+File.separator+thumbnailFileName);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return toDirPath+File.separator+thumbnailFileName;
    }


    public static void main(String[] args) {
        System.out.println(thumbnailImage("D:\\Projects\\uploadfile\\dongtai_ic","1.jpg","D:\\Projects\\uploadfile\\dongtai_ic\\sl"));
    }

}
