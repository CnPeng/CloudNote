package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Peng on 2016/7/24.
 */
public class AboutTime {
    public static String getTime() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = dateFormat.format(date);
        return  currentTime;
    }
}
