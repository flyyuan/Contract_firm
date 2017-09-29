package com.flyyuan.contract_firm.network;

/**
 * Created by Yuan on 2017/7/12.
 * class comment:
 */

public class LaborURL {
    //根地址
    public static String base_URL = "http://bennyshi.s3.natapp.cc/ldht";
    //登录地址
    public static String login_URL = base_URL + "/a/login";
    //合同模板地址
    public static String model_URL = base_URL + "/a/ldht/ldhtTemplate/listByMobile";
    //甲方用户信息地址
    public static String AuserInfo_URL = base_URL + "/a/ldht/contractSign/showContractByMobile";
    //手机号码检查
    public static String checkPhone_URL = base_URL + "/a/ldht/ldhtFormworkGuangdong/checkPartyBPhoneByMobile";
    //保存合同
    public static String postSaveContract_URL = base_URL + "/a/ldht/sysFormworkStart/saveByMobile";
    //直接发起合同签约
    public static String pendingContract_URL = base_URL + "/a/ldht/ldhtFormworkGuangdong/listByMobile?contractSignStatus:1";
    //所有合同记录
    public static String AlreadyLauched_URL = base_URL + "/a/ldht/ldhtFormworkGuangdong/listByMobile";
    //已签署
    public static String alreadySign_URL = base_URL + "/a/ldht/ldhtFormworkGuangdong/listByMobile?contractSignStatus:3";
}
