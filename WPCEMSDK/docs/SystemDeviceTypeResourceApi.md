# SystemDeviceTypeResourceApi

All URIs are relative to *//localhost:8080/*

Method | HTTP request | Description
------------- | ------------- | -------------
[**createSystemDeviceTypeUsingPOST**](SystemDeviceTypeResourceApi.md#createSystemDeviceTypeUsingPOST) | **POST** /api/system-device-types | createSystemDeviceType
[**deleteSystemDeviceTypeUsingDELETE**](SystemDeviceTypeResourceApi.md#deleteSystemDeviceTypeUsingDELETE) | **DELETE** /api/system-device-types/{id} | deleteSystemDeviceType
[**getAllSystemDeviceTypesUsingGET**](SystemDeviceTypeResourceApi.md#getAllSystemDeviceTypesUsingGET) | **GET** /api/system-device-types | getAllSystemDeviceTypes
[**getSystemDeviceTypeUsingGET**](SystemDeviceTypeResourceApi.md#getSystemDeviceTypeUsingGET) | **GET** /api/system-device-types/{id} | getSystemDeviceType
[**updateSystemDeviceTypeUsingPUT**](SystemDeviceTypeResourceApi.md#updateSystemDeviceTypeUsingPUT) | **PUT** /api/system-device-types | updateSystemDeviceType

<a name="createSystemDeviceTypeUsingPOST"></a>
# **createSystemDeviceTypeUsingPOST**
> SystemDeviceTypeDTO createSystemDeviceTypeUsingPOST(body)

createSystemDeviceType

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.SystemDeviceTypeResourceApi;


SystemDeviceTypeResourceApi apiInstance = new SystemDeviceTypeResourceApi();
SystemDeviceTypeDTO body = new SystemDeviceTypeDTO(); // SystemDeviceTypeDTO | systemDeviceTypeDTO
try {
    SystemDeviceTypeDTO result = apiInstance.createSystemDeviceTypeUsingPOST(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SystemDeviceTypeResourceApi#createSystemDeviceTypeUsingPOST");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**SystemDeviceTypeDTO**](SystemDeviceTypeDTO.md)| systemDeviceTypeDTO |

### Return type

[**SystemDeviceTypeDTO**](SystemDeviceTypeDTO.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="deleteSystemDeviceTypeUsingDELETE"></a>
# **deleteSystemDeviceTypeUsingDELETE**
> deleteSystemDeviceTypeUsingDELETE(id)

deleteSystemDeviceType

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.SystemDeviceTypeResourceApi;


SystemDeviceTypeResourceApi apiInstance = new SystemDeviceTypeResourceApi();
Long id = 789L; // Long | id
try {
    apiInstance.deleteSystemDeviceTypeUsingDELETE(id);
} catch (ApiException e) {
    System.err.println("Exception when calling SystemDeviceTypeResourceApi#deleteSystemDeviceTypeUsingDELETE");
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

<a name="getAllSystemDeviceTypesUsingGET"></a>
# **getAllSystemDeviceTypesUsingGET**
> List&lt;SystemDeviceTypeDTO&gt; getAllSystemDeviceTypesUsingGET(page, size, sort)

getAllSystemDeviceTypes

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.SystemDeviceTypeResourceApi;


SystemDeviceTypeResourceApi apiInstance = new SystemDeviceTypeResourceApi();
Integer page = 56; // Integer | Page number of the requested page
Integer size = 56; // Integer | Size of a page
List<String> sort = Arrays.asList("sort_example"); // List<String> | Sorting criteria in the format: property(,asc|desc). Default sort order is ascending. Multiple sort criteria are supported.
try {
    List<SystemDeviceTypeDTO> result = apiInstance.getAllSystemDeviceTypesUsingGET(page, size, sort);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SystemDeviceTypeResourceApi#getAllSystemDeviceTypesUsingGET");
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

[**List&lt;SystemDeviceTypeDTO&gt;**](SystemDeviceTypeDTO.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="getSystemDeviceTypeUsingGET"></a>
# **getSystemDeviceTypeUsingGET**
> SystemDeviceTypeDTO getSystemDeviceTypeUsingGET(id)

getSystemDeviceType

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.SystemDeviceTypeResourceApi;


SystemDeviceTypeResourceApi apiInstance = new SystemDeviceTypeResourceApi();
Long id = 789L; // Long | id
try {
    SystemDeviceTypeDTO result = apiInstance.getSystemDeviceTypeUsingGET(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SystemDeviceTypeResourceApi#getSystemDeviceTypeUsingGET");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **Long**| id |

### Return type

[**SystemDeviceTypeDTO**](SystemDeviceTypeDTO.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="updateSystemDeviceTypeUsingPUT"></a>
# **updateSystemDeviceTypeUsingPUT**
> SystemDeviceTypeDTO updateSystemDeviceTypeUsingPUT(body)

updateSystemDeviceType

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.SystemDeviceTypeResourceApi;


SystemDeviceTypeResourceApi apiInstance = new SystemDeviceTypeResourceApi();
SystemDeviceTypeDTO body = new SystemDeviceTypeDTO(); // SystemDeviceTypeDTO | systemDeviceTypeDTO
try {
    SystemDeviceTypeDTO result = apiInstance.updateSystemDeviceTypeUsingPUT(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SystemDeviceTypeResourceApi#updateSystemDeviceTypeUsingPUT");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**SystemDeviceTypeDTO**](SystemDeviceTypeDTO.md)| systemDeviceTypeDTO |

### Return type

[**SystemDeviceTypeDTO**](SystemDeviceTypeDTO.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

