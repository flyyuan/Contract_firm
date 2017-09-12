package com.flyyuan.contract_firm.network;

/**
 * Created by Yuan on 2017/7/12.
 * class comment:
 */

  public  class  LaborURL {
    public static  String base_URL = "http://39.108.69.214:8080/ldht";
    public static String get_contractSign_URL = base_URL + "/contractSign/launchContractByMobile";
    public static String get_id_URL = base_URL + "/sys/office/treeData";
    public static String get_type_work_URL = base_URL + "/sys/office/treeData";
    public static String get_company_URL = base_URL +"/sys/office/treeData";
    public static String post_contractSign = base_URL+ "/contractSign/saveByMobile";
}
