# SystemDeviceDataResourceApi

All URIs are relative to *//localhost:8080/*

Method | HTTP request | Description
------------- | ------------- | -------------
[**createSystemDeviceDataUsingPOST**](SystemDeviceDataResourceApi.md#createSystemDeviceDataUsingPOST) | **POST** /api/system-device-data | createSystemDeviceData
[**deleteSystemDeviceDataUsingDELETE**](SystemDeviceDataResourceApi.md#deleteSystemDeviceDataUsingDELETE) | **DELETE** /api/system-device-data/{id} | deleteSystemDeviceData
[**getAllSystemDeviceDataUsingGET**](SystemDeviceDataResourceApi.md#getAllSystemDeviceDataUsingGET) | **GET** /api/system-device-data | getAllSystemDeviceData
[**getSystemDeviceDataUsingGET**](SystemDeviceDataResourceApi.md#getSystemDeviceDataUsingGET) | **GET** /api/system-device-data/{id} | getSystemDeviceData
[**updateSystemDeviceDataUsingPUT**](SystemDeviceDataResourceApi.md#updateSystemDeviceDataUsingPUT) | **PUT** /api/system-device-data | updateSystemDeviceData

<a name="createSystemDeviceDataUsingPOST"></a>
# **createSystemDeviceDataUsingPOST**
> SystemDeviceDataDTO createSystemDeviceDataUsingPOST(body)

createSystemDeviceData

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.SystemDeviceDataResourceApi;


SystemDeviceDataResourceApi apiInstance = new SystemDeviceDataResourceApi();
SystemDeviceDataDTO body = new SystemDeviceDataDTO(); // SystemDeviceDataDTO | systemDeviceDataDTO
try {
    SystemDeviceDataDTO result = apiInstance.createSystemDeviceDataUsingPOST(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SystemDeviceDataResourceApi#createSystemDeviceDataUsingPOST");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**SystemDeviceDataDTO**](SystemDeviceDataDTO.md)| systemDeviceDataDTO |

### Return type

[**SystemDeviceDataDTO**](SystemDeviceDataDTO.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="deleteSystemDeviceDataUsingDELETE"></a>
# **deleteSystemDeviceDataUsingDELETE**
> deleteSystemDeviceDataUsingDELETE(id)

deleteSystemDeviceData

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.SystemDeviceDataResourceApi;


SystemDeviceDataResourceApi apiInstance = new SystemDeviceDataResourceApi();
Long id = 789L; // Long | id
try {
    apiInstance.deleteSystemDeviceDataUsingDELETE(id);
} catch (ApiException e) {
    System.err.println("Exception when calling SystemDeviceDataResourceApi#deleteSystemDeviceDataUsingDELETE");
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

<a name="getAllSystemDeviceDataUsingGET"></a>
# **getAllSystemDeviceDataUsingGET**
> List&lt;SystemDeviceDataDTO&gt; getAllSystemDeviceDataUsingGET(page, size, sort)

getAllSystemDeviceData

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.SystemDeviceDataResourceApi;


SystemDeviceDataResourceApi apiInstance = new SystemDeviceDataResourceApi();
Integer page = 56; // Integer | Page number of the requested page
Integer size = 56; // Integer | Size of a page
List<String> sort = Arrays.asList("sort_example"); // List<String> | Sorting criteria in the format: property(,asc|desc). Default sort order is ascending. Multiple sort criteria are supported.
try {
    List<SystemDeviceDataDTO> result = apiInstance.getAllSystemDeviceDataUsingGET(page, size, sort);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SystemDeviceDataResourceApi#getAllSystemDeviceDataUsingGET");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **page** | **Integer**| Page number of the requested page | [optional]
 **size** | **Integer**| Size of a page | [optional]
 **sort** | [**List&lt;String&gt;**](String.md)| Sorting criteria in the format: property(,asc|desc). Default sort order is ascending. Multiple sort criteria are supported. | [optional]

### Return type

[**List&lt;SystemDeviceDataDTO&gt;**](SystemDeviceDataDTO.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="getSystemDeviceDataUsingGET"></a>
# **getSystemDeviceDataUsingGET**
> SystemDeviceDataDTO getSystemDeviceDataUsingGET(id)

getSystemDeviceData

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.SystemDeviceDataResourceApi;


SystemDeviceDataResourceApi apiInstance = new SystemDeviceDataResourceApi();
Long id = 789L; // Long | id
try {
    SystemDeviceDataDTO result = apiInstance.getSystemDeviceDataUsingGET(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SystemDeviceDataResourceApi#getSystemDeviceDataUsingGET");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **Long**| id |

### Return type

[**SystemDeviceDataDTO**](SystemDeviceDataDTO.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="updateSystemDeviceDataUsingPUT"></a>
# **updateSystemDeviceDataUsingPUT**
> SystemDeviceDataDTO updateSystemDeviceDataUsingPUT(body)

updateSystemDeviceData

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.SystemDeviceDataResourceApi;


SystemDeviceDataResourceApi apiInstance = new SystemDeviceDataResourceApi();
SystemDeviceDataDTO body = new SystemDeviceDataDTO(); // SystemDeviceDataDTO | systemDeviceDataDTO
try {
    SystemDeviceDataDTO result = apiInstance.updateSystemDeviceDataUsingPUT(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SystemDeviceDataResourceApi#updateSystemDeviceDataUsingPUT");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**SystemDeviceDataDTO**](SystemDeviceDataDTO.md)| systemDeviceDataDTO |

### Return type

[**SystemDeviceDataDTO**](SystemDeviceDataDTO.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

