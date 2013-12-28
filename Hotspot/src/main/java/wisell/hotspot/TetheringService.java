package wisell.hotspot;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by yazid on 25/12/13.
 */
public class TetheringService extends Service{
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("LocalService", "Received start id " + startId + ": " + intent);
        // We want this service to continue running until it is explicitly
        // stopped, so return sticky.
        return START_STICKY;
    }
    // This is the object that receives interactions from clients. See
    // RemoteService for a more complete example.
    //private final IBinder mBinder = new LocalBinder();

    @Override
    public IBinder onBind(Intent intent) {
        //return mBinder;
        return null;
    }
}
