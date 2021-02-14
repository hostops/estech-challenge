# SystemUnitResourceApi

All URIs are relative to *//localhost:8080/*

Method | HTTP request | Description
------------- | ------------- | -------------
[**createSystemUnitUsingPOST**](SystemUnitResourceApi.md#createSystemUnitUsingPOST) | **POST** /api/system-units | createSystemUnit
[**deleteSystemUnitUsingDELETE**](SystemUnitResourceApi.md#deleteSystemUnitUsingDELETE) | **DELETE** /api/system-units/{id} | deleteSystemUnit
[**getAllSystemUnitsUsingGET**](SystemUnitResourceApi.md#getAllSystemUnitsUsingGET) | **GET** /api/system-units | getAllSystemUnits
[**getSystemUnitUsingGET**](SystemUnitResourceApi.md#getSystemUnitUsingGET) | **GET** /api/system-units/{id} | getSystemUnit
[**updateSystemUnitUsingPUT**](SystemUnitResourceApi.md#updateSystemUnitUsingPUT) | **PUT** /api/system-units | updateSystemUnit

<a name="createSystemUnitUsingPOST"></a>
# **createSystemUnitUsingPOST**
> SystemUnitDTO createSystemUnitUsingPOST(body)

createSystemUnit

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.SystemUnitResourceApi;


SystemUnitResourceApi apiInstance = new SystemUnitResourceApi();
SystemUnitDTO body = new SystemUnitDTO(); // SystemUnitDTO | systemUnitDTO
try {
    SystemUnitDTO result = apiInstance.createSystemUnitUsingPOST(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SystemUnitResourceApi#createSystemUnitUsingPOST");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**SystemUnitDTO**](SystemUnitDTO.md)| systemUnitDTO |

### Return type

[**SystemUnitDTO**](SystemUnitDTO.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="deleteSystemUnitUsingDELETE"></a>
# **deleteSystemUnitUsingDELETE**
> deleteSystemUnitUsingDELETE(id)

deleteSystemUnit

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.SystemUnitResourceApi;


SystemUnitResourceApi apiInstance = new SystemUnitResourceApi();
Long id = 789L; // Long | id
try {
    apiInstance.deleteSystemUnitUsingDELETE(id);
} catch (ApiException e) {
    System.err.println("Exception when calling SystemUnitResourceApi#deleteSystemUnitUsingDELETE");
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

<a name="getAllSystemUnitsUsingGET"></a>
# **getAllSystemUnitsUsingGET**
> List&lt;SystemUnitDTO&gt; getAllSystemUnitsUsingGET(eagerload, page, size, sort)

getAllSystemUnits

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.SystemUnitResourceApi;


SystemUnitResourceApi apiInstance = new SystemUnitResourceApi();
Boolean eagerload = true; // Boolean | eagerload
Integer page = 56; // Integer | Page number of the requested page
Integer size = 56; // Integer | Size of a page
List<String> sort = Arrays.asList("sort_example"); // List<String> | Sorting criteria in the format: property(,asc|desc). Default sort order is ascending. Multiple sort criteria are supported.
try {
    List<SystemUnitDTO> result = apiInstance.getAllSystemUnitsUsingGET(eagerload, page, size, sort);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SystemUnitResourceApi#getAllSystemUnitsUsingGET");
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

[**List&lt;SystemUnitDTO&gt;**](SystemUnitDTO.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="getSystemUnitUsingGET"></a>
# **getSystemUnitUsingGET**
> SystemUnitDTO getSystemUnitUsingGET(id)

getSystemUnit

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.SystemUnitResourceApi;


SystemUnitResourceApi apiInstance = new SystemUnitResourceApi();
Long id = 789L; // Long | id
try {
    SystemUnitDTO result = apiInstance.getSystemUnitUsingGET(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SystemUnitResourceApi#getSystemUnitUsingGET");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **Long**| id |

### Return type

[**SystemUnitDTO**](SystemUnitDTO.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="updateSystemUnitUsingPUT"></a>
# **updateSystemUnitUsingPUT**
> SystemUnitDTO updateSystemUnitUsingPUT(body)

updateSystemUnit

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.SystemUnitResourceApi;


SystemUnitResourceApi apiInstance = new SystemUnitResourceApi();
SystemUnitDTO body = new SystemUnitDTO(); // SystemUnitDTO | systemUnitDTO
try {
    SystemUnitDTO result = apiInstance.updateSystemUnitUsingPUT(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SystemUnitResourceApi#updateSystemUnitUsingPUT");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**SystemUnitDTO**](SystemUnitDTO.md)| systemUnitDTO |

### Return type

[**SystemUnitDTO**](SystemUnitDTO.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

