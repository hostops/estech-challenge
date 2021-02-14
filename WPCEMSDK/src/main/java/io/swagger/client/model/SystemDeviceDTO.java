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
import io.swagger.client.model.SystemDeviceDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * SystemDeviceDTO
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2019-04-06T16:15:45.073+02:00[Europe/Ljubljana]")public class SystemDeviceDTO {

  @SerializedName("connectionsUpcomings")
  private List<SystemDeviceDTO> connectionsUpcomings = null;

  @SerializedName("deviceConfiguration")
  private String deviceConfiguration = null;

  @SerializedName("id")
  private Long id = null;

  @SerializedName("serialNumber")
  private String serialNumber = null;

  @SerializedName("systemUnitId")
  private Long systemUnitId = null;

  @SerializedName("systemUnitName")
  private String systemUnitName = null;

  @SerializedName("typeId")
  private Long typeId = null;

  @SerializedName("typeName")
  private String typeName = null;
  public SystemDeviceDTO connectionsUpcomings(List<SystemDeviceDTO> connectionsUpcomings) {
    this.connectionsUpcomings = connectionsUpcomings;
    return this;
  }

  public SystemDeviceDTO addConnectionsUpcomingsItem(SystemDeviceDTO connectionsUpcomingsItem) {
    if (this.connectionsUpcomings == null) {
      this.connectionsUpcomings = new ArrayList<SystemDeviceDTO>();
    }
    this.connectionsUpcomings.add(connectionsUpcomingsItem);
    return this;
  }

  /**
  * Get connectionsUpcomings
  * @return connectionsUpcomings
  **/
  @Schema(description = "")
  public List<SystemDeviceDTO> getConnectionsUpcomings() {
    return connectionsUpcomings;
  }
  public void setConnectionsUpcomings(List<SystemDeviceDTO> connectionsUpcomings) {
    this.connectionsUpcomings = connectionsUpcomings;
  }
  public SystemDeviceDTO deviceConfiguration(String deviceConfiguration) {
    this.deviceConfiguration = deviceConfiguration;
    return this;
  }

  

  /**
  * Get deviceConfiguration
  * @return deviceConfiguration
  **/
  @Schema(description = "")
  public String getDeviceConfiguration() {
    return deviceConfiguration;
  }
  public void setDeviceConfiguration(String deviceConfiguration) {
    this.deviceConfiguration = deviceConfiguration;
  }
  public SystemDeviceDTO id(Long id) {
    this.id = id;
    return this;
  }

  

  /**
  * Get id
  * @return id
  **/
  @Schema(description = "")
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public SystemDeviceDTO serialNumber(String serialNumber) {
    this.serialNumber = serialNumber;
    return this;
  }

  

  /**
  * Get serialNumber
  * @return serialNumber
  **/
  @Schema(description = "")
  public String getSerialNumber() {
    return serialNumber;
  }
  public void setSerialNumber(String serialNumber) {
    this.serialNumber = serialNumber;
  }
  public SystemDeviceDTO systemUnitId(Long systemUnitId) {
    this.systemUnitId = systemUnitId;
    return this;
  }

  

  /**
  * Get systemUnitId
  * @return systemUnitId
  **/
  @Schema(description = "")
  public Long getSystemUnitId() {
    return systemUnitId;
  }
  public void setSystemUnitId(Long systemUnitId) {
    this.systemUnitId = systemUnitId;
  }
  public SystemDeviceDTO systemUnitName(String systemUnitName) {
    this.systemUnitName = systemUnitName;
    return this;
  }

  

  /**
  * Get systemUnitName
  * @return systemUnitName
  **/
  @Schema(description = "")
  public String getSystemUnitName() {
    return systemUnitName;
  }
  public void setSystemUnitName(String systemUnitName) {
    this.systemUnitName = systemUnitName;
  }
  public SystemDeviceDTO typeId(Long typeId) {
    this.typeId = typeId;
    return this;
  }

  

  /**
  * Get typeId
  * @return typeId
  **/
  @Schema(description = "")
  public Long getTypeId() {
    return typeId;
  }
  public void setTypeId(Long typeId) {
    this.typeId = typeId;
  }
  public SystemDeviceDTO typeName(String typeName) {
    this.typeName = typeName;
    return this;
  }

  

  /**
  * Get typeName
  * @return typeName
  **/
  @Schema(description = "")
  public String getTypeName() {
    return typeName;
  }
  public void setTypeName(String typeName) {
    this.typeName = typeName;
  }
  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SystemDeviceDTO systemDeviceDTO = (SystemDeviceDTO) o;
    return Objects.equals(this.connectionsUpcomings, systemDeviceDTO.connectionsUpcomings) &&
        Objects.equals(this.deviceConfiguration, systemDeviceDTO.deviceConfiguration) &&
        Objects.equals(this.id, systemDeviceDTO.id) &&
        Objects.equals(this.serialNumber, systemDeviceDTO.serialNumber) &&
        Objects.equals(this.systemUnitId, systemDeviceDTO.systemUnitId) &&
        Objects.equals(this.systemUnitName, systemDeviceDTO.systemUnitName) &&
        Objects.equals(this.typeId, systemDeviceDTO.typeId) &&
        Objects.equals(this.typeName, systemDeviceDTO.typeName);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(connectionsUpcomings, deviceConfiguration, id, serialNumber, systemUnitId, systemUnitName, typeId, typeName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SystemDeviceDTO {\n");
    
    sb.append("    connectionsUpcomings: ").append(toIndentedString(connectionsUpcomings)).append("\n");
    sb.append("    deviceConfiguration: ").append(toIndentedString(deviceConfiguration)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    serialNumber: ").append(toIndentedString(serialNumber)).append("\n");
    sb.append("    systemUnitId: ").append(toIndentedString(systemUnitId)).append("\n");
    sb.append("    systemUnitName: ").append(toIndentedString(systemUnitName)).append("\n");
    sb.append("    typeId: ").append(toIndentedString(typeId)).append("\n");
    sb.append("    typeName: ").append(toIndentedString(typeName)).append("\n");
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