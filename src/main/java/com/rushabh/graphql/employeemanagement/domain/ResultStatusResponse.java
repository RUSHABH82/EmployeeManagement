package com.rushabh.graphql.employeemanagement.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "resultStatus"
})
public class ResultStatusResponse {
    @JsonProperty("resultStatus")
    private ResultStatus resultStatus;


    public ResultStatusResponse() {
        super();
    }

    public ResultStatusResponse(ResultStatus resultStatus) {
        super();
        this.resultStatus = resultStatus;
    }

    public ResultStatus getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(ResultStatus resultStatus) {
        this.resultStatus = resultStatus;
    }

    @Override
    public String toString() {
        return "ResultStatusResponse{" +
                "resultStatus=" + resultStatus +
                '}';
    }
}
