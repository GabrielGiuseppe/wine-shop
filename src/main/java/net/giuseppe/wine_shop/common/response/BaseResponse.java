package net.giuseppe.wine_shop.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse<T> {

    public BaseResponse() {
        this.success = true;
        this.errorMessage = null;
        this.description = null;
        this.rejectValue = null;
        this.field = null;
        this.errorCode = null;
        this.error = null;
        this.data = null;
    }

    public BaseResponse(T data) {
        this.success = data != null;
        this.errorMessage = null;
        this.description = null;
        this.rejectValue = null;
        this.field = null;
        this.errorCode = null;
        this.error = null;
        this.data = data;
    }

    @JsonProperty("sucesso")
    public Boolean success;

    @JsonProperty("codigoErro")
    public Integer errorCode;

    @JsonProperty("erro")
    public String error;

    @JsonProperty("campo")
    public String field;

    @JsonProperty("valorRejeitado")
    public Object rejectValue;

    @JsonProperty("mensagemErro")
    public String errorMessage;

    @JsonProperty("descricao")
    public String description;

    @JsonProperty("dados")
    public T data;
}
