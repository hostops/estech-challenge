# UserJwtControllerApi

All URIs are relative to *//localhost:8080/*

Method | HTTP request | Description
------------- | ------------- | -------------
[**authorizeUsingPOST**](UserJwtControllerApi.md#authorizeUsingPOST) | **POST** /api/authenticate | authorize

<a name="authorizeUsingPOST"></a>
# **authorizeUsingPOST**
> JWTToken authorizeUsingPOST(body)

authorize

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.UserJwtControllerApi;


UserJwtControllerApi apiInstance = new UserJwtControllerApi();
LoginVM body = new LoginVM(); // LoginVM | loginVM
try {
    JWTToken result = apiInstance.authorizeUsingPOST(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UserJwtControllerApi#authorizeUsingPOST");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**LoginVM**](LoginVM.md)| loginVM |

### Return type

[**JWTToken**](JWTToken.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

