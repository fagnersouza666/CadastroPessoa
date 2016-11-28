package procergs.com.aplicacaoteste.ed;

import com.google.gson.annotations.SerializedName;

/**
 * Created by fagnersouza on 26/11/16.
 */


public class EnderecoED {

    @SerializedName(value = "cep")
    private String cep;

    @SerializedName(value = "lougradouro")
    private String lougradouro;

    @SerializedName(value = "complemento")
    private String complemento;

    @SerializedName(value = "bairro")
    private String bairro;

    @SerializedName(value = "localidade")
    private String localidade;

    @SerializedName(value = "uf")
    private String uf;

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

    public String getLougradouro() {
        return lougradouro;
    }

    public void setLougradouro(String lougradouro) {
        this.lougradouro = lougradouro;
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

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
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
