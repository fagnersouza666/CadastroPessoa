package procergs.com.aplicacaoteste.permissao;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fagner-souza on 30/11/16.
 */

public class PermissaoUtil {

    public static boolean validade (Activity activity, int requestCode, String... permissions){
        List<String> list = new ArrayList<String>();

        for (String permission : permissions){

            boolean ok = ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED;
            if (!ok){
                list.add(permission);
            }
        }

        if (list.isEmpty()){
            return true;
        }

        String[] newPermissions = new String[list.size()];
        list.toArray(newPermissions);

        ActivityCompat.requestPermissions(activity, newPermissions, 1);

        return false;
    }
}
