# AccountResourceApi

All URIs are relative to *//localhost:8080/*

Method | HTTP request | Description
------------- | ------------- | -------------
[**activateAccountUsingGET**](AccountResourceApi.md#activateAccountUsingGET) | **GET** /api/activate | activateAccount
[**changePasswordUsingPOST**](AccountResourceApi.md#changePasswordUsingPOST) | **POST** /api/account/change-password | changePassword
[**finishPasswordResetUsingPOST**](AccountResourceApi.md#finishPasswordResetUsingPOST) | **POST** /api/account/reset-password/finish | finishPasswordReset
[**getAccountUsingGET**](AccountResourceApi.md#getAccountUsingGET) | **GET** /api/account | getAccount
[**isAuthenticatedUsingGET**](AccountResourceApi.md#isAuthenticatedUsingGET) | **GET** /api/authenticate | isAuthenticated
[**registerAccountUsingPOST**](AccountResourceApi.md#registerAccountUsingPOST) | **POST** /api/register | registerAccount
[**requestPasswordResetUsingPOST**](AccountResourceApi.md#requestPasswordResetUsingPOST) | **POST** /api/account/reset-password/init | requestPasswordReset
[**saveAccountUsingPOST**](AccountResourceApi.md#saveAccountUsingPOST) | **POST** /api/account | saveAccount

<a name="activateAccountUsingGET"></a>
# **activateAccountUsingGET**
> activateAccountUsingGET(key)

activateAccount

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AccountResourceApi;


AccountResourceApi apiInstance = new AccountResourceApi();
String key = "key_example"; // String | key
try {
    apiInstance.activateAccountUsingGET(key);
} catch (ApiException e) {
    System.err.println("Exception when calling AccountResourceApi#activateAccountUsingGET");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **key** | **String**| key |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

<a name="changePasswordUsingPOST"></a>
# **changePasswordUsingPOST**
> changePasswordUsingPOST(body)

changePassword

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AccountResourceApi;


AccountResourceApi apiInstance = new AccountResourceApi();
PasswordChangeDTO body = new PasswordChangeDTO(); // PasswordChangeDTO | passwordChangeDto
try {
    apiInstance.changePasswordUsingPOST(body);
} catch (ApiException e) {
    System.err.println("Exception when calling AccountResourceApi#changePasswordUsingPOST");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**PasswordChangeDTO**](PasswordChangeDTO.md)| passwordChangeDto |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: Not defined

<a name="finishPasswordResetUsingPOST"></a>
# **finishPasswordResetUsingPOST**
> finishPasswordResetUsingPOST(body)

finishPasswordReset

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AccountResourceApi;


AccountResourceApi apiInstance = new AccountResourceApi();
KeyAndPasswordVM body = new KeyAndPasswordVM(); // KeyAndPasswordVM | keyAndPassword
try {
    apiInstance.finishPasswordResetUsingPOST(body);
} catch (ApiException e) {
    System.err.println("Exception when calling AccountResourceApi#finishPasswordResetUsingPOST");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**KeyAndPasswordVM**](KeyAndPasswordVM.md)| keyAndPassword |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: Not defined

<a name="getAccountUsingGET"></a>
# **getAccountUsingGET**
> UserDTO getAccountUsingGET()

getAccount

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AccountResourceApi;


AccountResourceApi apiInstance = new AccountResourceApi();
try {
    UserDTO result = apiInstance.getAccountUsingGET();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AccountResourceApi#getAccountUsingGET");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**UserDTO**](UserDTO.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="isAuthenticatedUsingGET"></a>
# **isAuthenticatedUsingGET**
> String isAuthenticatedUsingGET()

isAuthenticated

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AccountResourceApi;


AccountResourceApi apiInstance = new AccountResourceApi();
try {
    String result = apiInstance.isAuthenticatedUsingGET();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AccountResourceApi#isAuthenticatedUsingGET");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="registerAccountUsingPOST"></a>
# **registerAccountUsingPOST**
> registerAccountUsingPOST(body)

registerAccount

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AccountResourceApi;


AccountResourceApi apiInstance = new AccountResourceApi();
ManagedUserVM body = new ManagedUserVM(); // ManagedUserVM | managedUserVM
try {
    apiInstance.registerAccountUsingPOST(body);
} catch (ApiException e) {
    System.err.println("Exception when calling AccountResourceApi#registerAccountUsingPOST");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**ManagedUserVM**](ManagedUserVM.md)| managedUserVM |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: Not defined

<a name="requestPasswordResetUsingPOST"></a>
# **requestPasswordResetUsingPOST**
> requestPasswordResetUsingPOST(body)

requestPasswordReset

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AccountResourceApi;


AccountResourceApi apiInstance = new AccountResourceApi();
String body = "body_example"; // String | mail
try {
    apiInstance.requestPasswordResetUsingPOST(body);
} catch (ApiException e) {
    System.err.println("Exception when calling AccountResourceApi#requestPasswordResetUsingPOST");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**String**](String.md)| mail |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: Not defined

<a name="saveAccountUsingPOST"></a>
# **saveAccountUsingPOST**
> saveAccountUsingPOST(body)

saveAccount

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AccountResourceApi;


AccountResourceApi apiInstance = new AccountResourceApi();
UserDTO body = new UserDTO(); // UserDTO | userDTO
try {
    apiInstance.saveAccountUsingPOST(body);
} catch (ApiException e) {
    System.err.println("Exception when calling AccountResourceApi#saveAccountUsingPOST");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**UserDTO**](UserDTO.md)| userDTO |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: Not defined

