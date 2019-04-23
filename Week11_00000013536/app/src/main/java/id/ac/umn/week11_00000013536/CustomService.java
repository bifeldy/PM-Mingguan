package id.ac.umn.week11_00000013536;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class CustomService extends Service {
    public CustomService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
