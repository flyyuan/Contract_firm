package com.flyyuan.contract_firm.bean;

/**
 * Created by Yuan on 2017/9/21.
 * class comment:
 */

public class ContractModelBean {
    /**
     * id : 27ae87a201c94fb69508e08ee493b408
     * isNewRecord : false
     * remarks :
     * createDate : 2017-09-15 20:18:28
     * updateDate : 2017-09-15 20:18:28
     * name : 系统劳动合同(续签合同).pdf
     * url : /ldht/userfiles/1/files/files/2017/09/系统劳动合同(续签合同).pdf
     */

    private String id;
    private boolean isNewRecord;
    private String remarks;
    private String createDate;
    private String updateDate;
    private String name;
    private String url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isIsNewRecord() {
        return isNewRecord;
    }

    public void setIsNewRecord(boolean isNewRecord) {
        this.isNewRecord = isNewRecord;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
