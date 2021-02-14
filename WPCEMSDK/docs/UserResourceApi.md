# UserResourceApi

All URIs are relative to *//localhost:8080/*

Method | HTTP request | Description
------------- | ------------- | -------------
[**createUserUsingPOST**](UserResourceApi.md#createUserUsingPOST) | **POST** /api/users | createUser
[**deleteUserUsingDELETE**](UserResourceApi.md#deleteUserUsingDELETE) | **DELETE** /api/users/{login} | deleteUser
[**getAllUsersUsingGET**](UserResourceApi.md#getAllUsersUsingGET) | **GET** /api/users | getAllUsers
[**getAuthoritiesUsingGET**](UserResourceApi.md#getAuthoritiesUsingGET) | **GET** /api/users/authorities | getAuthorities
[**getUserUsingGET**](UserResourceApi.md#getUserUsingGET) | **GET** /api/users/{login} | getUser
[**updateUserUsingPUT**](UserResourceApi.md#updateUserUsingPUT) | **PUT** /api/users | updateUser

<a name="createUserUsingPOST"></a>
# **createUserUsingPOST**
> User createUserUsingPOST(body)

createUser

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.UserResourceApi;


UserResourceApi apiInstance = new UserResourceApi();
UserDTO body = new UserDTO(); // UserDTO | userDTO
try {
    User result = apiInstance.createUserUsingPOST(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UserResourceApi#createUserUsingPOST");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**UserDTO**](UserDTO.md)| userDTO |

### Return type

[**User**](User.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="deleteUserUsingDELETE"></a>
# **deleteUserUsingDELETE**
> deleteUserUsingDELETE(login)

deleteUser

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.UserResourceApi;


UserResourceApi apiInstance = new UserResourceApi();
String login = "login_example"; // String | login
try {
    apiInstance.deleteUserUsingDELETE(login);
} catch (ApiException e) {
    System.err.println("Exception when calling UserResourceApi#deleteUserUsingDELETE");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **login** | **String**| login |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

<a name="getAllUsersUsingGET"></a>
# **getAllUsersUsingGET**
> List&lt;UserDTO&gt; getAllUsersUsingGET(page, size, sort)

getAllUsers

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.UserResourceApi;


UserResourceApi apiInstance = new UserResourceApi();
Integer page = 56; // Integer | Page number of the requested page
Integer size = 56; // Integer | Size of a page
List<String> sort = Arrays.asList("sort_example"); // List<String> | Sorting criteria in the format: property(,asc|desc). Default sort order is ascending. Multiple sort criteria are supported.
try {
    List<UserDTO> result = apiInstance.getAllUsersUsingGET(page, size, sort);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UserResourceApi#getAllUsersUsingGET");
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

[**List&lt;UserDTO&gt;**](UserDTO.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="getAuthoritiesUsingGET"></a>
# **getAuthoritiesUsingGET**
> List&lt;String&gt; getAuthoritiesUsingGET()

getAuthorities

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.UserResourceApi;


UserResourceApi apiInstance = new UserResourceApi();
try {
    List<String> result = apiInstance.getAuthoritiesUsingGET();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UserResourceApi#getAuthoritiesUsingGET");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

**List&lt;String&gt;**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="getUserUsingGET"></a>
# **getUserUsingGET**
> UserDTO getUserUsingGET(login)

getUser

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.UserResourceApi;


UserResourceApi apiInstance = new UserResourceApi();
String login = "login_example"; // String | login
try {
    UserDTO result = apiInstance.getUserUsingGET(login);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UserResourceApi#getUserUsingGET");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **login** | **String**| login |

### Return type

[**UserDTO**](UserDTO.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="updateUserUsingPUT"></a>
# **updateUserUsingPUT**
> UserDTO updateUserUsingPUT(body)

updateUser

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.UserResourceApi;


UserResourceApi apiInstance = new UserResourceApi();
UserDTO body = new UserDTO(); // UserDTO | userDTO
try {
    UserDTO result = apiInstance.updateUserUsingPUT(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UserResourceApi#updateUserUsingPUT");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**UserDTO**](UserDTO.md)| userDTO |

### Return type

[**UserDTO**](UserDTO.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

