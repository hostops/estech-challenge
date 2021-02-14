# SystemDeviceResourceApi

All URIs are relative to *//localhost:8080/*

Method | HTTP request | Description
------------- | ------------- | -------------
[**createSystemDeviceUsingPOST**](SystemDeviceResourceApi.md#createSystemDeviceUsingPOST) | **POST** /api/system-devices | createSystemDevice
[**deleteSystemDeviceUsingDELETE**](SystemDeviceResourceApi.md#deleteSystemDeviceUsingDELETE) | **DELETE** /api/system-devices/{id} | deleteSystemDevice
[**getAllSystemDevicesUsingGET**](SystemDeviceResourceApi.md#getAllSystemDevicesUsingGET) | **GET** /api/system-devices | getAllSystemDevices
[**getSystemDeviceUsingGET**](SystemDeviceResourceApi.md#getSystemDeviceUsingGET) | **GET** /api/system-devices/{id} | getSystemDevice
[**updateSystemDeviceUsingPUT**](SystemDeviceResourceApi.md#updateSystemDeviceUsingPUT) | **PUT** /api/system-devices | updateSystemDevice

<a name="createSystemDeviceUsingPOST"></a>
# **createSystemDeviceUsingPOST**
> SystemDeviceDTO createSystemDeviceUsingPOST(body)

createSystemDevice

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.SystemDeviceResourceApi;


SystemDeviceResourceApi apiInstance = new SystemDeviceResourceApi();
SystemDeviceDTO body = new SystemDeviceDTO(); // SystemDeviceDTO | systemDeviceDTO
try {
    SystemDeviceDTO result = apiInstance.createSystemDeviceUsingPOST(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SystemDeviceResourceApi#createSystemDeviceUsingPOST");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**SystemDeviceDTO**](SystemDeviceDTO.md)| systemDeviceDTO |

### Return type

[**SystemDeviceDTO**](SystemDeviceDTO.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="deleteSystemDeviceUsingDELETE"></a>
# **deleteSystemDeviceUsingDELETE**
> deleteSystemDeviceUsingDELETE(id)

deleteSystemDevice

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.SystemDeviceResourceApi;


SystemDeviceResourceApi apiInstance = new SystemDeviceResourceApi();
Long id = 789L; // Long | id
try {
    apiInstance.deleteSystemDeviceUsingDELETE(id);
} catch (ApiException e) {
    System.err.println("Exception when calling SystemDeviceResourceApi#deleteSystemDeviceUsingDELETE");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **Long**| id |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

<a name="getAllSystemDevicesUsingGET"></a>
# **getAllSystemDevicesUsingGET**
> List&lt;SystemDeviceDTO&gt; getAllSystemDevicesUsingGET(eagerload, page, size, sort)

getAllSystemDevices

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.SystemDeviceResourceApi;


SystemDeviceResourceApi apiInstance = new SystemDeviceResourceApi();
Boolean eagerload = true; // Boolean | eagerload
Integer page = 56; // Integer | Page number of the requested page
Integer size = 56; // Integer | Size of a page
List<String> sort = Arrays.asList("sort_example"); // List<String> | Sorting criteria in the format: property(,asc|desc). Default sort order is ascending. Multiple sort criteria are supported.
try {
    List<SystemDeviceDTO> result = apiInstance.getAllSystemDevicesUsingGET(eagerload, page, size, sort);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SystemDeviceResourceApi#getAllSystemDevicesUsingGET");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **eagerload** | **Boolean**| eagerload | [optional]
 **page** | **Integer**| Page number of the requested page | [optional]
 **size** | **Integer**| Size of a page | [optional]
 **sort** | [**List&lt;String&gt;**](String.md)| Sorting criteria in the format: property(,asc|desc). Default sort order is ascending. Multiple sort criteria are supported. | [optional]

### Return type

[**List&lt;SystemDeviceDTO&gt;**](SystemDeviceDTO.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="getSystemDeviceUsingGET"></a>
# **getSystemDeviceUsingGET**
> SystemDeviceDTO getSystemDeviceUsingGET(id)

getSystemDevice

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.SystemDeviceResourceApi;


SystemDeviceResourceApi apiInstance = new SystemDeviceResourceApi();
Long id = 789L; // Long | id
try {
    SystemDeviceDTO result = apiInstance.getSystemDeviceUsingGET(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SystemDeviceResourceApi#getSystemDeviceUsingGET");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **Long**| id |

### Return type

[**SystemDeviceDTO**](SystemDeviceDTO.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="updateSystemDeviceUsingPUT"></a>
# **updateSystemDeviceUsingPUT**
> SystemDeviceDTO updateSystemDeviceUsingPUT(body)

updateSystemDevice

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.SystemDeviceResourceApi;


SystemDeviceResourceApi apiInstance = new SystemDeviceResourceApi();
SystemDeviceDTO body = new SystemDeviceDTO(); // SystemDeviceDTO | systemDeviceDTO
try {
    SystemDeviceDTO result = apiInstance.updateSystemDeviceUsingPUT(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SystemDeviceResourceApi#updateSystemDeviceUsingPUT");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**SystemDeviceDTO**](SystemDeviceDTO.md)| systemDeviceDTO |

### Return type

[**SystemDeviceDTO**](SystemDeviceDTO.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

