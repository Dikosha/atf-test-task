package com.example.atf.models.jsons;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ContactJson {

    @ApiModelProperty(name="contactId",
            example="1",
            required=true)
    @JsonProperty
    private Long contactId;

    @ApiModelProperty(name="contactPhone",
            example="+77475900709",
            required=true)
    @JsonProperty
    private String contactPhone;

    @ApiModelProperty(name="contactTypeId",
            example="1",
            required=true)
    @JsonProperty
    private Long contactTypeId;

    @ApiModelProperty(name="contactClientId",
            example="1",
            required=true)
    @JsonProperty
    private Long contactClientId;

}
