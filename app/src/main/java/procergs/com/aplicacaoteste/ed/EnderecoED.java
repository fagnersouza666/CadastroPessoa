package procergs.com.aplicacaoteste.ed;

import com.google.gson.annotations.SerializedName;

/**
 * Created by fagnersouza on 26/11/16.
 */


public class EnderecoED {

    @SerializedName(value = "cep")
    private String cep;

    @SerializedName(value = "logradouro")
    private String rua;

    @SerializedName(value = "complemento")
    private String complemento;

    @SerializedName(value = "bairro")
    private String bairro;

    @SerializedName(value = "localidade")
    private String cidade;

    @SerializedName(value = "uf")
    private String estado;

    @SerializedName(value = "unidade")
    private String unidade;

    @SerializedName(value = "ibge")
    private String ibge;

    @SerializedName(value = "gia")
    private String gia;


    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public String getIbge() {
        return ibge;
    }

    public void setIbge(String ibge) {
        this.ibge = ibge;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }
}
