package climatesys;

import java.net.*;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Climate {
    public static final String keyAPI = "43751621f77846a39d1194848220612";
    public static final String requestCurrentUrl = "http://api.weatherapi.com/v1/current.json";
    public static final String requestFutureUrl = "http://api.weatherapi.com/v1/forecast.json";
    public String position = "Seattle";
    public String climateNow = "";
    public String climateFuture = "";

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getClimateNow() {
        return climateNow;
    }

    public void setClimateNow() {
        this.climateNow = httpRequest(true);
    }

    public String getClimateFuture() {
        return climateFuture;
    }

    public void setClimateFuture() {
        this.climateFuture = httpRequest(false);
    }

    private String httpRequest(boolean nowOrFuture) {
        StringBuffer buffer = new StringBuffer();
        try {
            URL url;
            for(int i = 0; i < position.length(); i++) {
                if(position.charAt(i) == ' ') {
                    position = position.substring(0, i) + "\t" + position.substring(i + 1);
                }
            }
            if(nowOrFuture) url = new URL(requestCurrentUrl + "?key=" + keyAPI + "&q=" + position + "&aqi=yes");
            else url = new URL(requestFutureUrl + "?key=" + keyAPI + "&q=" + position + "&days=2&aqi=no&alerts=no");
            // Open http connect
            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
            httpUrlConn.setDoInput(true);
            httpUrlConn.setRequestMethod("GET");
            httpUrlConn.connect();
            // Get input
            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            // BufferReader to buffer
            String str = null;
            while((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            // Close stream
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            inputStream = null;
            // Disconnect
            httpUrlConn.disconnect();
        } catch (Exception e) {
            return "";
        }
        return buffer.toString();
    }
}