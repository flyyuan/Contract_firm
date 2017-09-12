package com.flyyuan.contract_firm.bean;

/**
 * Created by Yuan on 2017/7/13.
 * class comment:
 */

public class ContractSignBean {

    /**
     * id : 3534e2f2494048488d360064c3a3217a
     * isNewRecord : false
     * createDate : 2017-07-12 15:24:00
     * updateDate : 2017-07-12 15:24:00
     * name : 2017年劳动合同书范本.pdf
     * url : /xxzx/userfiles/1/files/files/2017/07/2017年劳动合同书范本.pdf
     */

    private String id;
    private boolean isNewRecord;
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
