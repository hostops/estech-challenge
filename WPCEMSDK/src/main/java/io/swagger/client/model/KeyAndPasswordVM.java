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

package io.swagger.client.model;

import java.util.Objects;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.IOException;

/**
 * KeyAndPasswordVM
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2019-04-06T16:15:45.073+02:00[Europe/Ljubljana]")public class KeyAndPasswordVM {

  @SerializedName("key")
  private String key = null;

  @SerializedName("newPassword")
  private String newPassword = null;
  public KeyAndPasswordVM key(String key) {
    this.key = key;
    return this;
  }

  

  /**
  * Get key
  * @return key
  **/
  @Schema(description = "")
  public String getKey() {
    return key;
  }
  public void setKey(String key) {
    this.key = key;
  }
  public KeyAndPasswordVM newPassword(String newPassword) {
    this.newPassword = newPassword;
    return this;
  }

  

  /**
  * Get newPassword
  * @return newPassword
  **/
  @Schema(description = "")
  public String getNewPassword() {
    return newPassword;
  }
  public void setNewPassword(String newPassword) {
    this.newPassword = newPassword;
  }
  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    KeyAndPasswordVM keyAndPasswordVM = (KeyAndPasswordVM) o;
    return Objects.equals(this.key, keyAndPasswordVM.key) &&
        Objects.equals(this.newPassword, keyAndPasswordVM.newPassword);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(key, newPassword);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class KeyAndPasswordVM {\n");
    
    sb.append("    key: ").append(toIndentedString(key)).append("\n");
    sb.append("    newPassword: ").append(toIndentedString(newPassword)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}
