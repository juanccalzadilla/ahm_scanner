package scanR;
import android.view.KeyEvent;

interface IScanServer {

     boolean initScanner();

     boolean scanProcess(in KeyEvent event);

     int getReaderState();

     void startRead();

     void stopRead();

     void closeReader();

     int setStringParam(int key, String value);

     int setIntParam(int key , int value);

     String getStringParam(int key);

     int getIntParam(int key);

     void setScanSwitchLeft(boolean flag);
     boolean getScanSwitchLeft();

     void setScanSwitchMiddle(boolean flag);
     boolean getScanSwitchMiddle();

     void setScanSwitchRight(boolean flag);
     boolean getScanSwitchRight();

     void setScanOperatingMode(int state);
     int getScanOperatingMode();

     void setScanSound(boolean flag);
     boolean getScanSound();

     void setScanVibrate(boolean flag);
     boolean getScanVibrate();

     void setBarcodeReceiveModel(int state);
     int getBarcodeReceiveModel();

     void setScanDelaySetting(int state);
     int getScanDelaySetting();

     void setScanDelay(float delay);
     float getScanDelay();

     void setBarcodeSeparator(int state);
     int getBarcodeSeparator();

     void setBarcodePrefixState(boolean flag);
     boolean getBarcodePrefixState();

     void setBarcodePrefix(String prefix);
     String getBarcodePrefix();

     void setBarcodeSuffixState(boolean flag);
     boolean getBarcodeSuffixState();

     void setBarcodeSuffix(String suffix);
     String getBarcodeSuffix();

     Map getBarCode1DType();

     Map getBarCode2DType();

     Map getBarCodePostType();

     int setCM30ScanParam(String paramName, String enable, String status);

     Map getCM30CodeMoreMap(String codeName);

     String getCM30ScanParam(String paramName, String value);

     String getCM30CodeFullName(String codeName);

     void setParameters();
}
