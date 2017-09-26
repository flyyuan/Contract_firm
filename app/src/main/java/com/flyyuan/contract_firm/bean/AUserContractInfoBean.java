package com.flyyuan.contract_firm.bean;

/**
 * Created by Yuan on 2017/9/24.
 * class comment:
 */

public class AUserContractInfoBean {
    /**
     * code : 000
     * desc : 返回成功
     * resultData : {"isNewRecord":true,"partyaid":"8f496e4e319c41f7a40aff78aac04895","partyAName":"广东某某 有限公司","partyAuserName":"谢达到","partyACorrespondenceAddress":"广东省湛江市霞山区","partyAType":"国企","partyAPhone":"02011564132","ldhtTemplate":{"id":"27ae87a201c94fb69508e08ee493b408","isNewRecord":false,"remarks":"","createDate":"2017-09-15 20:18:28","updateDate":"2017-09-15 20:18:28","name":"系统劳动合同(续签合同).pdf","url":"/ldht/userfiles/1/files/files/2017/09/系统劳动合同(续签合同).pdf"}}
     */

    private String code;
    private String desc;
    private ResultDataBean resultData;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public ResultDataBean getResultData() {
        return resultData;
    }

    public void setResultData(ResultDataBean resultData) {
        this.resultData = resultData;
    }

    public static class ResultDataBean {
        /**
         * isNewRecord : true
         * partyaid : 8f496e4e319c41f7a40aff78aac04895
         * partyAName : 广东某某 有限公司
         * partyAuserName : 谢达到
         * partyACorrespondenceAddress : 广东省湛江市霞山区
         * partyAType : 国企
         * partyAPhone : 02011564132
         * ldhtTemplate : {"id":"27ae87a201c94fb69508e08ee493b408","isNewRecord":false,"remarks":"","createDate":"2017-09-15 20:18:28","updateDate":"2017-09-15 20:18:28","name":"系统劳动合同(续签合同).pdf","url":"/ldht/userfiles/1/files/files/2017/09/系统劳动合同(续签合同).pdf"}
         */

        private boolean isNewRecord;
        private String partyaid;
        private String partyAName;
        private String partyAuserName;
        private String partyACorrespondenceAddress;
        private String partyAType;
        private String partyAPhone;
        private LdhtTemplateBean ldhtTemplate;

        public boolean isIsNewRecord() {
            return isNewRecord;
        }

        public void setIsNewRecord(boolean isNewRecord) {
            this.isNewRecord = isNewRecord;
        }

        public String getPartyaid() {
            return partyaid;
        }

        public void setPartyaid(String partyaid) {
            this.partyaid = partyaid;
        }

        public String getPartyAName() {
            return partyAName;
        }

        public void setPartyAName(String partyAName) {
            this.partyAName = partyAName;
        }

        public String getPartyAuserName() {
            return partyAuserName;
        }

        public void setPartyAuserName(String partyAuserName) {
            this.partyAuserName = partyAuserName;
        }

        public String getPartyACorrespondenceAddress() {
            return partyACorrespondenceAddress;
        }

        public void setPartyACorrespondenceAddress(String partyACorrespondenceAddress) {
            this.partyACorrespondenceAddress = partyACorrespondenceAddress;
        }

        public String getPartyAType() {
            return partyAType;
        }

        public void setPartyAType(String partyAType) {
            this.partyAType = partyAType;
        }

        public String getPartyAPhone() {
            return partyAPhone;
        }

        public void setPartyAPhone(String partyAPhone) {
            this.partyAPhone = partyAPhone;
        }

        public LdhtTemplateBean getLdhtTemplate() {
            return ldhtTemplate;
        }

        public void setLdhtTemplate(LdhtTemplateBean ldhtTemplate) {
            this.ldhtTemplate = ldhtTemplate;
        }

        public static class LdhtTemplateBean {
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
    }
}
