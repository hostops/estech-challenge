package com.danfoss.wpcem;

import io.swagger.client.ApiClient;
import io.swagger.client.ApiException;
import io.swagger.client.api.SystemDeviceDataResourceApi;
import io.swagger.client.api.SystemUnitResourceApi;
import io.swagger.client.api.UserJwtControllerApi;
import io.swagger.client.model.*;

import org.threeten.bp.OffsetDateTime;
import org.threeten.bp.ZoneId;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class WPSCEMSystemUnit {
    private static ApiClient authenticatedApiClient;
    private static List<SystemDeviceDTO> systemDevices;
    private static Integer size;

    public static void main(String[] args) {
        // Login
        try {
            String user = args[0];
            String pass = args[1];
            if (user == null || user.isEmpty() || pass == null || pass.isEmpty()) {
                return;
            }
            LoginVM login = new LoginVM();
            login.setPassword(user);
            login.setUsername(pass);

            login.setRememberMe(Boolean.TRUE);
            ApiClient client = new ApiClient();
            if (args.length >= 3) {
                client.setBasePath(args[2]);
            }
            UserJwtControllerApi authApi = new UserJwtControllerApi(client);
            JWTToken token = authApi.authorizeUsingPOST(login);
            authenticatedApiClient = new ApiClient();
            if (args.length >= 3) {
                authenticatedApiClient.setBasePath(args[2]);
            }
            authenticatedApiClient.setAccessToken(token.getIdToken());
            System.out.println("Loged in as " + user);
        } catch (NullPointerException | IndexOutOfBoundsException | ApiException ex) {
            if (ex instanceof ApiException) {
                switch (((ApiException) ex).getCode()) {
                    case 401:
                        System.out.println("User name or password is invalid. Please try again.");
                        break;
                    default:
                        System.out.println("Error:" + ex.getMessage());

                }
            }
            System.exit(-1);
        }

        // Send and receive data every 5 seconds.
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {

            private SystemUnitDTO systemUnit;

            @Override
            public void run() {
                try {
                    SystemUnitResourceApi systemUnitResourceApi = new SystemUnitResourceApi(authenticatedApiClient);
                    systemUnit = systemUnitResourceApi.getAllSystemUnitsUsingGET(true, 0, 1, new ArrayList<>()).get(0);
                    systemDevices = systemUnit.getSystemDevices();

                    for (SystemDeviceDTO systemDevice : systemDevices) {
                        SystemDeviceDataDTO systemDeviceDataDTO = new SystemDeviceDataDTO();
                        systemDeviceDataDTO.setDataType(SystemDeviceDataDTO.DataTypeEnum.REAL);
                        systemDeviceDataDTO.setDataValue(Math.random() * 100);
                        systemDeviceDataDTO.setDeviceSerialNumber(systemDevice.getSerialNumber());
                        systemDeviceDataDTO.setName("TEMPERATURE");
                        systemDeviceDataDTO.setDeviceId(systemDevice.getId());
                        systemDeviceDataDTO.setUnit("°C");
                        systemDeviceDataDTO.setTimestamp(OffsetDateTime.now(ZoneId.of("UTC")));
                        SystemDeviceDataResourceApi systemDeviceDataResourceApi = new SystemDeviceDataResourceApi(authenticatedApiClient);
                        systemDeviceDataResourceApi.createSystemDeviceDataUsingPOST(systemDeviceDataDTO);

                        systemDeviceDataDTO.setDataValue(Math.random() * 100);
                        systemDeviceDataDTO.setName("PRESSURE");
                        systemDeviceDataDTO.setUnit("bar");
                        systemDeviceDataResourceApi.createSystemDeviceDataUsingPOST(systemDeviceDataDTO);

                        systemDeviceDataDTO.setDataValue(Math.random() * 100);
                        systemDeviceDataDTO.setName("FLOW");
                        systemDeviceDataDTO.setUnit("m³/h");
                        systemDeviceDataResourceApi.createSystemDeviceDataUsingPOST(systemDeviceDataDTO);
                    }
                    if (systemDevices != null) {
                        if(size==null) {
                            size = systemDevices.size();
                        }
                        if (size < systemDevices.size()) {
                            ProcessBuilder pb = new ProcessBuilder("echo", "A");
                            pb.redirectOutput(new File("/dev/ttyACM0"));
                            Process p = pb.start();
                            p.waitFor();
                        }
                        size = systemDevices.size();
                    }
                } catch (ApiException | IOException | InterruptedException ex) {
                    System.err.println(ex.getMessage());
                }

            }
        }, 5000, 5000);
    }

}
