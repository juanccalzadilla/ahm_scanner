package scanR;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.KeyEvent;

import com.olc.scan.IScanServer;
import java.util.Map;

public class ScanManager {

    private static final String TAG = "ScanManager" ;

    private IScanServer mService;

    private Context mContext ;

    private static ScanManager Instance;

    public static ScanManager getInstance(Context context) {
        if (Instance == null) {
            Instance = new ScanManager(context);
        }
        return Instance;
    }

    public ScanManager(Context context) {
        this.mContext = context;

        Intent i = new Intent("com.olc.scan.ScanServer");
        i.setPackage("com.olc.scan");
        context.bindService(i,conn, Service.BIND_AUTO_CREATE);
    }

    ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mService= IScanServer.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };

    public boolean initScanner(){
        if(mService != null){
            try {
                mService.initScanner();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return false ;
    }

    public boolean scanProcess(KeyEvent event){
        if(mService != null){
            try {
                mService.scanProcess(event);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return false ;
    }

    public int getReaderState(){
        if(mService != null){
            try {
                mService.getReaderState();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return 0 ;
    }

    public void startRead(){
        if(mService != null){
            try {
                mService.startRead();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void stopRead(){
        if(mService!=null){
            try {
                mService.stopRead();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void closeReader(){
        if(mService!=null){
            try {
                mService.closeReader();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }


    public int setStringParam(int key, String value){
        if(mService!=null){
            try {
                return mService.setStringParam(key , value);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return -1 ;
    }

    public int setIntParam(int key , int value){
        if(mService!=null){
            try {
                return mService.setIntParam(key , value);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return -1 ;
    }

    public String getStringParam(int key){
        if(mService!=null){
            try {
                return mService.getStringParam(key );
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    public int getIntParam(int key){
        if(mService!=null){
            try {
                return mService.getIntParam(key);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return -1 ;
    }

    public void setScanSwitchLeft(boolean flag){
        if(mService!=null){
            try {
                mService.setScanSwitchLeft(flag);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean getScanSwitchLeft(){
        if(mService!=null){
            try {
                return mService.getScanSwitchLeft();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public void setScanSwitchMiddle(boolean flag){
        if(mService!=null){
            try {
                mService.setScanSwitchMiddle(flag);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean getScanSwitchMiddle(){
        if(mService!=null){
            try {
                return mService.getScanSwitchMiddle();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public void setScanSwitchRight(boolean flag){
        if(mService!=null){
            try {
                mService.setScanSwitchRight(flag);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean getScanSwitchRight(){
        if(mService!=null){
            try {
                return mService.getScanSwitchRight();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public void setScanOperatingMode(int state){
        if(mService!=null){
            try {
                mService.setScanOperatingMode(state);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public int getScanOperatingMode(){
        if(mService!=null){
            try {
                return mService.getScanOperatingMode();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public void setScanSound(boolean flag){
        if(mService!=null){
            try {
                mService.setScanSound(flag);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean getScanSound(){
        if(mService!=null){
            try {
                return mService.getScanSound();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public void setScanVibrate(boolean flag){
        if(mService!=null){
            try {
                mService.setScanVibrate(flag);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean getScanVibrate(){
        if(mService!=null){
            try {
                return mService.getScanVibrate();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public void setBarcodeReceiveModel(int state){
        if(mService!=null){
            try {
                mService.setBarcodeReceiveModel(state);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public int getBarcodeReceiveModel(){
        if(mService!=null){
            try {
                return mService.getBarcodeReceiveModel();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public void setScanDelaySetting(int state){
        if(mService!=null){
            try {
                mService.setScanDelaySetting(state);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public int getScanDelaySetting(){
        if(mService!=null){
            try {
                return mService.getScanDelaySetting();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public void setScanDelay(float delay){
        if(mService!=null){
            try {
                mService.setScanDelay(delay);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public float getScanDelay(){
        if(mService!=null){
            try {
                return mService.getScanDelay();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return 0.5f;
    }

    public void setBarcodeSeparator(int state){
        if(mService!=null){
            try {
                mService.setBarcodeSeparator(state);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public int getBarcodeSeparator(){
        if(mService!=null){
            try {
                return mService.getBarcodeSeparator();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public void setBarcodePrefixState(boolean flag){
        if(mService!=null){
            try {
                mService.setBarcodePrefixState(flag);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean getBarcodePrefixState(){
        if(mService!=null){
            try {
                return mService.getBarcodePrefixState();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public void setBarcodePrefix(String prefix){
        if(mService!=null){
            try {
                mService.setBarcodePrefix(prefix);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public String getBarcodePrefix(){
        if(mService!=null){
            try {
                return mService.getBarcodePrefix();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    public void setBarcodeSuffixState(boolean flag){
        if(mService!=null){
            try {
                mService.setBarcodeSuffixState(flag);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean getBarcodeSuffixState(){
        if(mService!=null){
            try {
                return mService.getBarcodeSuffixState();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public void setBarcodeSuffix(String suffix){
        if(mService!=null){
            try {
                mService.setBarcodeSuffix(suffix);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public String getBarcodeSuffix(){
        if(mService!=null){
            try {
                return mService.getBarcodeSuffix();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    public Map<String, String> getCM30Symbologies1D() {
        try {
            Log.d(TAG, "getCM30Symbologies");
            return mService.getBarCode1DType();
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    public Map<String, String> getCM30Symbologies2D() {
        try {
            Log.d(TAG, "getCM30Symbologies");
            return mService.getBarCode2DType();
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    public Map<String, String> getCM30SymbologiesPost() {
        try {
            Log.d(TAG, "getCM30Symbologies");
            return mService.getBarCodePostType();
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    public int setCM30ScanParam(String paramName, String enable, String status){
        try {
            Log.d(TAG, "setCM30ScanParam");
            return mService.setCM30ScanParam(paramName,enable,status);
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return -1;
        }
    }

    public String getCM30ScanParam(String paramName, String value) {
        try {
            Log.d(TAG, "getCM30ScanParam");
            return mService.getCM30ScanParam(paramName,value);
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "";
        }
    }

    public Map<String, String> getCM30CodeMoreMap(String codeName) {
        try {
            Log.d(TAG, "getCM30CodeMoreMap");
            return mService.getCM30CodeMoreMap(codeName);
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    public void setParameters(){
        try {
            mService.setParameters();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


}
