package com.flyyuan.contract_firm.network;

/**
 * Created by Yuan on 2017/7/12.
 * class comment:
 */

  public  class  LaborURL {
    public static String base_URL = "http://bennyshi.s3.natapp.cc/ldht";
    public static String login_URL = base_URL+"/a/login";
    public static String model_URL = base_URL+ "/a/ldht/ldhtTemplate/listByMobile";
    public static String AuserInfo_URL = base_URL+ "/a/ldht/contractSign/showContractByMobile";
    public static String checkPhone_URL = base_URL+"/a/ldht/ldhtFormworkGuangdong/checkPartyBPhoneByMobile";
    public static String postSaveContract_URL = base_URL +"/a/ldht/sysFormworkStart/saveByMobile";
}
