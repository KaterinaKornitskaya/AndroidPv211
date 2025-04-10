package itstep.learning.androidpv211;

import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Services {
    public static String fetchUrl(String href){
        try{
            URL url = new URL(href);
            InputStream urlStream = url.openStream();  // GET-request
            ByteArrayOutputStream byteBuilder = new ByteArrayOutputStream();
            byte[] buffer = new byte[8192];
            int len;
            while( (len = urlStream.read(buffer)) >0 ){
                byteBuilder.write(buffer, 0, len);
            }
            String charsetName = StandardCharsets.UTF_8.name();
            String data = byteBuilder.toString(charsetName);

            urlStream.close();

            //runOnUiThread(()->tvContainer.setText(data));
            return data;

        }
        catch(MalformedURLException ex){
            Log.d("Services::fetchUrl", "MalformedURLException" + ex.getMessage());
        }
        catch (IOException ex) {
            Log.d("Services::fetchUrl", "IOException" + ex.getMessage());
        }
        return null;

        // android.os.NetworkOnMainThreadException -
        // всі запити до мережі мають бути в окремих потоках.

        // java.lang.SecurityException:
        // Permission denied (missing INTERNET permission?)
        // Багато дії в Android вимагає дозволів, зокрема Інтернет.
        // Дозволи декларуються у маніфесті
        // <uses-permission android:name="android.permission.INTERNET"/>

        // android.view.ViewRootImpl$CalledFromWrongThreadException:
        // Only the original thread that created a view hierarchy
        // can touch its views.
        // Для роботи з елементами UI необхідно повернути
        // управління до основного UI, для уього треба метод runOnUiThread()
    }
}
