package com.example.atf.models.jsons;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ClientJson {

    @ApiModelProperty(name="clientId",
            example="1",
            required=true)
    @JsonProperty
    private Long clientId;

    @ApiModelProperty(name="clientName",
            example="Диас",
            required=true)
    @JsonProperty
    private String clientName;

    @ApiModelProperty(name="clientSurname",
            example="Абдуалиев",
            required=true)
    @JsonProperty
    private String clientSurname;

    @ApiModelProperty(name="clientSurname",
            example="Галымжанович")
    @JsonProperty
    private String clientPatronymic;
}
