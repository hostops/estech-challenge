/*
 * WPCEM API
 * WPCEM API documentation
 *
 * OpenAPI spec version: 0.0.1
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package io.swagger.client.api;

import io.swagger.client.ApiException;
import io.swagger.client.model.SystemDeviceDataDTO;
import org.junit.Test;
import org.junit.Ignore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for SystemDeviceDataResourceApi
 */
@Ignore
public class SystemDeviceDataResourceApiTest {

    private final SystemDeviceDataResourceApi api = new SystemDeviceDataResourceApi();

    /**
     * createSystemDeviceData
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void createSystemDeviceDataUsingPOSTTest() throws ApiException {
        SystemDeviceDataDTO body = null;
        SystemDeviceDataDTO response = api.createSystemDeviceDataUsingPOST(body);

        // TODO: test validations
    }
    /**
     * deleteSystemDeviceData
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void deleteSystemDeviceDataUsingDELETETest() throws ApiException {
        Long id = null;
        api.deleteSystemDeviceDataUsingDELETE(id);

        // TODO: test validations
    }
    /**
     * getAllSystemDeviceData
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getAllSystemDeviceDataUsingGETTest() throws ApiException {
        Integer page = null;
        Integer size = null;
        List<String> sort = null;
        List<SystemDeviceDataDTO> response = api.getAllSystemDeviceDataUsingGET(page, size, sort);

        // TODO: test validations
    }
    /**
     * getSystemDeviceData
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getSystemDeviceDataUsingGETTest() throws ApiException {
        Long id = null;
        SystemDeviceDataDTO response = api.getSystemDeviceDataUsingGET(id);

        // TODO: test validations
    }
    /**
     * updateSystemDeviceData
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void updateSystemDeviceDataUsingPUTTest() throws ApiException {
        SystemDeviceDataDTO body = null;
        SystemDeviceDataDTO response = api.updateSystemDeviceDataUsingPUT(body);

        // TODO: test validations
    }
}
